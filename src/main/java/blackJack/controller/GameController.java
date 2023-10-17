package blackJack.controller;

import static blackJack.util.RetyrUtil.*;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import blackJack.domain.BettingMoney;
import blackJack.domain.Card;
import blackJack.domain.CardGenerator;
import blackJack.domain.Player;
import blackJack.domain.PlayerWithCard;
import blackJack.view.ExtraCard;
import blackJack.view.InputView;
import blackJack.view.OutputView;
import blackJack.dto.PlayerInfoDto;
import blackJack.dto.PlayerNamesDto;
import blackJack.domain.Players;
import blackJack.domain.PlayersWithCard;
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
        Player dealer = Player.from("딜러 ", BettingMoney.from(1));

        PlayersWithCard playersWithCard = players.firstCardSetting(cardGenerator);
        PlayerWithCard dealerWithCard = dealer.playerCardFirstSetting(cardGenerator);
        OutputView.printFirstSetting(playersWithCard, dealerWithCard);

        // checkBlackJack - print_BlackJack 부분 중복 제거해야할듯
        int playerBlackJackCount = playersWithCard.getPlayerBlackJackCount();
        if (isBlackJack(playerBlackJackCount, dealerWithCard)) {
            OutputView.printFinalBenefit(playersWithCard, dealerWithCard);
        }

        playersWithCard.getPlayersWithCard().forEach(playerWithCard -> {
            ExtraCard extraCard = null;
            do {
                extraCard = InputView.scanExtraCard(playerWithCard);
                if (extraCard == ExtraCard.YES){
                    playerWithCard.extraCard(cardGenerator);
                }
            } while (extraCard == ExtraCard.YES);
//            OutputView.printPlayerCards(playersWithCard);
//            playersWithCard.checkGameOver();
        });
// extraCard가 N가 될 때까지 read를 하고 싶은데
//       ㅇ read 안에서는 scan하고 playersWithCard에 randomCard 1장 추가하는 게 일어나야함
//        N가 돼서 read 빠져나오면 printCards 하고
//        gameOver 체크


    }

    private static boolean isBlackJack(int playerBlackJackCount, PlayerWithCard dealerWithCard) {
        return playerBlackJackCount > 0 || dealerWithCard.isBlackJack();
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
