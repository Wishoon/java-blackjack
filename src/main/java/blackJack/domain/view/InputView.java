package blackJack.domain.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String NEWLINE = System.getProperty("line.separator");
    private static final String INPUT_MESSAGE_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_DELIMITER_PLAYER_NAMES = ",";

    public static List<String> inputPlayerNames() {
        System.out.println(INPUT_MESSAGE_PLAYER_NAMES);
        return splitInputPlayerNames(scanner.nextLine());
    }

    private static List<String> splitInputPlayerNames(String input) {
        return Arrays.stream(input.split(INPUT_DELIMITER_PLAYER_NAMES))
            .map(String::trim)
            .collect(Collectors.toList());
    }
}
