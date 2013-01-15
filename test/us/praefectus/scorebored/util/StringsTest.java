package us.praefectus.scorebored.util;

import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

public class StringsTest {
    
    public StringsTest() {
    }

    @Test
    public void test_isEmpty_Null() {
        assertTrue(Strings.isEmpty(null));
    }
    
    @Test
    public void test_isEmpty_Whitespace() {
        assertTrue(Strings.isEmpty(" \t\n"));
    }
    
    @Test
    public void test_isEmpty_NotEmpty() {
        assertFalse(Strings.isEmpty(" \tx\n"));
    }
    
    @Test
    public void test_join() { 
        assertEquals("One, Two, Three", Strings.join(", ", "One", "Two", 
                "Three"));
    }
    
    @Test
    public void test_join_singleValue() {
        assertEquals("One", Strings.join(", ", "One"));
    }
    
    @Test(expected=NullPointerException.class)
    public void test_join_nullDelimiter() {
        Strings.join(null, "One", "Two");
    }
    
    @Test
    public void test_join_nullValue() {
        assertEquals("One, null, Three", Strings.join(", ", "One", null, 
                "Three"));
    }
    
    @Test
    public void test_joinArray() { 
        assertEquals("One, Two, Three", Strings.join(", ", 
                new Object[] { "One", "Two", "Three" } ));
    }
    
    @Test
    public void test_joinArray_singleValue() {
        assertEquals("One", Strings.join(", ", new Object[] { "One" }));
    }
    
    @Test(expected=NullPointerException.class)
    public void test_joinArray_nullDelimiter() {
        Strings.join(null, new Object[] { "One", "Two" });
    }
    
    @Test
    public void test_joinArray_nullValue() {
        assertEquals("One, null, Three", Strings.join(", ", 
                new Object[] { "One", null, "Three" }));
    }
    
    @Test
    public void test_wordWrap() {
        String text = "1aa 2aa 3aa 4aa 5aa 6aa 7aa";
        String wrap = Strings.wrapText(12, text);
        assertEquals("1aa 2aa 3aa\n4aa 5aa 6aa\n7aa", wrap);
    }
}
