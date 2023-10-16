package blackJack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RandomCardGenerator implements CardGenerator {

    List<Card> openedCards = new ArrayList<>();

    @Override
    public Card generate(NumberGenerator randomNumberGenerator) {
        Suit suit = getRandomSuit(randomNumberGenerator);
        Rank rank = getRandomRank(randomNumberGenerator);
        Card card = null; // default?
        do {
            card = new Card(suit, rank);
        } while (openedCards.contains(card));
        openedCards.add(card);
        return card;
    }

    private Rank getRandomRank(NumberGenerator randomNumberGenerator) {
        int rankIndex = randomNumberGenerator.generateRankIndex();
        List<Rank> ranks = Arrays.asList(Rank.values());
        return ranks.get(rankIndex);
    }

    private Suit getRandomSuit(NumberGenerator randomNumberGenerator) {
        int suitIndex = randomNumberGenerator.generateSuitIndex();
        List<Suit> suits = Arrays.asList(Suit.values());
        return suits.get(suitIndex);
    }
}
