package blackJack.view;

import static blackJack.util.RetyrUtil.read;

import blackJack.domain.BettingMoney;
import blackJack.domain.PlayerWithCard;
import blackJack.dto.PlayerInfoDto;
import blackJack.dto.PlayerNamesDto;
import blackJack.util.InputConvertor;
import blackJack.util.InputValidator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String PLAYER_NAMES_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String BETTING_MONEY_INPUT_MESSAGE = "%s의 배팅 금액은?";
    private static InputView instance;

    public static InputView getInstance() {
        if (instance == null) {
            instance = new InputView();
        }
        return instance;
    }

    public static PlayerNamesDto scanPlayerNames() {
        Scanner sc = new Scanner(System.in);
        System.out.println(PLAYER_NAMES_INPUT_MESSAGE);
        String rawPlayerNmaes = sc.nextLine();
        InputValidator.validatePlayerNames(rawPlayerNmaes); // read 밖에 필요
        List<String> playerNames = InputConvertor.convertPlayerNames(rawPlayerNmaes);
        return new PlayerNamesDto(playerNames);
    }

    public static ExtraCard scanExtraCard(PlayerWithCard playerWithCard) {
        String playerName = playerWithCard.getPlayer().getPlayerName().getName();
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", playerName);
        Scanner sc = new Scanner(System.in);
        String rawExtraCard = sc.nextLine();
        InputValidator.validateExtraCard(rawExtraCard);
        ExtraCard extraCard = InputConvertor.convertExtraCard(rawExtraCard);
        return extraCard;
    }

    public List<PlayerInfoDto> scanBettingMoneys(List<String> playerNames) {
        return playerNames.stream()
            .map(playerName ->  new PlayerInfoDto(playerName, read(this::scanBettingMoneys, playerName)))
            .collect(Collectors.toList());
    }

    private BettingMoney scanBettingMoneys(String playerName) {
        Scanner sc = new Scanner(System.in);
        System.out.printf(BETTING_MONEY_INPUT_MESSAGE, playerName);
        String rawBetiingMoeny = sc.nextLine();
        InputValidator.validateBettingMoney(rawBetiingMoeny);
        int betiingMoeny = InputConvertor.convertBettingMoney(rawBetiingMoeny);
        return BettingMoney.from(betiingMoeny);
    }
}
