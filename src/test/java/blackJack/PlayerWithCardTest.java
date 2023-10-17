package blackJack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import blackJack.domain.CardGenerator;
import blackJack.domain.Player;
import blackJack.domain.Players;
import blackJack.domain.PlayersWithCard;
import blackJack.domain.RandomCardGenerator;
import blackJack.view.OutputView;
import camp.nextstep.edu.missionutils.test.NsTest;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

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
        PlayersWithCard playersWithCard = players.firstCardSetting(cardGenerator);
        OutputView.printFirstSetting(playersWithCard);
    }
}
