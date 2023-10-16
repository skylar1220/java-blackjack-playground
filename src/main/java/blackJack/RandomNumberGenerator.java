package blackJack;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {

    public int generateSuitIndex() {
        Random random = new Random();
        return random.nextInt(4);
    }

    @Override
    public int generateRankIndex() {
        Random random = new Random();
        return random.nextInt(12);
    }
}
