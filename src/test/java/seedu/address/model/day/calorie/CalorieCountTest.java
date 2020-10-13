package seedu.address.model.day.calorie;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CalorieCountTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CalorieCount(null));
    }

    @Test
    public void constructor_invalidCalorieCount_throwsIllegalArgumentException() {
        String invalidCalorieCount = "";
        assertThrows(IllegalArgumentException.class, () -> new CalorieCount(invalidCalorieCount));
    }

    @Test
    public void isValidCalorieCount() {
        // null CalorieCount
        assertThrows(NullPointerException.class, () -> CalorieCount.isValidCalorieCount(null));

        // invalid CalorieCount
        assertFalse(CalorieCount.isValidCalorieCount("")); // empty string
        assertFalse(CalorieCount.isValidCalorieCount(" ")); // spaces only
        assertFalse(CalorieCount.isValidCalorieCount("123a")); // contains alphabet
        assertFalse(CalorieCount.isValidCalorieCount("123$")); // contains special character
        assertFalse(CalorieCount.isValidCalorieCount("abc$%^")); // contains both alphabet and special character

        // valid CalorieCount
        assertTrue(CalorieCount.isValidCalorieCount("123455")); // numbers only
    }

}
