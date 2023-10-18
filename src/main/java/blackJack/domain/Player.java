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
        for (int i = 0; i < 2; i++) {
            Card card = cardGenerator.generate();
            playerWithCard.add(card);
        }
        return playerWithCard;
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public static Player fromTest(String playerName, int bettingMoney) {
        return new Player(new PlayerName(playerName), BettingMoney.from(bettingMoney));
    }

    public PlayerWithCard playerCardFirstSettingTest(Card card1, Card card2) {
        PlayerWithCard playerWithCard = PlayerWithCard.from(this);
        playerWithCard.add(card1);
        playerWithCard.add(card2);
        return playerWithCard;
    }


    public void winBlackJack() {
        bettingMoney.winBlackJack();
    }

    public void draw() {
        bettingMoney.draw();
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

    public void lossBettingMoney() {
        bettingMoney.lossMoney();
    }
}
