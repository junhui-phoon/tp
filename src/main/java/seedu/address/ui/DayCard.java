package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.day.Day;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class DayCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Day day;

    @FXML
    private HBox cardPane;
    @FXML
    private Label date;
    @FXML
    private Label id;
    @FXML
    private Label weight;
    @FXML
    private Label totalCalorieIn;
    @FXML
    private Label totalCalorieOut;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public DayCard(Day day, int displayedIndex) {
        super(FXML);
        this.day = day;
        id.setText(displayedIndex + ". ");
        date.setText("Date: " + day.getDate().value);
        weight.setText("Weight: " + day.getWeight().value + " kg");
        totalCalorieIn.setText("Total Calories In: " + day.getTotalInputCalorie() + " calories");
        totalCalorieOut.setText("Total Calories Out: " + day.getTotalOutputCalorie() + " calories");
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DayCard)) {
            return false;
        }

        // state check
        DayCard card = (DayCard) other;
        return id.getText().equals(card.id.getText())
                && day.equals(card.day);
    }
}
