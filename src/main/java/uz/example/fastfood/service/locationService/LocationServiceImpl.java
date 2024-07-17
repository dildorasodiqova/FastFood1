package uz.example.fastfood.service.locationService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.example.fastfood.dtos.createDto.LocationDto;
import uz.example.fastfood.enties.Location;
import uz.example.fastfood.exception.DataNotFoundException;
import uz.example.fastfood.repository.LocationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService{
    private final LocationRepository locationRepository;

    public Location createLocation(LocationDto locationDto) {
        Location location = new Location();
        location.setLatitude(locationDto.getLatitude());
        location.setLongitude(locationDto.getLongitude());
        location.setAddress(locationDto.getAddress());
        location.setCreatedDate(LocalDateTime.now());
        location.setUpdateDate(LocalDateTime.now());

        return locationRepository.save(location);
    }

    public Location updateLocation(UUID id, LocationDto locationDto) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Location not found with id: " + id));

        location.setLatitude(locationDto.getLatitude());
        location.setLongitude(locationDto.getLongitude());
        location.setAddress(locationDto.getAddress());
        location.setUpdateDate(LocalDateTime.now());

        return locationRepository.save(location);
    }

    public void deleteLocation(UUID id) {
        if (!locationRepository.existsById(id)) {
            throw new DataNotFoundException("Location not found with id: " + id);
        }
        locationRepository.deleteById(id);
    }

    public Location getLocationById(UUID id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Location not found with id: " + id));
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
}
