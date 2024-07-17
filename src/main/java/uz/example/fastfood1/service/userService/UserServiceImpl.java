package uz.example.fastfood1.service.userService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.example.fastfood.dtos.request.LoginReqDTO;
import uz.example.fastfood.dtos.request.RegisterReqDTO;
import uz.example.fastfood.dtos.responcseDto.BaseResponse;
import uz.example.fastfood.dtos.responcseDto.UserResponseDto;
import uz.example.fastfood.dtos.responcseDto.auth.LoginResponseDTO;
import uz.example.fastfood.enties.UserEntity;
import uz.example.fastfood.enums.UserRole;
import uz.example.fastfood.exception.BadRequestException;
import uz.example.fastfood.exception.DataAlreadyExistsException;
import uz.example.fastfood.exception.DataNotFoundException;
import uz.example.fastfood.jwt.JwtService;
import uz.example.fastfood.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public UserEntity findByPhone(String phone) {
        return userRepository
                .findByPhoneNumberAndIsActiveIsTrue(phone)
                .orElseThrow(() -> new DataNotFoundException("User not found!"));
    }

    @Override
    public BaseResponse<?> register(RegisterReqDTO registerDTO) {
        Optional<UserEntity> existsUser = userRepository.findByPhoneNumberAndIsActiveIsTrue(registerDTO.getPhone());
        if (existsUser.isPresent()) {
            log.warn("User already exists phone = {}", registerDTO.getPhone());
            throw new DataAlreadyExistsException("User already exists!");
        }
        UserEntity entity = new UserEntity();
        entity.setFullName(registerDTO.getFullName());
        entity.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        entity.setPhoneNumber(registerDTO.getPhone());
        entity.setRole(UserRole.USER);
        userRepository.save(entity);

        return BaseResponse.success(UserResponseDto.toDTO(entity));
    }

    @Override
    public BaseResponse<?> login(LoginReqDTO dto) {
        UserEntity user = findByPhone(dto.getPhone());

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            log.warn("Password incorrect userId={}", user.getId());
            throw new BadRequestException("Login or password incorrect!");
        }

        return BaseResponse.success(
                LoginResponseDTO.toDTO(
                        jwtService.generateAccessToken(user),
                        jwtService.generateRefreshToken(user),
                        user
                )
        );
    }
}
