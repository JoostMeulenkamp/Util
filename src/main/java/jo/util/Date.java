/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jo.util;

import java.util.HashMap;
import java.util.Map;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author joost.meulenkamp
 */
public class Date {

    public static final ObservableList<String> DATE_FORMAT = observableArrayList(
            "dd.MM.yyyy",
            "YYYY.MM.dd",
            "EE dd.MM.yyyy HH:mm Z",
            "yyyyMMdd",
            "dd-MM-yyyy",
            "yyyy-MM-dd",
            "MM/dd/yyyy",
            "yyyy/MM/dd",
            "dd MMM yyyy",
            "dd MMMM yyyy",
            "yyyyMMddHHmm",
            "yyyyMMdd HHmm",
            "dd-MM-yyyy HH:mm",
            "yyyy-MM-dd HH:mm",
            "MM/dd/yyyy HH:mm",
            "yyyy/MM/dd HH:mm",
            "dd MMM yyyy HH:mm",
            "dd MMMM yyyy HH:mm",
            "yyyyMMddHHmmss",
            "yyyyMMdd HHmmss",
            "dd-MM-yyyy HH:mm:ss",
            "yyyy-MM-dd HH:mm:ss",
            "MM/dd/yyyy HH:mm:ss",
            "yyyy/MM/dd HH:mm:ss",
            "dd MMM yyyy HH:mm:ss",
            "dd MMMM yyyy HH:mm:ss"
    );

    public static final Map<String, String> DATE_REGEX = new HashMap<String, String>() {
        { //http://balusc.omnifaces.org/2007/09/dateutil.html
            put("^\\d{1,2}\\.\\d{1,2}\\.\\d{4}$", "dd.MM.yyyy"); //01.04.2016
            put("^\\d{4}\\.\\d{1,2}\\.\\d{1,2}$", "YYYY.MM.dd"); //01.04.2016
            put("^[a-zA-Z]{2}\\s\\d{1,2}.\\d{1,2}.\\d{4}\\s\\d{1,2}:\\d{2}\\s[+|-]\\d{4}$", "EE dd.MM.yyyy HH:mm Z"); //Mi 06.07.2016 09:47 +0200
            put("^\\d{8}$", "yyyyMMdd");
            put("^\\d{1,2}-\\d{1,2}-\\d{4}$", "dd-MM-yyyy");
            put("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd");
            put("^\\d{1,2}/\\d{1,2}/\\d{4}$", "MM/dd/yyyy");
            put("^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd");
            put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}$", "dd MMM yyyy");
            put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}$", "dd MMMM yyyy");
            put("^\\d{12}$", "yyyyMMddHHmm");
            put("^\\d{8}\\s\\d{4}$", "yyyyMMdd HHmm");
            put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}$", "dd-MM-yyyy HH:mm");
            put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy-MM-dd HH:mm");
            put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}$", "MM/dd/yyyy HH:mm");
            put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy/MM/dd HH:mm");
            put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMM yyyy HH:mm");
            put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMMM yyyy HH:mm");
            put("^\\d{14}$", "yyyyMMddHHmmss");
            put("^\\d{8}\\s\\d{6}$", "yyyyMMdd HHmmss");
            put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd-MM-yyyy HH:mm:ss");
            put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy-MM-dd HH:mm:ss");
            put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "MM/dd/yyyy HH:mm:ss");
            put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy/MM/dd HH:mm:ss");
            put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMM yyyy HH:mm:ss");
            put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMMM yyyy HH:mm:ss");
        }
    };

    /**
     * Determine SimpleDateFormat pattern matching with the given date string.
     * Returns null if format is unknown. You can simply extend DateUtil with
     * more formats if needed.
     *
     * @param dateString The date string to determine the SimpleDateFormat
     * pattern for.
     * @return The matching SimpleDateFormat pattern, or null if format is
     * unknown.
     * @see SimpleDateFormat
     */
    public static String getDateFormat(String dateString) {
        for (String regexp : DATE_REGEX.keySet()) {
            if (dateString.toLowerCase().matches(regexp)) {
                return DATE_REGEX.get(regexp);
            }
        }
        return null; // Unknown format.
    } //http://balusc.omnifaces.org/2007/09/dateutil.html

}
