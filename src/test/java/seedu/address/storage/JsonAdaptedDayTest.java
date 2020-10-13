package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedDay.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDays.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.day.Date;
import seedu.address.model.day.Weight;

public class JsonAdaptedDayTest {
    private static final String INVALID_DATE = "";
    private static final String INVALID_WEIGHT = "7-0";
    private static final String INVALID_TAG = "#friend";

    private static final List<JsonAdaptedInput> VALID_INPUT = new ArrayList<JsonAdaptedInput>();
    private static final List<JsonAdaptedOutput> VALID_OUTPUT = new ArrayList<JsonAdaptedOutput>();
    private static final String VALID_DATE = BENSON.getDate().toString();
    private static final String VALID_WEIGHT = BENSON.getWeight().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()

            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validDayDetails_returnsDay() throws Exception {
        JsonAdaptedDay day = new JsonAdaptedDay(BENSON);
        assertEquals(BENSON, day.toModelType());
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {

        JsonAdaptedDay day =
                new JsonAdaptedDay(INVALID_DATE, VALID_WEIGHT, VALID_TAGS, VALID_INPUT, VALID_OUTPUT);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, day::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedDay day = new JsonAdaptedDay(null, VALID_WEIGHT, VALID_TAGS, VALID_INPUT, VALID_OUTPUT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, day::toModelType);
    }

    @Test
    public void toModelType_invalidWeight_throwsIllegalValueException() {
        JsonAdaptedDay day =
                new JsonAdaptedDay(VALID_DATE, INVALID_WEIGHT, VALID_TAGS, VALID_INPUT, VALID_OUTPUT);
        String expectedMessage = Weight.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, day::toModelType);
    }

    @Test
    public void toModelType_nullWeight_throwsIllegalValueException() {
        JsonAdaptedDay day = new JsonAdaptedDay(VALID_DATE, null, VALID_TAGS, VALID_INPUT, VALID_OUTPUT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Weight.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, day::toModelType);
    }
    /*
    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedDay day =
                new JsonAdaptedDay(VALID_DATE, VALID_WEIGHT, VALID_TAGS, VALID_INPUT, VALID_OUTPUT);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, day::toModelType);
    }
    */
    /*
    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedDay day = new JsonAdaptedDay(VALID_DATE, VALID_WEIGHT, VALID_TAGS, VALID_INPUT, VALID_OUTPUT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, day::toModelType);
    }
    /*
     */
    /*
    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedDay day =
                new JsonAdaptedDay(VALID_DATE, VALID_WEIGHT, VALID_TAGS, VALID_INPUT, VALID_OUTPUT);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, day::toModelType);
    }
    */
    /*
    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedDay day = new JsonAdaptedDay(VALID_DATE, VALID_WEIGHT, VALID_TAGS, VALID_INPUT, VALID_OUTPUT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, day::toModelType);
    }
    */

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedDay day =
                new JsonAdaptedDay(VALID_DATE, VALID_WEIGHT, invalidTags, VALID_INPUT, VALID_OUTPUT);
        assertThrows(IllegalValueException.class, day::toModelType);
    }

}
