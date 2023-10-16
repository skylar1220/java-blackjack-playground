package blackJack;

public class Player {

    private final PlayerName playName;
    private final BettingMoney bettingMoney;

    private Player(PlayerName playerName, BettingMoney bettingMoney) {
        this.playName = playerName;
        this.bettingMoney = bettingMoney;
    }

    public static Player from(String playerName, BettingMoney bettingMoney) {
        return new Player(new PlayerName(playerName), bettingMoney);
    }
}
