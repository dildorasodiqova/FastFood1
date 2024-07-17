package uz.example.fastfood.enties;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "branch")
public class BranchEntity extends BaseEntity {
    private String moljal;
    private String name;
    private Location location;

}
