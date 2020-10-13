package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDays.ALICE;
import static seedu.address.testutil.TypicalDays.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.day.Day;
import seedu.address.model.day.exceptions.DuplicateDayException;
import seedu.address.testutil.DayBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getDayList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateDays_throwsDuplicateDayException() {
        // Two days with the same identity fields
        Day editedAlice = new DayBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Day> newDays = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newDays);

        assertThrows(DuplicateDayException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasDay_nullDay_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasDay(null));
    }

    @Test
    public void hasDay_dayNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasDay(ALICE));
    }

    @Test
    public void hasDay_dayInAddressBook_returnsTrue() {
        addressBook.addDay(ALICE);
        assertTrue(addressBook.hasDay(ALICE));
    }

    @Test
    public void hasDay_dayWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addDay(ALICE);
        Day editedAlice = new DayBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasDay(editedAlice));
    }

    @Test
    public void getDayList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getDayList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose days list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Day> days = FXCollections.observableArrayList();

        AddressBookStub(Collection<Day> days) {
            this.days.setAll(days);
        }

        @Override
        public ObservableList<Day> getDayList() {
            return days;
        }
    }

}
