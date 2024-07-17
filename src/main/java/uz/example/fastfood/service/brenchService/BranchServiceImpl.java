package uz.example.fastfood.service.brenchService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.example.fastfood.dtos.createDto.BranchCreateDto;
import uz.example.fastfood.dtos.responcseDto.BranchResponseDto;
import uz.example.fastfood.enties.BranchEntity;
import uz.example.fastfood.enties.Location;
import uz.example.fastfood.exception.DataNotFoundException;
import uz.example.fastfood.repository.BranchRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService{
    private final ModelMapper modelMapper;
    private final BranchRepository branchRepository;
    private BranchResponseDto map(BranchEntity entity) {
        return modelMapper.map(entity, BranchResponseDto.class);
    }

    private List<BranchResponseDto> map(List<BranchEntity> entities) {
        return entities.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
    @Override
    public BranchResponseDto findNearestBranch(Location location) {
        BranchEntity nearestBranch = branchRepository.findNearestBranchWithName(location.getLatitude(), location.getLongitude());
        return modelMapper.map(nearestBranch, BranchResponseDto.class);
    }

    @Override
    public List<BranchResponseDto> getAllBranches() {
        return map(branchRepository.findAllByIsActiveTrue());
    }

    @Override
    public BranchResponseDto getBranchById(UUID id) {
        BranchEntity entity = branchRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Branch not found !"));
        return map(entity);
    }

    @Override
    public BranchResponseDto createBranch(BranchCreateDto dto) {
        if (branchRepository.existsByNameAndLocation(dto.getName(), dto.getLocation())) {
            throw new IllegalArgumentException("Branch with the same name and location already exists");
        }
        BranchEntity branchEntity = modelMapper.map(dto, BranchEntity.class);
        branchEntity.setCreatedDate(LocalDateTime.now());
        branchEntity.setUpdateDate(LocalDateTime.now());
        BranchEntity savedEntity = branchRepository.save(branchEntity);
        return map(savedEntity);
    }

    @Override
    public BranchResponseDto updateBranch(UUID id, BranchCreateDto dto) {
        return branchRepository.findById(id).map(existingBranch -> {
            if (branchRepository.existsByNameAndLocation(dto.getName(), dto.getLocation())) {
                throw new IllegalArgumentException("Branch with the same name and location already exists");
            }
            existingBranch.setMoljal(dto.getMoljal());
            existingBranch.setName(dto.getName());
            existingBranch.setLocation(dto.getLocation());
            existingBranch.setUpdateDate(LocalDateTime.now());
            BranchEntity updatedEntity = branchRepository.save(existingBranch);
            return map(updatedEntity);
        }).orElseThrow(() -> new IllegalArgumentException("Branch not found with id: " + id));
    }

    @Override
    public void deleteBranch(UUID id) {
        branchRepository.softDeleteById(id);
    }


}
