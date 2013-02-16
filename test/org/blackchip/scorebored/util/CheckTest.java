package org.blackchip.scorebored.util;

import org.blackchip.scorebored.util.Check;
import org.junit.*;
import static org.junit.Assert.*;

public class CheckTest {
    
    public CheckTest() {
    }

    @Test
    public void test_notNull_isNotNull() {
        assertEquals("foo", Check.notNull("foo"));
    }
    
    @Test(expected=NullPointerException.class)
    public void test_notNull_isNull() { 
        Check.notNull(null);
    }
    
    @Test
    public void test_notEmpty_true() {
        assertEquals("foo", Check.notEmpty("foo"));
    }
    
    @Test(expected=NullPointerException.class)
    public void test_notEmpty_null() {
        Check.notEmpty(null);
    }
    
    @Test(expected=IllegalArgumentException.class) 
    public void test_notEmpty_empty() {
        Check.notEmpty(" \t\n");
    }
    
    @Test
    public void test_notNegative_true() {
        assertEquals(2, (int)Check.notNegative(2));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void test_notNegative_false() {
        Check.notNegative(-2);
    }
    
}
