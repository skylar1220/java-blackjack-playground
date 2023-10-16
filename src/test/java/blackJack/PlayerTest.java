package blackJack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void name() {
        assertThatThrownBy(() -> InputValidator.validatePlayerNames(""))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("공백은 입력할 수 없습니다.");
        assertThatThrownBy(() -> InputValidator.validatePlayerNames("가나#다"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("형식에 맞지 않습니다.");
        assertThatThrownBy(() -> InputValidator.validatePlayerNames("가나#다,라마"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("형식에 맞지 않습니다.");
        InputValidator.validatePlayerNames("가나");
        InputValidator.validatePlayerNames("가나,다라");
    }
}
