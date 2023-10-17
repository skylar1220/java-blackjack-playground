package blackJack.view;

import blackJack.domain.PlayerWithCard;
import blackJack.domain.PlayersWithCard;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String ERROR_MESSAGE_FORMAT = "[ERROR] ";

    public static void printExceptionMessage(String message) {
        System.out.println(ERROR_MESSAGE_FORMAT + message);
    }

    public static void printFirstSetting(PlayersWithCard playersWithCard, PlayerWithCard dealerWithCard) {
        List<String> nameList = playersWithCard.getPlayersWithCard().stream()
            .map(playerWithCard -> playerWithCard.getPlayer().getPlayerName().getName())
            .collect(Collectors.toList());
        String playersName = String.join(", ", nameList);
        System.out.printf("딜러와 %s에게 2장의 나누었습니다.", playersName);
        System.out.println();

        playersWithCard.addDealer(dealerWithCard);
        playersWithCard.getPlayersWithCard().forEach(playerWithCard -> {
                String playerName = playerWithCard.getPlayer().getPlayerName().getName();
                String playerCards = String.join(", ", playerWithCard.getCards().getCardsName());
                System.out.printf("%s카드: %s", playerName, playerCards);
                System.out.println();
            }

        );
    }
}
