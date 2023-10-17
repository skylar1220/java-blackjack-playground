package blackJack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import blackJack.domain.BettingMoney;
import blackJack.domain.Card;
import blackJack.domain.CardGenerator;
import blackJack.domain.Player;
import blackJack.domain.PlayerWithCard;
import blackJack.domain.Players;
import blackJack.domain.PlayersWithCard;
import blackJack.domain.RandomCardGenerator;
import blackJack.domain.Rank;
import blackJack.domain.Suit;
import blackJack.view.OutputView;
import camp.nextstep.edu.missionutils.test.NsTest;
import java.time.Duration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class isBlackJackTest extends NsTest {
    private static final Duration RANDOM_TEST_TIMEOUT = Duration.ofSeconds(10L);
//    CardGenerator cardGenerator = new RandomCardGenerator();

    @Nested
    class AllFeatureTest {
        @Test
        void pwc() {
            assertTimeoutPreemptively(RANDOM_TEST_TIMEOUT, () -> {
                runMain();
                assertThat(output()).contains(
                    "BLACKJACK", "일부러"
                );
            });
        }
    }

    @Override
    protected void runMain() {
        Players players = Players.fromTest(Player.fromTest("가나", 100)
            , Player.fromTest("다라", 100));
        Player dealer = Player.from("딜러", BettingMoney.from(200));
        CardGenerator cardGenerator = new RandomCardGenerator();
        PlayersWithCard playersWithCard = players.firstCardSetting(cardGenerator);

        Player tester = Player.from("tester", BettingMoney.from(200));
        Card card1 = Card.fromTest(Suit.DIA, Rank.A);
        Card card2 = Card.fromTest(Suit.DIA, Rank.Q);
        PlayerWithCard blackPlayerWithCard = tester.playerCardFirstSettingTest(card1,card2);

        PlayerWithCard dealerWithCard = dealer.playerCardFirstSetting(cardGenerator);

        playersWithCard.add(blackPlayerWithCard);
        playersWithCard.checkBalckJack();
    }
}
