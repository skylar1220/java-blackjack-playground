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



    /*
    - 블랙잭 검증하기
--plyaerㄴWithCard 돌면서 isBlackJack이 트루인 PlayerWithCard만 필터해서
- PlyaerWithCard.player.winBlackJack(bettingmoney +1.5배 시켜줌)
.count
     */
}
