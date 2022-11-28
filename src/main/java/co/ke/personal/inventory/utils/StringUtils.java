package co.ke.personal.inventory.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class StringUtils {
    public static String generateRefId(){
       return  RandomStringUtils.random(16, true, true);
    }
    public static Boolean isNullOrEmpty(String str){
        return str.isEmpty();
    }
}
