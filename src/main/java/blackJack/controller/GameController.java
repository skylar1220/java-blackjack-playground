package blackJack.controller;

import static blackJack.util.RetyrUtil.*;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import blackJack.domain.BettingMoney;
import blackJack.domain.CardGenerator;
import blackJack.domain.Player;
import blackJack.domain.PlayerWithCard;
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
        Player dealer = Player.from("딜러 ", BettingMoney.from(1));

        PlayersWithCard playersWithCard = players.firstCardSetting(cardGenerator);
        PlayerWithCard dealerWithCard = dealer.playerCardFirstSetting(cardGenerator);
        OutputView.printFirstSetting(playersWithCard, dealerWithCard);
        playersWithCard.checkBalckJack();
/*
- 블랙잭 검증하기

-
 */

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
