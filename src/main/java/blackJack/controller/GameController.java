package blackJack.controller;

import static blackJack.util.RetyrUtil.*;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import blackJack.domain.CardGenerator;
import blackJack.view.InputView;
import blackJack.view.OutputView;
import blackJack.dto.PlayerInfoDto;
import blackJack.dto.PlayerNamesDto;
import blackJack.domain.Players;
import blackJack.domain.PlayersWithCard;
import blackJack.domain.RandomCardGenerator;
import java.util.List;

public class GameController {

    private final CardGenerator cardGenerator;
    InputView inputView = InputView.getInstance();

    public GameController(CardGenerator cardGenerator) {
        this.cardGenerator = cardGenerator;
    }

    public void start() {
        InputView.scanPlayerNames();
        Players players = read(this::generatePlayers);
        PlayersWithCard playersWithCard = players.firstCardSetting(cardGenerator);
        OutputView.printFirstSetting(playersWithCard);
//        playersWithCard.checkBalckJack();


/*
- 딜러 객체 만들기
기능: 똑같이 이름 객체 필드로 주고 dealer로 불러올지 / 아님 그 필드 없이 할지
 */

//         랜덤 카드 세팅 어떻게? 일단 CardGenerator 인터페이스 구현한 RandomCardGenerator 클래스에서 generate
//        Gmae or GameStatus에 openedCards 가 있어서 거기서 그걸 들고 갖고온 cardGenerator도 갖고 들어가서 받은 걸
//        Card로 받아서 그걸 Game 또는 GameStatus에서 플레이어별로 갖은 걸 출력해야할 것 같은데.
//
//        아래의 것을 어떻게, 어디서 만들거냐
//        Players 객체를 stream해서 만들어야지
//
//         어디서 받을거냐, 그리고 어떻게 플레이어랑 연결할거냐
//       o 이름은 Cards: List<card>
//       o 뽑혀서 판에 내놓을 카드 (+ 플레이어) 가 합쳐진 객체
//       o 턴 당 add 돼서 업데이트 되기도 함
//       o playerCards.add(card, card2)
//        List<Cards> playersCards를 갖고 출력 또는 전에 하나씩할때 출력
//        plyersCards 갖고 처음 블랙잭 검증
//
//        이제 반복할 부분 작성
//

    }

    private void firstCardSetting(Players players, RandomCardGenerator randomCardGenerator) {
    }

    private Players generatePlayers() {
        PlayerNamesDto playerNamesDto = read(InputView::scanPlayerNames);
        List<PlayerInfoDto> playerInfoDtos = inputView.scanBettingMoneys(
            playerNamesDto.getPlayerNames());
        return playerInfoDtos.stream()
            .map(PlayerInfoDto::toPlayer)
            .collect(collectingAndThen(toList(), Players::from));
    }
}
