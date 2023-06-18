package sahak.sahakyan.maxmi.service;

import sahak.sahakyan.maxmi.entity.Basket;

public interface BasketService {
    Basket findByBasketId(Long id);
    void saveBasket(Basket basket);
    void deleteBasket(Basket basket);
    Basket findByUserId(Long user_id);
}
