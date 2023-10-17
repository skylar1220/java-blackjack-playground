package blackJack.domain;

public class PlayerWithCard {

    private final Player player;
    private final Cards cards;
    private Status status;

    public PlayerWithCard(Player player) {
        this.player = player;
        this.cards = Cards.from();
    }

    public static PlayerWithCard from(Player player) {
        return new PlayerWithCard(player);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public Cards getCards() {
        return cards;
    }

    public Player getPlayer() {
        return player;
    }

    public void checkBlackJack() {
        if (cards.isBlackJack()) {
            status = Status.BLACKJACK;
            System.out.println(player.getPlayerName().getName() + status.name());
        }
    }
}
