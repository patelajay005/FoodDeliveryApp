package com.javamicroservice.order.service;

import com.javamicroservice.order.dto.OrderDTO;
import com.javamicroservice.order.dto.UserInformationDTO;
import com.javamicroservice.order.entity.Order;
import com.javamicroservice.order.mapper.OrderMapper;
import com.javamicroservice.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    SequenceGenerator sequenceGenerator;

    @Autowired
    RestTemplate restTemplate;

    public List<OrderDTO> saveOrder(OrderDTO orderDTO) {

        String orderId = sequenceGenerator.generateOrderId();
        UserInformationDTO userInformationDTO = getUserInformationFromMS(orderDTO.getUserId());
        Order order = new Order(Integer.valueOf(orderId), orderDTO.getFoodCatalogueDTOS(), userInformationDTO, orderDTO.getRestaurant());
        order.setUserId(userInformationDTO.getUserid());
        orderRepository.save(order);
        List<Order> orderDTOS  = orderRepository.findAll();

        //Convert from Entity to DTOs
        return orderDTOS.stream()
                .map(OrderMapper.ORDER_MAPPER::orderInformationEntityToDTO)
                .collect(Collectors.toList());
    }

    private UserInformationDTO getUserInformationFromMS(Integer userId) {
        return restTemplate.getForObject("https://USER-INFORMATION-SERVICE/user/" + userId, UserInformationDTO.class);
    }
}
