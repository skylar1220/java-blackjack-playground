package blackJack;

import java.util.ArrayList;
import java.util.List;

public interface CardGenerator {
//    List<Card> openedCards = new ArrayList<>();
    Card generate(NumberGenerator randomNumberGenerator);

}
