package seedu.address.model.day.calorie;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FoodTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Food(null));
    }

    @Test
    public void constructor_invalidFood_throwsIllegalArgumentException() {
        String invalidFood = "";
        assertThrows(IllegalArgumentException.class, () -> new Exercise(invalidFood));
    }

    @Test
    public void isValidFood() {
        // null Food
        assertThrows(NullPointerException.class, () -> Food.isValidFood(null));

        // invalid Food
        assertFalse(Food.isValidFood("")); // empty string
        assertFalse(Food.isValidFood(" ")); // spaces only

        // valid Food
        assertTrue(Food.isValidFood("laksa")); // alphabets only
        assertTrue(Food.isValidFood("laksa@nus")); // contains special character
        assertTrue(Food.isValidFood("5 bowls of laksa")); // contains number and space
        assertTrue(Food.isValidFood("5 bowls of laksa @ nus")); //contains number, alphabet and special char
    }
}
