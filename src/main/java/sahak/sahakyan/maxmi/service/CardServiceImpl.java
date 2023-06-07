package sahak.sahakyan.maxmi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sahak.sahakyan.maxmi.dao.CardRepository;
import sahak.sahakyan.maxmi.dto.CardDTO;
import sahak.sahakyan.maxmi.entity.Card;
import sahak.sahakyan.maxmi.entity.User;

import java.util.Arrays;

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

    @Override
    public Card createCard(CardDTO cardDTO, User user) {
        Card card = new Card();
        String cardNumber = cardDTO.getValue1()+"-"+cardDTO.getValue2()+"-"+cardDTO.getValue3()+"-"+cardDTO.getValue4();
        card.setCardNumber(cardNumber);
        String cardExpirationDate = cardDTO.getExpirationDate1() + "-" + cardDTO.getExpirationDate2();
        card.setExpirationDate(cardExpirationDate);
        String signature = String.valueOf(cardDTO.getCardHolder());
        card.setSignature(signature);
        card.setUser(user);
        return card;
    }

    @Override
    public CardDTO createCardDTO(Card card) {
        CardDTO cardDTO = new CardDTO();
        String modified = card.getCardNumber().replaceAll("-", "");
        String[] parts = modified.split("(?<=\\G.{4})");
        System.out.println(Arrays.toString(parts));
        cardDTO.setValue1(Integer.valueOf(parts[0]));
        cardDTO.setValue2(Integer.valueOf(parts[1]));
        cardDTO.setValue3(Integer.valueOf(parts[2]));
        cardDTO.setValue4(Integer.valueOf(parts[3]));
        cardDTO.setCardHolder(Integer.parseInt(card.getSignature()));
        parts = card.getExpirationDate().split("-");
        cardDTO.setExpirationDate1(Integer.parseInt(parts[0]));
        cardDTO.setExpirationDate2(Integer.parseInt(parts[1]));
        return cardDTO;
    }
}
