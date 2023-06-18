package sahak.sahakyan.maxmi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sahak.sahakyan.maxmi.dao.OrderRepository;
import sahak.sahakyan.maxmi.entity.Order;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Override
    public Order findByOrderId(Long id) {
        return orderRepository.findByOrderId(id);
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }
}
