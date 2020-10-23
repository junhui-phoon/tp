package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDays.getTypicalMyFitnessBuddy;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.MyFitnessBuddy;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyMyFitnessBuddy_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyMyFitnessBuddy_success() {
        Model model = new ModelManager(getTypicalMyFitnessBuddy(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalMyFitnessBuddy(), new UserPrefs());
        expectedModel.setMyFitnessBuddy(new MyFitnessBuddy());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
