import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;

/**
 * A Calculator for adding number together
 */
public class Calculator {

    private static String NEWLINE = "\n";
    private static String CHANGE_DELIMITER_OPERATOR = "//";
    private static char DELIMITER_SEPERATOR = '|';
    private static String ADD_SEPERATOR = "+";
    private static String DEFAULT_DELIMITER = ",";

    /**
     * @param numbers number in a String
     * @return Sum of the number in a String numbers
     * @throws Exception Exceoption throw netgative numbers
     */
    public int add(String numbers) throws Exception {
        if (Strings.isNullOrEmpty(numbers))
            return 0;

        String[] delimiters = new String[]{DEFAULT_DELIMITER};
        if (numbers.startsWith(CHANGE_DELIMITER_OPERATOR)) {
            delimiters = getDelimiters(numbers);
            numbers = numbers.substring(numbers.indexOf(NEWLINE) + NEWLINE.length());
        }

        numbers = RegenerateNumberWithAddFormat(numbers, delimiters, ADD_SEPERATOR);

        String[] numArr = StringUtils.split(numbers, ADD_SEPERATOR);

        int sum = 0;
        StringBuilder errText = new StringBuilder();
        for (int i = 0; i < numArr.length; i++) {
            sum = getSum(sum, numArr[i], errText);
        }

        if (errText.length() > 0) {
            throw new Exception(errText.toString());
        }

        return sum;
    }


    /**
     * @param numbers numbers that going to regenerate
     * @param delimiters delimiter list
     * @param addOperator operator that will used to replace the delimiter
     * @return a new number String that replace with an add operator
     */
    private String RegenerateNumberWithAddFormat(String numbers, String[] delimiters, String addOperator) {
        for (int i = 0; i < delimiters.length; i++) {
            numbers = StringUtils.replace(numbers, delimiters[i], addOperator);
        }
        return numbers;
    }

    /**
     * @param numbers
     * @return Get all the valid delimiters
     */
    private String[] getDelimiters(String numbers) {
        String delimiter = numbers.substring(CHANGE_DELIMITER_OPERATOR.length(), numbers.indexOf(NEWLINE));
        String[] delimiters = StringUtils.split(delimiter, DELIMITER_SEPERATOR);
        return delimiters;
    }

    /**
     * @param sum
     * @param number
     * @param errText
     * @return Sum of the number in the String
     */
    private int getSum(int sum, String number, StringBuilder errText) {
        int valueAdd = 0;
        if (number.startsWith(NEWLINE)) {
            valueAdd += convertToInteger(StringUtils.replace(number, NEWLINE, ""), errText);
        } else if (number.contains(NEWLINE)) {
            String[] intermediate = number.split(NEWLINE);
            valueAdd += convertToInteger(intermediate[0], errText) + convertToInteger(intermediate[1], errText);
        } else {
            valueAdd += convertToInteger(number, errText);
        }
        sum += valueAdd;
        return sum;
    }

    /**
     * @param num
     * @param errText
     * @return Convert the String number to Integer With checking
     */
    private int convertToInteger(String num, StringBuilder errText) {
        int value = Integer.parseInt(num);
        if (value > 1000) {
            return 0;
        }

        if (value < 0) {
            if (errText.length() == 0) {
                errText.append("negatives not allowed:");
            }
            errText.append(" ").append(value);
            value = 0;
        }
        return value;
    }
}
