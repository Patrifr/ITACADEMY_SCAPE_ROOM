package utils;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import java.io.ByteArrayInputStream;


public class HelperTest {

    public static ByteArrayInputStream testIn;

    @BeforeAll
    public static void replaceSystemIn() {
        testIn = new ByteArrayInputStream("42,15\nyes\nMary\n\n42\n".getBytes());
        System.setIn(testIn);
    }

    @AfterAll
    public static void restoreSystemIn() {
        System.setIn(System.in);
    }

    @Test
    @DisplayName("Checking that readDouble(String message) returns a double.")
    public void testReadDouble() {
        String message = "Introduce a number with decimals:";
        double result = Helper.readDouble(message);
        assertThat(result).isExactlyInstanceOf(java.lang.Double.class);
    }

    @Test
    @DisplayName("Checking that readBoolean(String message) returns a boolean.")
    public void testReadBoolean() {
        String message = "Introduce 'yes' or 'no':";
        boolean result = Helper.readBoolean(message);
        assertThat(result).isExactlyInstanceOf(java.lang.Boolean.class);
    }

    @Test
    @DisplayName("Checking that readString(String message) returns a string.")
    public void testReadString() {
        String message = "Introduce your name:";
        String result = Helper.readString(message);
        assertThat(result).isExactlyInstanceOf(java.lang.String.class);
    }

    @Test
    @DisplayName("Checking that readInt(String message) returns an integer.")
    public void testReadInt() {
        String message = "Introduce a number:";
        int result = Helper.readInt(message);
        assertThat(result).isExactlyInstanceOf(java.lang.Integer.class);
    }

}
