package blackJack;

import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<Player> players) {
        return new Players(players);
    }
}
