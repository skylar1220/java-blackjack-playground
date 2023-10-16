package blackJack;

public class OutputView {

    private static final String ERROR_MESSAGE_FORMAT = "[ERROR] ";

    public static void printExceptionMessage(String message) {
        System.out.println(ERROR_MESSAGE_FORMAT + message);
    }
}
