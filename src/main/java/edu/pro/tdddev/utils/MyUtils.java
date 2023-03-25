package edu.pro.tdddev.utils;
/*
  @author   george
  @project   tdd-dev
  @class  MyUtils
  @version  1.0.0 
  @since 19.03.23 - 23.58
*/

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyUtils {
    public static String toJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

    public static boolean isValidPhoneNumber(String phone){

        phone = phone.replace("(", "")
                .replace(")", "");

        if(phone.length() < 3) {
            return false;
        }

        if(phone.length() > 10) {
            return false;
        }

        return true;
    }


}
