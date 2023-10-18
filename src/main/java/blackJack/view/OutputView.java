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

    public static void printFirstSetting(PlayersWithCard playersWithCard,
        PlayerWithCard dealerWithCard) {
        List<String> nameList = playersWithCard.getPlayersWithCard().stream()
            .map(playerWithCard -> playerWithCard.getPlayer().getPlayerName().getName())
            .collect(Collectors.toList());
        String playersName = String.join(", ", nameList);
        System.out.printf("딜러와 %s에게 2장의 나누었습니다.", playersName);
        System.out.println();

        List<PlayerWithCard> allParticipant = playersWithCard.getAllParticipantWithCard(dealerWithCard);
        allParticipant.forEach(playerWithCard -> {
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

    public static void printPlayerCards(PlayerWithCard playerWithCard) {
        String playerName = playerWithCard.getPlayer().getPlayerName().getName();
        String playerCards = String.join(", ", playerWithCard.getCards().getCardsName());
        System.out.printf("%s카드: %s", playerName, playerCards);
        System.out.println();
    }

    public static void printDealerExtraCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResult(PlayersWithCard allParicipant) {
        allParicipant.getPlayersWithCard().forEach(playerWithCard -> {
                String playerName = playerWithCard.getPlayer().getPlayerName().getName();
                String playerCards = String.join(", ", playerWithCard.getCards().getCardsName());
                int cardRankSum = playerWithCard.getCards().getCardsSum();
                System.out.printf("%s카드: %s - 결과: %d", playerName, playerCards, cardRankSum);
                System.out.println();
            }
        );
    }

    public static void printBenefit(PlayersWithCard playersWithCard,
        PlayerWithCard dealerWithCard) {
        System.out.println("## 최종 수익");
        int dealerEarning = getDealerEarning(playersWithCard, dealerWithCard);
        System.out.printf("딜러: %d", dealerEarning);
        System.out.println();

        playersWithCard.getPlayersWithCard().forEach(playerWithCard -> {
                String playerName = playerWithCard.getPlayer().getPlayerName().getName();
                int playerEarning = playerWithCard.getPlayer().getBettingMoney().getBettingMoeny();
                System.out.printf("%s: %d", playerName, playerEarning);
                System.out.println();
            }
        );
    }

    private static int getDealerEarning(PlayersWithCard playersWithCard, PlayerWithCard dealerWithCard) {
        int playersEarningSum = playersWithCard.getPlayersWithCard().stream()
            .map(playerWithCard -> playerWithCard.getPlayer().getBettingMoney().getBettingMoeny())
            .reduce(0, Integer::sum);
        return -playersEarningSum;
    }
}
// 수익은 어떻게 세팅하느냐
        /*
         일단 이긴 사람이 있는 경우
        - 플레이어만win: ㅇ이긴 플레이어 +베팅머니 그대로 가져감 /  ㅇ안이긴 플레이어는 -베팅머니원 /
        ㅇ(출력에서!) 딜러는 앞의 +200 -300 = 결과에 -+를 반대로 붙여서 +100

        - 딜러만win: ㅇ플레이어: - 베팅머니 /
       ㅇ (출력에서!) 베팅머니 다 합친만큼 + /

        - 플레이어, 딜러 둘다 win :ㅇ 비긴플레이어 0, ㅇ안이긴플레이어 -베팅머니,
        ㅇ(출력에서!) 딜러는 앞의 베팅머니 결과합에 +-를 반대로 붙여서
        ---
        o 일단 블랙잭이었던 경우
        - ㅇ 플레이어만 win: 블랙잭플레이어는 +베팅*1.5 / 그냥 플레이어는 0 /
        ㅇ(출력에서!) 딜러는 -수익합

        - ㅇ 딜러만 win: 플레이이들: -베팅 /
        ㅇ(출력에서!) 딜러: -를 +로 수익합

        - ㅇ 플레이어, 딜러 둘 다: 다 0
       ㅇ(출력에서!) 딜러: -를 +로 수익합

         */
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