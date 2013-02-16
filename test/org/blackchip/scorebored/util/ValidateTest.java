package org.blackchip.scorebored.util;

import org.blackchip.scorebored.util.Validate;
import org.blackchip.scorebored.util.ValidationException;
import org.junit.*;
import static org.junit.Assert.*;

public class ValidateTest {
    
    public ValidateTest() {
    }

    @Test
    public void test_isInteger_True() throws ValidationException {
        assertEquals(2, Validate.isInteger("2", "error"));
    }
    
    @Test(expected=ValidationException.class)
    public void test_isInteger_False() throws ValidationException {
        Validate.isInteger("2i", "error");
    }
    
    @Test(expected=NullPointerException.class)
    public void test_isInteger_Null() throws ValidationException {
        Validate.isInteger(null, "error");
    }
    
    @Test
    public void test_notNegative_True() throws ValidationException {
        assertEquals(2, (int)Validate.notNegative(2, "error"));
    }

    @Test(expected=ValidationException.class)
    public void test_notNegative_False() throws ValidationException {
        Validate.notNegative(-2, "error");
    }
    
    @Test(expected=NullPointerException.class)
    public void test_notNegative_Null() throws ValidationException {
        Validate.notNegative(null, "error");
    }
    
    public void test_notEmpty_True() throws ValidationException {
        assertEquals("foo", Validate.notEmpty("foo", "error"));
    }
    
    @Test(expected=ValidationException.class)
    public void test_notEmpty_False() throws ValidationException {
        Validate.notEmpty("\t", "error");
    }

    @Test(expected=ValidationException.class)
    public void test_notEmpty_Null() throws ValidationException {
        Validate.notEmpty(null, "error");
    }
}
