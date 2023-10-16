package blackJack;

import static blackJack.RetyrUtil.*;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.List;

public class GameController {
    InputView inputView = InputView.getInstance();

    public void start() {
        InputView.scanPlayerNames();
        Players players = read(this::generatePlayers);
        RandomCardGenerator randomCardGenerator = new RandomCardGenerator();
//         랜덤 카드 세팅 어떻게? 일단 CardGenerator 인터페이스 구현한 RandomCardGenerator 클래스에서 generate
//        Gmae or GameStatus에 openedCards 가 있어서 거기서 그걸 들고 갖고온 cardGenerator도 갖고 들어가서 받은 걸
//        Card로 받아서 그걸 Game 또는 GameStatus에서 플레이어별로 갖은 걸 출력해야할 것 같은데.


    }

    private Players generatePlayers() {
        PlayerNamesDto playerNamesDto = read(InputView::scanPlayerNames);
        List<PlayerInfoDto> playerInfoDtos = inputView.scanBettingMoneys(playerNamesDto.getPlayerNames());
        return playerInfoDtos.stream()
            .map(PlayerInfoDto::toPlayer)
            .collect(collectingAndThen(toList(), Players::from));
    }
}
