package sahak.sahakyan.maxmi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sahak.sahakyan.maxmi.dao.BasketRepository;
import sahak.sahakyan.maxmi.entity.Basket;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService{

    private final BasketRepository basketRepository;


    @Override
    @Transactional
    public Basket findByBasketId(Long id) {
        return basketRepository.findByBasketId(id);
    }

    @Override
    @Transactional
    public void saveBasket(Basket basket) {
        basketRepository.save(basket);
    }

    @Override
    @Transactional
    public Basket findByUserId(Long user_id) {
        return basketRepository.findByUserId(user_id);
    }
}
