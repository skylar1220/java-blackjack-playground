package blackJack.domain;

public class Player {

    private final PlayerName playerName;
    private final BettingMoney bettingMoney;

    private Player(PlayerName playerName, BettingMoney bettingMoney) {
        this.playerName = playerName;
        this.bettingMoney = bettingMoney;
    }

    public static Player from(String playerName, BettingMoney bettingMoney) {
        return new Player(new PlayerName(playerName), bettingMoney);
    }

    public PlayerWithCard playerCardFirstSetting(CardGenerator cardGenerator) {
        PlayerWithCard playerWithCard = PlayerWithCard.from(this);
        Card card1 = cardGenerator.generate();
        Card card2 = cardGenerator.generate();
        playerWithCard.add(card1);
        playerWithCard.add(card2);
        return playerWithCard;
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public static Player fromTest(String playerName, int bettingMoney) {
        return new Player(new PlayerName(playerName), BettingMoney.from(bettingMoney));
    }
}
