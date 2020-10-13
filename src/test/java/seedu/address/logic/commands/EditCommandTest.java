package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showDayAtIndex;
import static seedu.address.testutil.TypicalDays.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_DAY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_DAY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditDayDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.day.Day;
import seedu.address.testutil.DayBuilder;
import seedu.address.testutil.EditDayDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Day editedDay = new DayBuilder().build();
        EditCommand.EditDayDescriptor descriptor = new EditDayDescriptorBuilder(editedDay).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_DAY, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_DAY_SUCCESS, editedDay);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setDay(model.getFilteredDayList().get(0), editedDay);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }
    /*
    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredDayList().size());
        Day lastDay = model.getFilteredDayList().get(indexLastPerson.getZeroBased());

        DayBuilder personInList = new DayBuilder(lastDay);
        Day editedDay = personInList.withDate(VALID_DATE_2).withWeight(VALID_WEIGHT_2)
                .withTags(VALID_TAG_HUSBAND).build();

        EditCommand.EditDayDescriptor descriptor = new EditDayDescriptorBuilder().withDate(VALID_DATE_2)
                .withWeight(VALID_WEIGHT_2).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_DAY_SUCCESS, editedDay);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setDay(lastDay, editedDay);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }
    */
    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_DAY, new EditCommand.EditDayDescriptor());
        Day editedDay = model.getFilteredDayList().get(INDEX_FIRST_DAY.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_DAY_SUCCESS, editedDay);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }
    /*
    @Test
    public void execute_filteredList_success() {
        showDayAtIndex(model, INDEX_FIRST_DAY);

        Day dayInFilteredList = model.getFilteredDayList().get(INDEX_FIRST_DAY.getZeroBased());
        Day editedDay = new DayBuilder(dayInFilteredList).withDate(VALID_DATE_2).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_DAY,
                new EditDayDescriptorBuilder().withDate(VALID_DATE_2).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_DAY_SUCCESS, editedDay);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setDay(model.getFilteredDayList().get(0), editedDay);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }
    */
    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Day firstDay = model.getFilteredDayList().get(INDEX_FIRST_DAY.getZeroBased());
        EditDayDescriptor descriptor = new EditDayDescriptorBuilder(firstDay).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_DAY, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_DAY);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showDayAtIndex(model, INDEX_FIRST_DAY);

        // edit day in filtered list into a duplicate in address book
        Day dayInList = model.getAddressBook().getDayList().get(INDEX_SECOND_DAY.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_DAY,
                new EditDayDescriptorBuilder(dayInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_DAY);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDayList().size() + 1);
        EditDayDescriptor descriptor = new EditDayDescriptorBuilder().withDate(VALID_DATE_2).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_DAY_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showDayAtIndex(model, INDEX_FIRST_DAY);
        Index outOfBoundIndex = INDEX_SECOND_DAY;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getDayList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditDayDescriptorBuilder().withDate(VALID_DATE_2).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_DAY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_DAY, DESC_1);

        // same values -> returns true
        EditCommand.EditDayDescriptor copyDescriptor = new EditCommand.EditDayDescriptor(DESC_1);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_DAY, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_DAY, DESC_1)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_DAY, DESC_2)));
    }

}
