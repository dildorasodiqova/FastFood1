package uz.example.fastfood.service.locationService;

import uz.example.fastfood.dtos.createDto.LocationDto;
import uz.example.fastfood.enties.Location;

import java.util.List;
import java.util.UUID;

public interface LocationService {
    Location createLocation(LocationDto locationDto);
    Location updateLocation(UUID id, LocationDto locationDto);

    void deleteLocation(UUID id);
    Location getLocationById(UUID id);
    List<Location> getAllLocations();
}
