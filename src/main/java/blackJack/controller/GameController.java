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
        // 블랙잭이면 이 뒤부분 안 가게 해야하는데, 일단 출력때 계산하기 위해서 1.5배는 안함

        // 여기 정리 필요
        for (PlayerWithCard playerWithCard : playersWithCard.getPlayersWithCard()) {
            ExtraCard extraCard = null;
            do {
                extraCard = InputView.scanExtraCard(playerWithCard);
                if (extraCard == ExtraCard.YES) {
                    playerWithCard.extraCard(cardGenerator);
                }
                if (extraCard == ExtraCard.NO) {
                    OutputView.printPlayerCards(playerWithCard);
                }
                if (playerWithCard.isGameOver()) {
                    OutputView.printPlayerCards(playerWithCard);
                    break; // 루프를 빠져나옴
                }
            } while (extraCard == ExtraCard.YES);
        }

        // 딜러 차례
        // 16이하일 때와 gameOver일 때까지 extra 카드 받기 / 플레이어 중 gameOver가 아니면
        while (dealerWithCard.needExtraCard() || !dealerWithCard.isGameOver() || !playersWithCard.isGameOver()) {
            dealerWithCard.extraCard(cardGenerator);
            OutputView.printDealerExtraCard();
        }

        playersWithCard.calculateEarning(dealerWithCard);

        OutputView.printResult(playersWithCard, dealerWithCard);
        OutputView.printBenefit(playersWithCard, dealerWithCard);


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
