package blackJack.domain;

public enum Suit {
    CLOVER("클로버"), SPADE("스페이드"), HEART("하트"), DIA("다이아");

    private final String form;

    Suit(String form) {
        this.form = form;
    }

    public String getForm() {
        return form;
    }
}
