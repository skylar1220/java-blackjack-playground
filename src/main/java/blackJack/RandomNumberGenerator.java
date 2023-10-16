package blackJack;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {

    public int generateSuitIndex() {
        Random random = new Random(3);
        return random.nextInt();
    }

    @Override
    public int generateRankIndex() {
        return 0;
    }
}
