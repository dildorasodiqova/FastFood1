package uz.example.fastfood.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.example.fastfood.dtos.request.LoginReqDTO;
import uz.example.fastfood.dtos.request.RegisterReqDTO;
import uz.example.fastfood.dtos.responcseDto.BaseResponse;
import uz.example.fastfood.service.userService.UserService;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<?>> register(@RequestBody @Valid RegisterReqDTO registerDTO) {
        log.info("Register new user = {}", registerDTO);
        return ResponseEntity.ok(
                userService.register(registerDTO)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<?>> login(@RequestBody @Valid LoginReqDTO dto) {
        log.info("login with {}", dto);
        return ResponseEntity.ok(
                userService.login(dto)
        );
    }
}
