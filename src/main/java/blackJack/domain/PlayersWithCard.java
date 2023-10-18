package blackJack.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlayersWithCard {

    private final List<PlayerWithCard> playersWithCard;

    public PlayersWithCard() {
        this.playersWithCard = new ArrayList<>();
    }

    public PlayersWithCard(List<PlayerWithCard> playersWithCard) {
        this.playersWithCard = playersWithCard;
    }

    public void add(PlayerWithCard playerWithCard) {
        playersWithCard.add(playerWithCard);
    }

    public List<PlayerWithCard> getPlayersWithCard() {
        return playersWithCard;
    }

    public void addDealer(PlayerWithCard dealerWithCard) {
        playersWithCard.add(0, dealerWithCard);
    }

    public int getPlayerBlackJackCount() {
//        winBlackJack(playersWithCard);
        return (int) playersWithCard.stream()
            .filter(PlayerWithCard::isBlackJack)
            .count();
    }

    private boolean hasBlackJack(List<PlayerWithCard> playersWithCard) {
        int blackJackCount = (int) playersWithCard.stream()
            .filter(PlayerWithCard::isBlackJack)
            .count();
        return blackJackCount > 0;
    }

    public boolean isGameOver() {
        int gameOverCount = (int) playersWithCard.stream()
            .filter(PlayerWithCard::isGameOver)
            .count();
        return gameOverCount > 0;
    }
//    public boolean isGameOver() {
//        int gameOverCount = (int) playersWithCard.stream()
//            .filter(PlayerWithCard::isGameOver)
//            .count();
//        return gameOverCount > 0;
//    }

    public void calculateEarning(PlayerWithCard dealerWithCard) {
        calculateBlackJack(dealerWithCard);
        calculateFinalWin(dealerWithCard);
    }

    // 여기 finalCheck 처럼 blackJack status에 따라 처리 간소화 됐는지 확인@
    private void calculateBlackJack(PlayerWithCard dealerWithCard) {
        List<PlayerWithCard> allParticipant = getAllParticipantWithCard(dealerWithCard);
        if (hasBlackJack(allParticipant)) {
            if (hasBlackJack(playersWithCard) && !dealerWithCard.isBlackJack()) {
                anyPlayerBlackJack();
            }
            if (!hasBlackJack(playersWithCard) && dealerWithCard.isBlackJack()) {
                playersLoss();
            }
            if (hasBlackJack(playersWithCard) && dealerWithCard.isBlackJack()) {
                allParticipant.forEach(PlayerWithCard::draw);
            }
        }
    }

    // 어차피 21 이하인 경우만 여기 온 거라서 제일 큰 수를 이긴 걸로 하면 될듯
    // 여기 중복 부분 메소드 분리 필요@
    private void calculateFinalWin(PlayerWithCard dealerWithCard) {
        List<PlayerWithCard> allParticipant = getAllParticipantWithCard(dealerWithCard);

        checkWinner(allParticipant);

        if (!isDraw(dealerWithCard)) {
            playersWithCard.forEach(playerWithCard -> {
                if (!playerWithCard.isWin()) {
                    playerWithCard.lossMoney();
                }
            });
        }
        if (isDraw(dealerWithCard)) {
            playersWithCard.forEach(playerWithCard -> {
                if (playerWithCard.isWin()) {
                    playerWithCard.draw();
                }
                if (!playerWithCard.isWin()) {
                    playerWithCard.lossMoney();
                }
            });
        }
    }

    private boolean isDraw(PlayerWithCard dealerWithCard) {
        return isAnyPlayersWin() && dealerWithCard.isWin();
    }

    // 수익은 어떻게 세팅하느냐
        /*
         일단 이긴 사람이 있는 경우
        - 플레이어만win: ㅇ이긴 플레이어 +베팅머니 그대로 가져감 /  ㅇ안이긴 플레이어는 -베팅머니원 /
        (출력에서!) 딜러는 앞의 +200 -300 = 결과에 -+를 반대로 붙여서 +100

        - 딜러만win: ㅇ플레이어: - 베팅머니 /
        (출력에서!) 베팅머니 다 합친만큼 + /

        - 플레이어, 딜러 둘다 win :ㅇ 비긴플레이어 0, ㅇ안이긴플레이어 -베팅머니,
        (출력에서!) 딜러는 앞의 베팅머니 결과합에 +-를 반대로 붙여서
        ---
        o 일단 블랙잭이었던 경우
        - ㅇ 플레이어만 win: 블랙잭플레이어는 +베팅*1.5 / 그냥 플레이어는 0 / (출력에서!) 딜러는 -수익합
        - ㅇ 딜러만 win: 플레이이들: -베팅 / (출력에서!) 딜러: -를 +로 수익합
        - ㅇ 플레이어, 딜러 둘 다: 다 0

         */
    private void playersLoss() {
        playersWithCard.forEach(PlayerWithCard::lossMoney);
    }

    private boolean isAnyPlayersWin() {
        int winPlayerCont = (int) playersWithCard.stream()
            .filter(PlayerWithCard::isWin)
            .count();
        return winPlayerCont > 0;
    }

    // @ 근데 결과를 위해 수익을 더해주는 건 ouput의 역할이 아니겠지?
    // 왜냐면 dealer는 수익을 ouput에서 계산해서...
    // 여기서도 사실 max cardsum을 구하는 과정에서 get이 한 번 들어가는 게 걸림
    private void checkWinner(List<PlayerWithCard> allParticipant) {
        // 스트림에서 all 돌면서 제일 큰 애한테 status win 주는 거지
        allParticipant.stream()
            .max(Comparator.comparing(playerWithCard -> playerWithCard.getCards().getCardsSum()))
            .ifPresent(PlayerWithCard::gameWin);
    }

    public List<PlayerWithCard> getAllParticipantWithCard(PlayerWithCard dealerWithCard) {
        List<PlayerWithCard> participantList = new ArrayList<>(playersWithCard);
        participantList.add(0, dealerWithCard);
        return participantList;
    }

    private void anyPlayerBlackJack() {
        playersWithCard.forEach(playerWithCard -> {
            if (playerWithCard.isBlackJack()) {
                playerWithCard.winBlackJack();
            }
            if (!playerWithCard.isBlackJack()) {
                playerWithCard.draw();
            }
        });
    }

    public static PlayersWithCard from(List<PlayerWithCard> playersWithCard){
        return new PlayersWithCard(playersWithCard);
    }

//    private void dealerWin(PlayerWithCard dealerWithCard) {
////        int bettingMoneySum = playersWithCard. // get 써야하는데 ouput에서 할까
//        dealerWithCard.dealerWinGame();
//    }


}
