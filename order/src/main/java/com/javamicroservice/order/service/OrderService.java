package com.javamicroservice.order.service;

import com.javamicroservice.order.dto.FoodCatalogueDTO;
import com.javamicroservice.order.dto.OrderDTO;
import com.javamicroservice.order.dto.UserInformationDTO;
import com.javamicroservice.order.entity.Order;
import com.javamicroservice.order.mapper.OrderMapper;
import com.javamicroservice.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        
        // Enrich food items with IDs from FoodCatalogue service
        List<FoodCatalogueDTO> enrichedFoodItems = enrichFoodItemsWithIds(
            orderDTO.getFoodCatalogueDTOS(), 
            orderDTO.getRestaurant().getId()
        );
        
        Order order = new Order(Integer.valueOf(orderId), enrichedFoodItems, userInformationDTO, orderDTO.getRestaurant());
        order.setUserId(userInformationDTO.getUserid());
        orderRepository.save(order);
        List<Order> orderDTOS  = orderRepository.findAll();

        //Convert from Entity to DTOs
        return orderDTOS.stream()
                .map(OrderMapper.ORDER_MAPPER::orderInformationEntityToDTO)
                .collect(Collectors.toList());
    }

    private List<FoodCatalogueDTO> enrichFoodItemsWithIds(List<FoodCatalogueDTO> orderFoodItems, Integer restaurantId) {
        try {
            // Fetch food items from FoodCatalogue service
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                "https://FOOD-CATALOGUE-SERVICE/foodCatalogue/fetchRestaurantAndFoodItemsById/" + restaurantId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, Object>>() {}
            );
            
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("foodCatalogueDTOS")) {
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> catalogueItems = (List<Map<String, Object>>) responseBody.get("foodCatalogueDTOS");
                
                // Create a map of itemName -> id for quick lookup
                Map<String, Integer> itemNameToIdMap = new HashMap<>();
                for (Map<String, Object> item : catalogueItems) {
                    String itemName = (String) item.get("itemName");
                    Object idObj = item.get("id");
                    if (itemName != null && idObj != null) {
                        Integer id = idObj instanceof Integer ? (Integer) idObj : 
                                    idObj instanceof Number ? ((Number) idObj).intValue() : null;
                        if (id != null) {
                            itemNameToIdMap.put(itemName, id);
                        }
                    }
                }
                
                // Enrich order food items with IDs
                return orderFoodItems.stream()
                    .map(item -> {
                        Integer id = itemNameToIdMap.get(item.getItemName());
                        if (id != null && item.getId() == null) {
                            item.setId(id);
                        }
                        return item;
                    })
                    .collect(Collectors.toList());
            }
        } catch (Exception e) {
            // Log error but continue with original items if enrichment fails
            System.err.println("Error enriching food items with IDs: " + e.getMessage());
        }
        
        // Return original items if enrichment fails
        return orderFoodItems;
    }

    private UserInformationDTO getUserInformationFromMS(Integer userId) {
        return restTemplate.getForObject("https://USER-INFORMATION-SERVICE/user/" + userId, UserInformationDTO.class);
    }
}
