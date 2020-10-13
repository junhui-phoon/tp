package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DAYS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.day.Day;
import seedu.address.model.day.calorie.Calorie;
import seedu.address.model.day.calorie.Input;
import seedu.address.model.day.calorie.Output;


public class CalorieCommand extends Command {

    public static final String COMMAND_WORD = "calorie";
    private Calorie calorie;
    private String type;

    /**
     * Creates an CalorieCommand to add the specified {@code Calorie}
     */
    public CalorieCommand(Calorie calorie, String type) {
        this.calorie = calorie;
        this.type = type;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (model.hasDay()) {
            Day editDay = model.getDay();
            if (type.equals("in")) {
                editDay.addCalorieInput((Input) calorie);
            } else {
                editDay.addCalorieOutput((Output) calorie);
            }
            model.setDay(model.getDay(), editDay);
            model.updateFilteredDayList(PREDICATE_SHOW_ALL_DAYS);
        } else {
            throw new CommandException("please add a new entry for today before adding calorie input/output");
        }
        return new CommandResult(calorie.toString());
    }
}
