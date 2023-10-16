package blackJack;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class RandomCardTest {

    @Test
    void cardGeneratorTest() {
        NumberGenerator numberGenerator = new NumberGenerator() {
            @Override
            public int generateSuitIndex() {
                return 0;
            }

            @Override
            public int generateRankIndex() {
                return 0;
            }
        };
        RandomCardGenerator randomCardGenerator = new RandomCardGenerator();
        assertThat(randomCardGenerator.generate(numberGenerator)).isEqualTo(
            new Card(Suit.CLOVER, Rank.A));
    }

    @Test
    void randomTest() {
        RandomCardGenerator randomCardGenerator = new RandomCardGenerator();
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        System.out.println(randomCardGenerator.generate(randomNumberGenerator));
        System.out.println(randomCardGenerator.generate(randomNumberGenerator));
        System.out.println(randomCardGenerator.generate(randomNumberGenerator));
        System.out.println(randomCardGenerator.generate(randomNumberGenerator));
        System.out.println(randomCardGenerator.generate(randomNumberGenerator));
    }
}
