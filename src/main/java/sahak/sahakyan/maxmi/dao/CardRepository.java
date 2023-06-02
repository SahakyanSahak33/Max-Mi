package sahak.sahakyan.maxmi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sahak.sahakyan.maxmi.entity.Card;
import sahak.sahakyan.maxmi.entity.User;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByCardId(Long id);
    Card findCardByUser(User user);
}