package blackJack;

import static blackJack.util.RetyrUtil.read;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import blackJack.domain.BettingMoney;
import blackJack.domain.CardGenerator;
import blackJack.domain.Player;
import blackJack.domain.PlayerWithCard;
import blackJack.domain.Players;
import blackJack.domain.PlayersWithCard;
import blackJack.domain.RandomCardGenerator;
import blackJack.view.OutputView;
import camp.nextstep.edu.missionutils.test.NsTest;
import java.time.Duration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class PlayerWithCardTest extends NsTest {
    private static final Duration RANDOM_TEST_TIMEOUT = Duration.ofSeconds(10L);
    CardGenerator cardGenerator = new RandomCardGenerator();

    @Nested
    class AllFeatureTest {
        @Test
        void pwc() {
            assertTimeoutPreemptively(RANDOM_TEST_TIMEOUT, () -> {
                runMain();
                assertThat(output()).contains(
                    "딜러", "가나", "다라", "스페이드", "일부러"
                );
            });
        }
    }

    @Override
    protected void runMain() {
        Players players = Players.fromTest(Player.fromTest("가나", 100)
            , Player.fromTest("다라", 100));
        Player dealer = Player.from("딜러", BettingMoney.from(200));

        PlayersWithCard playersWithCard = players.firstCardSetting(cardGenerator);
        PlayerWithCard dealerWithCard = dealer.playerCardFirstSetting(cardGenerator);
        OutputView.printFirstSetting(playersWithCard, dealerWithCard);
    }
}
