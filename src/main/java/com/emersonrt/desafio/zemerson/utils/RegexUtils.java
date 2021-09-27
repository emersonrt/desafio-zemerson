package com.emersonrt.desafio.zemerson.utils;

/**
 *
 * @author emerson
 */

public class RegexUtils {

    public static Boolean isValidLongitude(String longitude) {
        String regex = "\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$";
        return longitude.matches(regex);
    }
    
    public static Boolean isValidLatitude(String latitude) {
        String regex = "^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?)";
        return latitude.matches(regex);
    }

}
