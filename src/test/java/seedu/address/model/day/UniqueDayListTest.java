package seedu.address.model.day;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDays.ALICE;
import static seedu.address.testutil.TypicalDays.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.day.exceptions.DayNotFoundException;
import seedu.address.model.day.exceptions.DuplicateDayException;

public class UniqueDayListTest {

    private final UniqueDayList uniqueDayList = new UniqueDayList();

    @Test
    public void contains_nullDay_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDayList.contains(null));
    }

    @Test
    public void contains_dayNotInList_returnsFalse() {
        assertFalse(uniqueDayList.contains(ALICE));
    }

    @Test
    public void contains_dayInList_returnsTrue() {
        uniqueDayList.add(ALICE);
        assertTrue(uniqueDayList.contains(ALICE));
    }

    /*
    @Test
    public void contains_dayWithSameIdentityFieldsInList_returnsTrue() {
        uniqueDayList.add(ALICE);
        Day editedAlice = new DayBuilder(ALICE).withAddress(VALID_ADDRESS_2).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueDayList.contains(editedAlice));
    }
     */

    @Test
    public void add_nullDay_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDayList.add(null));
    }
    /*
    @Test
    public void add_duplicateDay_throwsDuplicateDayException() {
        uniqueDayList.add(ALICE);
        assertThrows(DuplicateDayException.class, () -> uniqueDayList.add(ALICE));
    }
    */

    @Test
    public void setDay_nullTargetDay_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDayList.setDay(null, ALICE));
    }

    @Test
    public void setDay_nullEditedDay_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDayList.setDay(ALICE, null));
    }

    @Test
    public void setDay_targetDayNotInList_throwsDayNotFoundException() {
        assertThrows(DayNotFoundException.class, () -> uniqueDayList.setDay(ALICE, ALICE));
    }

    @Test
    public void setDay_editedDayIsSameDay_success() {
        uniqueDayList.add(ALICE);
        uniqueDayList.setDay(ALICE, ALICE);
        UniqueDayList expectedUniqueDayList = new UniqueDayList();
        expectedUniqueDayList.add(ALICE);
        assertEquals(expectedUniqueDayList, uniqueDayList);
    }

    /*
    @Test
    public void setDay_editedDayHasSameIdentity_success() {
        uniqueDayList.add(ALICE);
        Day editedAlice = new DayBuilder(ALICE).withAddress(VALID_ADDRESS_2).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueDayList.setDay(ALICE, editedAlice);
        UniqueDayList expectedUniqueDayList = new UniqueDayList();
        expectedUniqueDayList.add(editedAlice);
        assertEquals(expectedUniqueDayList, uniqueDayList);
    }
     */

    @Test
    public void setDay_editedDayHasDifferentIdentity_success() {
        uniqueDayList.add(ALICE);
        uniqueDayList.setDay(ALICE, BOB);
        UniqueDayList expectedUniqueDayList = new UniqueDayList();
        expectedUniqueDayList.add(BOB);
        assertEquals(expectedUniqueDayList, uniqueDayList);
    }
    /*
    @Test
    public void setDay_editedDayHasNonUniqueIdentity_throwsDuplicateDayException() {
        uniqueDayList.add(ALICE);
        uniqueDayList.add(BOB);
        assertThrows(DuplicateDayException.class, () -> uniqueDayList.setDay(ALICE, BOB));
    }
    */
    @Test
    public void remove_nullDay_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDayList.remove(null));
    }

    @Test
    public void remove_dayDoesNotExist_throwsDayNotFoundException() {
        assertThrows(DayNotFoundException.class, () -> uniqueDayList.remove(ALICE));
    }

    @Test
    public void remove_existingDay_removesDay() {
        uniqueDayList.add(ALICE);
        uniqueDayList.remove(ALICE);
        UniqueDayList expectedUniqueDayList = new UniqueDayList();
        assertEquals(expectedUniqueDayList, uniqueDayList);
    }

    @Test
    public void setDays_nullUniqueDayList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDayList.setDays((UniqueDayList) null));
    }

    @Test
    public void setDays_uniqueDayList_replacesOwnListWithProvidedUniqueDayList() {
        uniqueDayList.add(ALICE);
        UniqueDayList expectedUniqueDayList = new UniqueDayList();
        expectedUniqueDayList.add(BOB);
        uniqueDayList.setDays(expectedUniqueDayList);
        assertEquals(expectedUniqueDayList, uniqueDayList);
    }

    @Test
    public void setDays_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDayList.setDays((List<Day>) null));
    }

    @Test
    public void setDays_list_replacesOwnListWithProvidedList() {
        uniqueDayList.add(ALICE);
        List<Day> dayList = Collections.singletonList(BOB);
        uniqueDayList.setDays(dayList);
        UniqueDayList expectedUniqueDayList = new UniqueDayList();
        expectedUniqueDayList.add(BOB);
        assertEquals(expectedUniqueDayList, uniqueDayList);
    }

    @Test
    public void setDays_listWithDuplicateDays_throwsDuplicateDayException() {
        List<Day> listWithDuplicateDays = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateDayException.class, () -> uniqueDayList.setDays(listWithDuplicateDays));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueDayList.asUnmodifiableObservableList().remove(0));
    }
}
