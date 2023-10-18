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
            .map(Card::getCard)
            .collect(Collectors.toList());
    }

    public boolean isBlackJack() {
        boolean containsA = false;
        boolean containsJQK = false;
        for (Card card:cards) {
            containsA = card.isA();
        }
        for (Card card:cards) {
            containsJQK = card.isJQK();
        }
        return containsA && containsJQK;
    }


    public boolean isOverOr21() {
        int cardSum = getCardsSum();
        return cardSum >= 21;
    }

    public int getCardsSum() {
        return cards.stream()
            .map(card -> card.getRank().getNumber())
            .reduce(0, Integer::sum);
    }

    public boolean needExtraCard() {
        int cardSum = getCardsSum();
        return cardSum <= 16;
    }

    public boolean isSumBiggerThan(int comparison) {
        return getCardsSum() > comparison;
    }

    public void gameWin() {
        status = Status.WIN;
    }

    public boolean isWin() {
        return status == Status.WIN;
    }

    public void gameOver() {
        status = Status.LOSS;
    }

    // a인 경우, 근데 나머지가 ? 이상일 때는 1로, 이하일 때는 11ㅇ로 계산하는 부분 sum 메소드들에 넣어야 할듯

}
