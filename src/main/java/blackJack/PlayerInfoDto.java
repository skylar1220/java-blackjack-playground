package blackJack;

public class PlayerInfoDto {

    private final BettingMoney bettingMoney;
    private final String playerName;

    public PlayerInfoDto(String playerName, BettingMoney bettingMoney) {
        this.playerName = playerName;
        this.bettingMoney = bettingMoney;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

    public String getPlayerName() {
        return playerName;
    }
}
