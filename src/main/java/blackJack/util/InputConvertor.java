package blackJack.util;

import blackJack.view.ExtraCard;
import java.util.Arrays;
import java.util.List;

public class InputConvertor {

    public static List<String> convertPlayerNames(String rawPlayerNmaes) {
        return Arrays.asList(rawPlayerNmaes.split(","));
    }

    public static int convertBettingMoney(String rawBetiingMoeny) {
        return Integer.parseInt(rawBetiingMoeny);
    }

    public static ExtraCard convertExtraCard(String rawExtraCard) {
        if (rawExtraCard.equals("y")) {
            return ExtraCard.YES;
        }
        if (rawExtraCard.equals("n")) {
            return ExtraCard.NO;
        }
        return null; // null 처리
    }
}
