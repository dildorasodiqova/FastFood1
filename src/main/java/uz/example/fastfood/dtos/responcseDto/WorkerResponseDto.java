package uz.example.fastfood.dtos.responcseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.example.fastfood.enums.UserRole;

import java.time.LocalDateTime;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WorkerResponseDto {
    private UUID id;
    private String fullName;
    private UserRole role;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
