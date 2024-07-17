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
import uz.example.fastfood.dtos.createDto.MealCreateDto;
import uz.example.fastfood.dtos.responcseDto.MealResponseDto;
import uz.example.fastfood.service.mealService.MealService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meals")
public class MealController {
    private final MealService mealService;

    @Operation(summary = "Get all meals",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all meals")
    })
    @PermitAll
    @GetMapping
    public ResponseEntity<List<MealResponseDto>> getAllMeals() {
        List<MealResponseDto> meals = mealService.getAllMeals();
        return new ResponseEntity<>(meals, HttpStatus.OK);
    }

    @Operation(summary = "Get a meal by UUID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the meal"),
            @ApiResponse(responseCode = "404", description = "Meal not found")
    })
    @PermitAll
   @GetMapping("/{id}")
    public ResponseEntity<MealResponseDto> getMealById(@PathVariable UUID id) {
        return ResponseEntity.ok(mealService.getMealById(id));

    }

    @Operation(summary = "Create a new meal",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Meal created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('OFITSIANT')")
    @PostMapping("/create")
    public ResponseEntity<MealResponseDto> createMeal(@RequestBody MealCreateDto dto) {
        MealResponseDto createdMeal = mealService.createMeal(dto);
        return new ResponseEntity<>(createdMeal, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a meal by UUID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meal updated successfully"),
            @ApiResponse(responseCode = "404", description = "Meal not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('OFITSIANT')")
    @PutMapping("/update/{id}")
    public ResponseEntity<MealResponseDto> updateMeal(@PathVariable UUID id, @RequestBody MealCreateDto dto) {
        try {
            MealResponseDto updatedMeal = mealService.updateMeal(id, dto);
            return new ResponseEntity<>(updatedMeal, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a meal by UUID",
            method = "DELETE",
            security = @SecurityRequirement(name = "pre authorize", scopes = {"ADMIN"}))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meal deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Meal not found")
    })
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('OFITSIANT')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable UUID id) {
        mealService.deleteMeal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
