package blackJack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private final List<Card> cards;
    private Status status;

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
        int cardSum = getCardsSum(cards);
        return cardSum == 11;
    }

    public boolean isGameOver() {
        int cardSum = getCardsSum(cards);
        return cardSum >= 21;
    }

    private static int getCardsSum(List<Card> cards) {
        return cards.stream()
            .map(card -> card.getRank().getNumber())
            .reduce(0, Integer::sum);
    }

//    public void setBlackJack() {
//        this.status = Status.BLACKJACK;
//    }
}
