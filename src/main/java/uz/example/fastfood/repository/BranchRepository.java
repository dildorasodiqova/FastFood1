package uz.example.fastfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uz.example.fastfood.enties.BranchEntity;
import uz.example.fastfood.enties.Location;

import java.util.List;
import java.util.UUID;

public interface BranchRepository extends JpaRepository<BranchEntity, UUID> {
    boolean existsByNameAndLocation(String name, Location location);

    @Modifying
    @Transactional
    @Query("UPDATE branch b SET b.isActive = false WHERE b.id = :id")
    void softDeleteById(UUID id);

    List<BranchEntity> findAllByIsActiveTrue();

    @Query("SELECT b FROM branch b ORDER BY ST_Distance(b.location, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326)) ASC")
    BranchEntity findNearestBranchWithName(@Param("latitude") double latitude, @Param("longitude") double longitude);

}
