package com.javamicroservice.order.mapper;

import com.javamicroservice.order.dto.OrderDTO;
import com.javamicroservice.order.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper ORDER_MAPPER = Mappers.getMapper(OrderMapper.class);

    Order orderInformationDTOToEntity(OrderDTO orderDTO);

    OrderDTO orderInformationEntityToDTO(Order order);
}
