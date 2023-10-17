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

    public static void printDealerOnlyBlackJack(PlayersWithCard playersWithCard) {
        int playersBettingMoney = playersWithCard.getPlayersWithCard().stream()
            .map(playerWithCard -> playerWithCard.getPlayer().getBettingMoney().getBettingMoeny())
            .reduce(0, Integer::sum);
    }

    public static void printPlayerBlackJack(PlayersWithCard players) {
    }

    public static void printAllBlackJack(PlayersWithCard players) {
    }

    public static void printFinalBenefit(PlayersWithCard playersWithCard,
        PlayerWithCard dealerWithCard) {
        int playerBlackJackCount = playersWithCard.getPlayerBlackJackCount();

        if (playerBlackJackCount > 0 && !dealerWithCard.isBlackJack()) {
            printPlayerBlackJack(playersWithCard);
        }
        if (playerBlackJackCount == 0 && dealerWithCard.isBlackJack()) {
            printDealerOnlyBlackJack(playersWithCard);
        }
        if (playerBlackJackCount > 0 && dealerWithCard.isBlackJack()) {
            printAllBlackJack(playersWithCard);
        }
    }
}

/*
- 블랙잭 검증하기
--plyaerㄴWithCard 돌면서 isBlackJack이 트루인 PlayerWithCard만 필터해서
- PlyaerWithCard.player.winBlackJack(bettingmoney +1.5배 시켜줌)
.count
--앞에서 나온 카운드가 0이면서 dealerWithCard에서 isBlackJack이 트루면
players 돌면서 betting 금액 더해서 int dealerBettingMoney에 넣어줌
-- 앞에서 나온 카운트가 0이 아닌데, 딜러가 블랙잭이면
모든 플레이어의 수익을 0으로 출력하게 print에 얘기하면 됨
player
 */