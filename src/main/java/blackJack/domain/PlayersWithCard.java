package blackJack.domain;

import java.util.ArrayList;
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
        return blackJackCount >0;
    }

    public boolean isGameOver() {
        int gameOverCount = (int) playersWithCard.stream()
            .filter(PlayerWithCard::isGameOver)
            .count();
        return gameOverCount > 0;
    }

    public void calculateEarning(PlayerWithCard dealerWithCard) {
        List<PlayerWithCard> allParticipant = getAllParticipantWithCard(dealerWithCard);
        if (hasBlackJack(allParticipant)) {
            if (hasBlackJack(playersWithCard)&& !dealerWithCard.isBlackJack()) {
                anyPlayerBlackJack();
            }
            if (!hasBlackJack(playersWithCard) && dealerWithCard.isBlackJack()) {
                playersLoss();
            }
            if (hasBlackJack(playersWithCard)&& dealerWithCard.isBlackJack()) {
                allParticipant.forEach(PlayerWithCard::notBlackJack);
            }
        }

        // 아무도 블랙잭 아니고 결과로 온 경우

    }


    // 수익은 어떻게 세팅하느냐
        /*
         일단 이긴 사람이 있는 경우
        - 플레이어: +베팅머니 /  안이긴 플레이어는 -베팅머니원 / 딜러는 앞의 +200 -300 = 결과에 -+를 반대로 붙여서 +100
        - 딜러: 베팅머니 다 합친만큼 + / 플레이어: - 베팅머니
        - 플레이어, 딜러 둘다 : 비긴플레이어 0, 안이긴플레이어 -베팅머니, 딜러는 앞의 베팅머니 결과합에 +-를 반대로 붙여서
        ---
        o 일단 블랙잭이었던 경우
        - ㅇ 플레이어만: 블랙잭플레이어는 +베팅*1.5 / 그냥 플레이어는 0 / (출력에서!) 딜러는 -수익합
        - ㅇ 딜러만: 플레이이들: -베팅 / (출력에서!) 딜러: -를 +로 수익합
        - ㅇ 플레이어, 딜러 둘 다: 다 0

         */
    private void playersLoss() {
        playersWithCard.forEach(PlayerWithCard::lossGame);
    }
    private List<PlayerWithCard> getAllParticipantWithCard(PlayerWithCard dealerWithCard) {
        List<PlayerWithCard> participantList = new ArrayList<>();
        participantList.add(0, dealerWithCard);
        return participantList;
    }

    private void anyPlayerBlackJack() {
        playersWithCard.forEach(playerWithCard -> {
            if (playerWithCard.isBlackJack()) {
                playerWithCard.winBlackJack();
            }
            if (!playerWithCard.isBlackJack()){
                playerWithCard.notBlackJack();
            }
        });
    }

//    public static PlayersWithCard from(List<PlayerWithCard> playersWithCard){
//        return new PlayersWithCard(playersWithCard);
//    }

//    private void dealerWin(PlayerWithCard dealerWithCard) {
////        int bettingMoneySum = playersWithCard. // get 써야하는데 ouput에서 할까
//        dealerWithCard.dealerWinGame();
//    }


}
