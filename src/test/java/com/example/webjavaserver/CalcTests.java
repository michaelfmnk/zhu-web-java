package com.example.webjavaserver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class CalcTests {

    @Test
    public void shouldAddNumbers() {
        int a = 4;
        int b = 8;

        int result = Calc.add(a, b);

        Assertions.assertEquals(12, result);
    }


    @Test
    public void shouldDivideNumbers() {
        int a = 8;
        int b = 4;

        int result = Calc.divide(a, b);

        Assertions.assertEquals(2, result);
    }


    @Test
    @Disabled("To be discussed with management")
    public void shouldDivideNumbers_Zero() {
        int a = 8;
        int b = 0;

        Assertions.assertThrows(
                ArithmeticException.class,
                () -> Calc.divide(a, b)
        );
    }

    @Test
    public void shouldDivideNumberOnZeroAndNotFail() {
        int a = 8;
        int b = 0;

        int result = Calc.divide(a, b);

        Assertions.assertEquals(Integer.MAX_VALUE -100, result);
    }

}
