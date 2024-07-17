package uz.example.fastfood.dtos.responcseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.example.fastfood.dtos.createDto.LocationDto;
import uz.example.fastfood.enties.UserEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponseDto {
    private UUID id;
    private String fullName;
    private String phoneNumber;
    private LocationDto location;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public UserResponseDto(UUID id, String fullName, String phoneNumber, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public static UserResponseDto toDTO(UserEntity entity) {
        return new UserResponseDto(
                entity.getId(),
                entity.getFullName(),
                entity.getPhoneNumber(),
                entity.getCreateDate(),
                entity.getUpdateDate()
        );
    }
}
