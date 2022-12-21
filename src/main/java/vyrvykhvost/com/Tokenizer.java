package vyrvykhvost.com;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Arithmetic expression tokenizer
 */
public class Tokenizer {

    private static final Character[] numbers = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.' };
    private static final Character[] operators = { '+', '-', '*', '/' };
    private static final Character[] brackets = { '(', ')' };

    public static List<String> brakeInTokens(String expression) {
        List<String> result = new ArrayList<>();
        StringBuilder number = new StringBuilder();
        boolean isCharOperator = true;
        for (char c : expression.toCharArray()) {
            if (Arrays.asList(numbers).contains(c) || (c == '-' && isCharOperator)) {
                number.append(c);
                isCharOperator = false;
            } else if (Arrays.asList(operators).contains(c) || Arrays.asList(brackets).contains(c)) {
                if (!number.isEmpty()) {
                    result.add(number.toString());
                    number.setLength(0);
                }
                result.add(String.valueOf(c));
                isCharOperator = true;
            } else if (c != ' ') {
                throw new IllegalArgumentException("invalid token character: " + c);
            }
        }
        if (!number.isEmpty()) {
            result.add(number.toString());
        }
        return result;
    }


    public static boolean chekcingCorrectBrakets(String expression) {
        int counter = 0;
        for (char c : expression.toCharArray()) {
            if (c == '(') {
                counter++;
            } else if (c == ')') {
                counter--;
            }
        }
        return counter == 0;
    }


    public static boolean checkingCorrectOperators(String expression) {
        boolean isCharOperaor = false;
        List<String> tokens = brakeInTokens(expression);
        for (String token : tokens) {
            if (token.length() == 1 && Arrays.asList(operators).contains(token.charAt(0))) {
                if (isCharOperaor) {
                    return false;
                }
                isCharOperaor = true;
            } else {
                isCharOperaor = false;
            }
        }
        return true;
    }


    public static boolean checkingValidChar(String expression) {
        Pattern pattern = Pattern.compile("^[\\d\\-.+/*() ]*[^\\d\\-.+/*() ]+.*$");
        Matcher matcher = pattern.matcher(expression);
        return !matcher.matches();
    }


    public static boolean isExpressionValid(String expression) {
        return checkingValidChar(expression) && chekcingCorrectBrakets(expression) && checkingCorrectOperators(expression);
    }
}
