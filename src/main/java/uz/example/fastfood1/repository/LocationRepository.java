package uz.example.fastfood1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.example.fastfood.enties.Location;

import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, UUID> {
}
