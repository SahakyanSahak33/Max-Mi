package sahak.sahakyan.maxmi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sahak.sahakyan.maxmi.entity.Basket;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    Basket findByBasketId(Long id);
    Basket findByUserId(Long user_id);
}
