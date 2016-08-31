package jo.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        String regExp = "^\\d+$";
        boolean isInteger = rawValue.matches(regExp);

        if (isInteger) {
            newValue = Integer.parseInt(rawValue);
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

}