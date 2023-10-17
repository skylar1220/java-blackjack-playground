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
}
