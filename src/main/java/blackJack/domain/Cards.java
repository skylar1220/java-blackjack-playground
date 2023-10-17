package blackJack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private final List<Card> cards;

    private Cards() {
        this.cards = new ArrayList<>();
    }

    public static Cards from() {
        return new Cards();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<String> getCardsName() {
        return cards.stream()
            .map(card -> card.getCard())
            .collect(Collectors.toList());
    }

    public boolean isBlackJack() {
        int cardSum = cards.stream()
            .map(card -> card.getRank().getNumber())
            .reduce(0, Integer::sum);
        return cardSum == 11;
    }
}
