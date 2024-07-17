package uz.example.fastfood.dtos.responcseDto.auth;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.example.fastfood.enties.UserEntity;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginResponseDTO {
    UUID id;
    String fullName;
    String refreshToken;
    String accessToken;

    public static LoginResponseDTO toDTO(String accessToken, String refreshToken, UserEntity user) {
        return new LoginResponseDTO(user.getId(), user.getFullName(), refreshToken, accessToken);
    }
}
