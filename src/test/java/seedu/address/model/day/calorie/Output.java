package seedu.address.model.day.calorie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class OutputTest {

    private final String invalidInput = "";
    private final Exercise exercise = new Exercise("Running");
    private final Time time = new Time("1200");
    private final CalorieCount calorieCount = new CalorieCount("400");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Output(null, null, null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Output(new Time(invalidInput), exercise, calorieCount));
    }

    @Test
    public void constructor_invalidFood_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Output(time, new Exercise(invalidInput), calorieCount));
    }

    @Test
    public void constructor_invalidCalorieCount_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Output(time, exercise, new CalorieCount(invalidInput)));
    }

    @Test
    void getFood() {
        Exercise validExercise = new Exercise("running");
        Output output = new Output(new Time("1200"), validExercise, new CalorieCount("200"));
        assertEquals(validExercise, output.getExercise());
    }

}
