package blackJack.domain;

import java.util.Arrays;
import java.util.List;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<Player> players) {
        return new Players(players);
    }

    public static Players fromTest(Player... players) {
        return new Players(Arrays.asList(players));
    }

    public PlayersWithCard firstCardSetting(CardGenerator cardGenerator) {
        PlayersWithCard playersWithCard = new PlayersWithCard();
        for (Player player : players) {
            PlayerWithCard playerWithCard = player.playerCardFirstSetting(cardGenerator);
            playersWithCard.add(playerWithCard);
        }
        return playersWithCard;
    }

    public List<Player> getPlayers() {
        return players;
    }

}
