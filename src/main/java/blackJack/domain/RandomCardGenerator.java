package blackJack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RandomCardGenerator implements CardGenerator {

    List<Card> openedCards = new ArrayList<>();
    NumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    @Override
    public Card generate() {
        Suit suit = getRandomSuit();
        Rank rank = getRandomRank();
        Card card = null; // default?
        do {
            card = new Card(suit, rank);
        } while (openedCards.contains(card));
        openedCards.add(card);
        return card;
    }

    private Rank getRandomRank() {
        int rankIndex = randomNumberGenerator.generateRankIndex();
        List<Rank> ranks = Arrays.asList(Rank.values());
        return ranks.get(rankIndex);
    }

    private Suit getRandomSuit() {
        int suitIndex = randomNumberGenerator.generateSuitIndex();
        List<Suit> suits = Arrays.asList(Suit.values());
        return suits.get(suitIndex);
    }
}
