package blackJack.domain;

import java.util.ArrayList;
import java.util.List;

public class PlayersWithCard {

    private final List<PlayerWithCard> playersWithCard;

    public PlayersWithCard() {
        this.playersWithCard = new ArrayList<>();
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
        winBlackJack(playersWithCard);
        return (int) playersWithCard.stream()
            .filter(PlayerWithCard::isBlackJack)
            .count();
    }

    private static void winBlackJack(List<PlayerWithCard> playersWithCard) {
        playersWithCard.stream()
            .filter(PlayerWithCard::isBlackJack)
            .forEach(PlayerWithCard::winBlackJack);
    }

    public boolean isGameOver() {
        int gameOverCount = (int) playersWithCard.stream()
            .filter(PlayerWithCard::isGameOver)
            .count();
        return gameOverCount > 0;
    }

    public void calculateEarning(PlayerWithCard dealerWithCard) {
    }


    // 수익은 어떻게 세팅하느냐
        /*
        그동안 베팅머니가
        블랙잭이면 1.5배 됐을 거고 / 딜러는
        일단 이긴 사람이 있는 경우
        - 플레이어: +베팅머니 /  안이긴 플레이어는 -베팅머니원 / 딜러는 앞의 +200 -300 = 결과에 -+를 반대로 붙여서 +100
        - 딜러: 베팅머니 다 합친만큼 + / 플레이어: - 베팅머니
        - 플레이어, 딜러 둘다 : 비긴플레이어 0, 안이긴플레이어 -베팅머니, 딜러는 앞의 베팅머니 결과합에 +-를 반대로 붙여서
         */


}
