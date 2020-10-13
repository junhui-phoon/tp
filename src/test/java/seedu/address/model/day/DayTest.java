package seedu.address.model.day;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEIGHT_2;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDays.ALICE;
import static seedu.address.testutil.TypicalDays.BOB;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.day.calorie.CalorieCount;
import seedu.address.model.day.calorie.Exercise;
import seedu.address.model.day.calorie.Food;
import seedu.address.model.day.calorie.Input;
import seedu.address.model.day.calorie.Output;
import seedu.address.model.day.calorie.Time;
import seedu.address.testutil.DayBuilder;

public class DayTest {

    public static final Time TIME = new Time("1200");
    public static final Exercise EXERCISE = new Exercise("run");
    public static final Food FOOD = new Food("Laksa");
    public static final CalorieCount CALORIE_COUNT = new CalorieCount("300");
    public static final Input INPUT = new Input(TIME, FOOD, CALORIE_COUNT);
    public static final Output OUTPUT = new Output(TIME, EXERCISE, CALORIE_COUNT);

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Day day = new DayBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> day.getTags().remove(0));
    }

    @Test
    public void isSameDay() {
        // same object -> returns true
        assertTrue(ALICE.isSameDay(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameDay(null));

        // different weight and email -> returns false
        Day editedAlice = new DayBuilder(ALICE).withWeight(VALID_WEIGHT_2).build();
        //assertFalse(ALICE.isSameDay(editedAlice));

        // different date -> returns false
        editedAlice = new DayBuilder(ALICE).withDate(VALID_DATE_2).build();
        assertFalse(ALICE.isSameDay(editedAlice));

        // same date, same weight, different attributes -> returns true
        editedAlice = new DayBuilder(ALICE)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameDay(editedAlice));

        // same date, same email, different attributes -> returns true
        editedAlice = new DayBuilder(ALICE).withWeight(VALID_WEIGHT_2)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameDay(editedAlice));

        // same date, same weight, same email, different attributes -> returns true
        editedAlice = new DayBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameDay(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Day aliceCopy = new DayBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different day -> returns false
        assertFalse(ALICE.equals(BOB));

        // different date -> returns false
        Day editedAlice = new DayBuilder(ALICE).withDate(VALID_DATE_2).build();
        assertFalse(ALICE.equals(editedAlice));

        // different weight -> returns false
        editedAlice = new DayBuilder(ALICE).withWeight(VALID_WEIGHT_2).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new DayBuilder(ALICE).build();
        //assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new DayBuilder(ALICE).build();
        // assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new DayBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    void addTotalCalorieOut() {
        Day day = new DayBuilder().build();
        day.addTotalCalorieOut(CALORIE_COUNT);
        day.addTotalCalorieOut(CALORIE_COUNT);
        assertEquals(day.getTotalOutputCalorie(), 600);
    }

    @Test
    void getCalorieOutputList() {
        Day day = new DayBuilder().build();

        assertEquals(day.getCalorieOutputList(), new ArrayList<Output>());
        assertTrue(day.getCalorieOutputList().isEmpty());

        day.addCalorieOutput(OUTPUT);
        assertFalse(day.getCalorieOutputList().isEmpty());
    }

    @Test
    void addCalorieOutput() {
        Day day = new DayBuilder().build();
        day.addCalorieOutput(OUTPUT);
        day.addCalorieOutput(OUTPUT);
        assertEquals(day.getTotalOutputCalorie(), 600);
        assertFalse(day.getCalorieOutputList().isEmpty());
    }

    @Test
    void getTotalOutputCalorie() {
        Day day = new DayBuilder().build();
        day.addCalorieOutput(OUTPUT);
        day.addCalorieOutput(OUTPUT);
        assertEquals(day.getTotalOutputCalorie(), 600);
    }

    @Test
    void getCalorieInputList() {
        Day day = new DayBuilder().build();
        assertEquals(day.getCalorieInputList(), new ArrayList<Input>());
        assertTrue(day.getCalorieInputList().isEmpty());

        day.addCalorieInput(INPUT);
        assertFalse(day.getCalorieInputList().isEmpty());
    }

    @Test
    void addTotalCalorieInput() {
        Day day = new DayBuilder().build();
        day.addTotalCalorieInput(CALORIE_COUNT);
        day.addTotalCalorieInput(CALORIE_COUNT);
        assertEquals(day.getTotalInputCalorie(), 600);
    }

    @Test
    void addCalorieInput() {
        Day day = new DayBuilder().build();
        day.addCalorieInput(INPUT);
        day.addCalorieInput(INPUT);
        assertEquals(day.getTotalInputCalorie(), 600);
        assertFalse(day.getCalorieInputList().isEmpty());
    }
}
