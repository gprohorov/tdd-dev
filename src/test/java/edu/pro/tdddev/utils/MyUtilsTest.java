package edu.pro.tdddev.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/*
  @author   george
  @project   tdd-dev
  @class  MyUtilsTest
  @version  1.0.0 
  @since 25.03.23 - 22.17
*/

class MyUtilsTest {

    @Test
   void itShouldNotValidateNumberLessThan3Digits(){
        String phone = "00";
        assertFalse(MyUtils.isValidPhoneNumber(phone));
    }

    @Test
   void itShouldNotValidateNumberGraterThan10Digits(){
        String phone = "012345678900"; // 12 digits
        assertFalse(MyUtils.isValidPhoneNumber(phone));
    }

    @Test
   void itShouldValidateNumberWithBrackets(){
        String phone = "(050)5451414";
        assertTrue(MyUtils.isValidPhoneNumber(phone));
    }

    @Test
   void itShouldValidateNumberWithSpaces(){
        String phone = "050 5451414";
        assertTrue(MyUtils.isValidPhoneNumber(phone));
    }




    @ParameterizedTest
    @ValueSource(strings = {
          "(050)5451414", // round brackets
          "0505451414"    // happy path
    })
    void itShouldValidateNumbers(String phone){
        assertTrue(MyUtils.isValidPhoneNumber(phone));

    }



}
/*
    ------------- valid cases  -------------
    0505451414
    050-545-14-14
    050 545 14 14
    050 5451414
    050-5451414
    (050)5451414
    (050) 5451414
    +8(050)5451414
    +80505451414
    +8050 5451414
    +8 050 5451414
    +8-050-5451414
    +8-050-545-14-14

    -------- Not Valid
    050545141A
    #0505451414
    050_545_14_14
    UA 050 5451414
    +8{050}5451414



 */
