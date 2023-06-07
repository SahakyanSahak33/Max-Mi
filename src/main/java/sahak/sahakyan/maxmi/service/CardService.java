package sahak.sahakyan.maxmi.service;

import sahak.sahakyan.maxmi.dto.CardDTO;
import sahak.sahakyan.maxmi.entity.Card;
import sahak.sahakyan.maxmi.entity.User;

public interface CardService {
    Card findByCardId(Long id);
    Card findCardByUser(User user);
    void saveCard(Card card);
    Card createCard(CardDTO cardDTO, User user);
    CardDTO createCardDTO(Card card);
}
