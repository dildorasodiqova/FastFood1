package uz.example.fastfood.dtos.createDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.example.fastfood.enties.Location;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BranchCreateDto {
    private String moljal;
    private String name;
    private Location location;
}
