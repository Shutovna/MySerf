package ru.shutovna.moyserf.service;

import ru.shutovna.moyserf.model.Order;
import ru.shutovna.moyserf.model.Site;
import ru.shutovna.moyserf.payload.request.OrderRequest;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    List<Order> getAllOrders();

    Optional<Order> getOrder(int orderId);

    Order createOrder(OrderRequest orderRequest);

    Order getFirstOpenedOrderForSite(Site site);

}
