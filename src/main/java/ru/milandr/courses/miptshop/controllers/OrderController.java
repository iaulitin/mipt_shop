package ru.milandr.courses.miptshop.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.milandr.courses.miptshop.dtos.OrderDto;
import ru.milandr.courses.miptshop.services.OrderService;
import ru.milandr.courses.miptshop.common.utils.ValidationException;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
    public static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) throws ValidationException {
        return ResponseEntity.ok(orderService.get(orderId));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDto>> getOrders() throws ValidationException {
        return ResponseEntity.ok(orderService.getUserOrders());
    }

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody OrderDto orderDto) throws ValidationException {
        orderService.create(orderDto);
    }

    @GetMapping(value = "cart", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto getCart() throws ValidationException {
        return orderService.getCart();
    }

    @GetMapping
    public List<ResponseEntity<OrderDto>> getByUserIdAndStatus(
            @RequestParam(value = "userId", required = false) Long userId,
            @RequestParam(value = "status", required = false) Short status) {
        log.info("user id: {}, status: {}", userId, status);
        return null;
    }
}
