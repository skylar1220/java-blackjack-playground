package blackJack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomCardGenerator implements CardGenerator {

    List<Card> openedCars = new ArrayList<>();

    @Override
    public Card generate(NumberGenerator randomNumberGenerator) {
//        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        int suitIndex = randomNumberGenerator.generateSuitIndex();
        List<Suit> suits = Arrays.asList(Suit.values());
        Suit suit = suits.get(suitIndex);
        Rank rank = null;
        return new Card(suit, rank);
    }
}
