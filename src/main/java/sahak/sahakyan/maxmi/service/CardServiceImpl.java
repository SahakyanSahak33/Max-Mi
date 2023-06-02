package sahak.sahakyan.maxmi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sahak.sahakyan.maxmi.dao.CardRepository;
import sahak.sahakyan.maxmi.entity.Card;
import sahak.sahakyan.maxmi.entity.User;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService{

    private final CardRepository cardRepository;

    @Override
    public Card findByCardId(Long id) {
        return cardRepository.findByCardId(id);
    }

    @Override
    public Card findCardByUser(User user) {
        return cardRepository.findCardByUser(user);
    }

    @Override
    public void saveCard(Card card) {
        cardRepository.save(card);
    }
}
