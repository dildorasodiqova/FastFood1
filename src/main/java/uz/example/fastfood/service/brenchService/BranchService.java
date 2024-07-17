package uz.example.fastfood.service.brenchService;

import uz.example.fastfood.dtos.createDto.BranchCreateDto;
import uz.example.fastfood.dtos.responcseDto.BranchResponseDto;
import uz.example.fastfood.enties.Location;

import java.util.List;
import java.util.UUID;

public interface BranchService {
    List<BranchResponseDto> getAllBranches();
    BranchResponseDto getBranchById(UUID id);
    BranchResponseDto createBranch(BranchCreateDto dto);
    BranchResponseDto updateBranch(UUID id, BranchCreateDto dto);
    void deleteBranch(UUID id);
    BranchResponseDto findNearestBranch(Location location);
}


