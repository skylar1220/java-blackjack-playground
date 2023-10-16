package blackJack;

import static blackJack.RetyrUtil.*;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.List;

public class GameController {
    InputView inputView = InputView.getInstance();

    public void start() {
        InputView.scanPlayerNames();
        PlayerNamesDto playerNamesDto = read(InputView::scanPlayerNames);
        List<PlayerInfoDto> playerInfoDtos = inputView.scanBettingMoneys(playerNamesDto.getPlayerNames());
        Players players = playerInfoDtos.stream()
            .map(PlayerInfoDto::toPlayer)
            .collect(collectingAndThen(toList(), Players::from));
    }
}
