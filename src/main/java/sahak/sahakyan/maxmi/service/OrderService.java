package sahak.sahakyan.maxmi.service;

import sahak.sahakyan.maxmi.entity.Order;

public interface OrderService {
    Order findByOrderId(Long id);
    void saveOrder(Order order);
}
