package blackJack.util;

import java.util.Arrays;
import java.util.List;

public class InputConvertor {

    public static List<String> convertPlayerNames(String rawPlayerNmaes) {
        return Arrays.asList(rawPlayerNmaes.split(","));
    }

    public static int convertBettingMoney(String rawBetiingMoeny) {
        return Integer.parseInt(rawBetiingMoeny);
    }
}
