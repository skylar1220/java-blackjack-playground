package blackJack.domain;

import java.util.Objects;

public class Card {

    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public static Card fromTest(Suit suit, Rank rank) {
        return new Card(suit,rank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return suit == card.suit && rank == card.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }

    public String getCard() {
        return rank.getNumber() + suit.getForm() ;
    }

    public Rank getRank() {
        return rank;
    }
}
