package com.sandy.orderservice.service;

import com.sandy.orderservice.model.Order;
import com.sandy.orderservice.model.OrderRequest;
import com.sandy.orderservice.model.OrderLineItems;
import com.sandy.orderservice.model.OrderLineItemsDto;
import com.sandy.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    private final WebClient webClient;

    public OrderService(WebClient webClient) {
        this.webClient = webClient;
    }

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNo(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream().map(orderLineItemsDto -> mapToOrderLineItems(orderLineItemsDto)).toList();
        order.setOrderLineItemsList(orderLineItems);
        Boolean result =webClient.get().uri("http://localhost:8082/api/inventory")
                        .retrieve().bodyToMono(Boolean.class).block();
        if(result)
        orderRepository.save(order);
        else
            throw new IllegalArgumentException("Product is not in stock, try again later");
    }

    private OrderLineItems mapToOrderLineItems(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItems.getQuantity());
        orderLineItems.setSkuCode(orderLineItems.getSkuCode());
        return orderLineItems;
    }
}
