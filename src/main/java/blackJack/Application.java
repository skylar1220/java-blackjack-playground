package blackJack;


import blackJack.controller.GameController;
import blackJack.domain.CardGenerator;
import blackJack.domain.RandomCardGenerator;

public class Application {

    public static void main(String[] args) {
        CardGenerator cardGenerator = new RandomCardGenerator();
        GameController gameController = new GameController(cardGenerator);
        gameController.start();
    }
}
