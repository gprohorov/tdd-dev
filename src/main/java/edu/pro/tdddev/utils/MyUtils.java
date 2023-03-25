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
import org.springframework.stereotype.Service;

public class MyUtils {

    public static String toJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

    public static boolean validatePhoneNumber(String phone){
        if(phone.length() < 3) {
            return false;
        }

        return true;
    }
}
