package uz.example.fastfood.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.example.fastfood.dtos.createDto.BranchCreateDto;
import uz.example.fastfood.dtos.responcseDto.BranchResponseDto;
import uz.example.fastfood.enties.Location;
import uz.example.fastfood.service.brenchService.BranchService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/branch")
public class BranchController {
    private final BranchService branchService;
    @Operation(summary = "Get all branches",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of branches")
    })
    @PermitAll
    @GetMapping
    public ResponseEntity<List<BranchResponseDto>> getAllBranches() {
        List<BranchResponseDto> branches = branchService.getAllBranches();
        return new ResponseEntity<>(branches, HttpStatus.OK);
    }

    @Operation(summary = "Get branch by ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved branch"),
            @ApiResponse(responseCode = "404", description = "Branch not found")
    })
    @PermitAll
    @GetMapping("/{id}")
    public ResponseEntity<BranchResponseDto> getBranchById(@PathVariable UUID id) {
        BranchResponseDto branch = branchService.getBranchById(id);
        return ResponseEntity.ok(branch);
    }

    @Operation(summary = "Create a new branch",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Branch created successfully")
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<BranchResponseDto> createBranch(@RequestBody BranchCreateDto dto) {
        BranchResponseDto createdBranch = branchService.createBranch(dto);
        return new ResponseEntity<>(createdBranch, HttpStatus.CREATED);
    }

    @Operation(summary = "Update branch by ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Branch updated successfully"),
            @ApiResponse(responseCode = "404", description = "Branch not found")
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<BranchResponseDto> updateBranch(@PathVariable UUID id, @RequestBody BranchCreateDto dto) {
        BranchResponseDto updatedBranch = branchService.updateBranch(id, dto);
        return ResponseEntity.ok(updatedBranch);
    }

    @Operation(summary = "Delete branch by ID",
            method = "DELETE",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Branch deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Branch not found")
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable UUID id) {
        branchService.deleteBranch(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Find nearest branch by name",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the nearest branch"),
            @ApiResponse(responseCode = "404", description = "Branch not found")
    })
    @PermitAll
    @GetMapping("/nearest")
    public ResponseEntity<BranchResponseDto> findNearestBranch( @RequestParam double latitude, @RequestParam double longitude) {
        Location location = new Location();
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        BranchResponseDto nearestBranch = branchService.findNearestBranch( location);
        return ResponseEntity.ok(nearestBranch);
    }
}

