package blackJack;

import static blackJack.RetyrUtil.*;

import java.util.List;

public class GameController {
    InputView inputView = InputView.getInstance();

    public void start() {
        InputView.scanPlayerNames();
        PlayerNamesDto playerNamesDto = read(InputView::scanPlayerNames);
        List<PlayerInfoDto> playerInfoDtos = inputView.scanBettingMoneys(playerNamesDto.getPlayerNames());
    }
}
