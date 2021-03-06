package jo.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;

/**
 *
 * @author JoostMeulenkamp
 */
public class General {

    /**
     * @param value the double to check
     * @return the number of decimal places of a double.
     */
    public static int getNumberOfDecimalPlaces(Double value) {
        String str = value + "";
        return str.length() - str.indexOf('.') - 1;
    }

    /**
     * @param rawValue the string to check
     * @return a double when the string is a valid number, otherwise null.
     */
    public static Double getDoubleValue(String rawValue) {
        Double newValue = null;
        //http://stackoverflow.com/questions/3133770/how-to-find-out-if-the-value-contained-in-a-string-is-double-or-not
        String regExp = "[\\x00-\\x20]*[+-]?(((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*";
        boolean isDouble = rawValue.matches(regExp);

        if (isDouble) {
            newValue = Double.parseDouble(rawValue);
        }
        return newValue;
    }

    /**
     * @param rawValue the string to check
     * @return an integer when the string is a valid number, otherwise null.
     */
    public static Integer getIntegerValue(String rawValue) {
        Integer newValue = null;
        //http://stackoverflow.com/questions/16331423/whats-the-java-regular-expression-for-an-only-integer-numbers-string
//        String regExp = "^\\d+$";
        String regExp = "^[\\d+]{1,10}$";
        boolean isInteger = rawValue.matches(regExp);

        if (isInteger) {
            newValue = Integer.parseInt(rawValue);
        }
        return newValue;
    }

    /**
     * @param rawValue the string to check
     * @return a long when the string is a valid number, otherwise null.
     */
    public static Long getLongValue(String rawValue) {
        Long newValue = null;

        //http://stackoverflow.com/questions/16331423/whats-the-java-regular-expression-for-an-only-integer-numbers-string
        String regExp = "^(\\d+){1,19}$";
        boolean isLong = rawValue.matches(regExp);

        if (isLong) {
            newValue = Long.parseLong(rawValue);
        }
        return newValue;
    }

    /**
     * @param rawValue the string to check
     * @return a boolean when the string is a valid boolean, otherwise null.
     */
    public static Boolean getBooleanValue(String rawValue) {
        Boolean newValue = null;

        if (rawValue.toLowerCase().equals("true")) {
            newValue = true;
        } else if (rawValue.toLowerCase().equals("false")) {
            newValue = false;
        }

        return newValue;
    }

    /**
     * Format a double property by another double property, e.g. a number likely
     * set by the user.
     *
     * @param value the double property
     * @param format the formatted double property
     * @return a formatted double binding
     */
    public static DoubleBinding getFormattedDoubleBinding(DoubleProperty value, DoubleProperty format) {
        DoubleBinding formattedValue = new DoubleBinding() {

            {
                super.bind(value, format);
            }

            @Override
            protected double computeValue() {
                int places = getNumberOfDecimalPlaces(format.get());
                Double newValue = new BigDecimal(value.get()).setScale(places, RoundingMode.HALF_UP).doubleValue();
                return newValue;
            }
        };
        return formattedValue;
    }

    /**
     * @param value the integer value to format
     * @param length the length of the output string
     * @return the formatted integer value e.g. 0001
     */
    public static String getIntegerValueWithLeadingZeros(int value, int length) {

        String newValue = value + "";
        int numOfZeros = length - newValue.length();

        if (numOfZeros < 0) {
            throw new IndexOutOfBoundsException("Integer value " + value + " exceeds requested length of " + length + " digits");
        }

        while (newValue.length() < length) {
            newValue = "0" + newValue;
        }

        return newValue;
    }

    /**
     * Get the absolute path to the directory of the .jar
     *
     * @param any object within the project
     * @return the parent directory of the .jar
     */
    public static String getPathOfJAR(Object any) {
        return getPathOfJAR(any.getClass());
    }

    /**
     * Get the absolute path to the directory of the .jar
     *
     * @param any class within the project
     * @return the parent directory of the .jar
     */
    public static String getPathOfJAR(Class any) {
        String jarPath = any.getProtectionDomain().getCodeSource().getLocation().getPath();
        return jarPath.substring(0, jarPath.lastIndexOf('/') + 1);
    }

    /**
     * Create a new dynamically typed list. <br>
     * https://stackoverflow.com/questions/15697775/dynamic-initialization-of-arraylistanyclassobject
     *
     * @param <T>
     * @param type
     * @return
     */
    public static <T> List<T> getList(Class<T> type) {
        List<T> arrayList = new ArrayList<>();
        return arrayList;
    }
}
