package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidateEmailTest {

    @Test
    @DisplayName("Checking email validation with a valid address.")
    public void testValidateEmailTrue() {
        String validEmail = "username@domain.com";
        assertTrue(Helper.validateEmail(validEmail));
    }

    @Test
    @DisplayName("Checking email validation with an invalid address.")
    public void testValidateEmailTrueFalse() {
        String invalidEmail = "invalid-email";
        assertFalse(Helper.validateEmail(invalidEmail));
    }
}
