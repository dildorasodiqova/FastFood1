package uz.example.fastfood.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.example.fastfood.dtos.createDto.OrderCreateDto;
import uz.example.fastfood.dtos.request.order.OrderUpdateStatusReqDTO;
import uz.example.fastfood.dtos.responcseDto.BaseResponse;
import uz.example.fastfood.dtos.responcseDto.OrderResponseDto;
import uz.example.fastfood.service.orderService.OrderService;

import java.util.UUID;


@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/make")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<BaseResponse<?>> makeOrder(@RequestBody @Valid OrderCreateDto dto) {
        log.info("Make order with = {}", dto);
        return ResponseEntity.ok(
                orderService.makeOrder(dto)
        );
    }

    @PutMapping("/status")
    @PreAuthorize("hasRole('ROLE_OFITSIANT')")
    public ResponseEntity<BaseResponse<?>> updateStatus(@RequestBody @Valid OrderUpdateStatusReqDTO dto) {
        log.info(
                "Update status"
        );
        return ResponseEntity.ok(orderService.updateStatus(dto.getOrderId(), dto.getStatus()));
    }

    @GetMapping("/admin-all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<BaseResponse<PageImpl<OrderResponseDto>>> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                           @RequestParam(value = "size", defaultValue = "50") int size) {
        log
                .info("get all page = {} size = {}", page, size);
        return ResponseEntity.ok(orderService.getAll(page, size));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<BaseResponse<PageImpl<OrderResponseDto>>> getAllAsUser(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                  @RequestParam(value = "size", defaultValue = "50") int size) {
        log
                .info("get all page = {} size = {}", page, size);
        // todo userId
        return ResponseEntity.ok(orderService.getAllAsUser(UUID.fromString(""),page, size));
    }
}
