package uz.example.fastfood.dtos.responcseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.example.fastfood.enties.Location;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BranchResponseDto {
    private UUID id;
    private String moljal;
    private String name;
    private Location location;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
