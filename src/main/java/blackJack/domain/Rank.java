package blackJack.domain;

public enum Rank {
    A(1), TWO(2), THREE(3),
    FOUR(4), FIVE(5), SIX(6),
    SEVEN(7), EIGHT(8), NINE(9),
    K(11), Q(11), J(11);

    private final int number;

    Rank(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
