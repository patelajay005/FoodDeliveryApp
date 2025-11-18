package com.javamicroservice.order.controller;

import com.javamicroservice.order.dto.OrderDTO;
import com.javamicroservice.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/createOrder")
    public ResponseEntity<List<OrderDTO>> saveOrder(@RequestBody OrderDTO orderDTO) {
        List<OrderDTO> orderDTOS =  orderService.saveOrder(orderDTO);
        return new ResponseEntity<List<OrderDTO>>(orderDTOS, HttpStatus.CREATED);
    }
}
