package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.day.calorie.CalorieCount;
import seedu.address.model.day.calorie.Food;
import seedu.address.model.day.calorie.Input;
import seedu.address.model.day.calorie.Time;


/**
 * Jackson-friendly version of {@link seedu.address.model.day.calorie.Calorie}.
 */
class JsonAdaptedInput {

    private final String calorieCount;
    private final String time;
    private final String food;

    /**
     * Constructs a {@code JsonAdaptedDay} with the given day details.
     */
    @JsonCreator
    public JsonAdaptedInput(@JsonProperty("calorieCount") String calorieCount, @JsonProperty("time") String time,
                             @JsonProperty("food") String food) {
        this.calorieCount = calorieCount;
        this.time = time;
        this.food = food;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedInput(Input source) {
        calorieCount = source.getCalorieCount().calorieCount;
        time = source.getTime().time;
        food = source.getFood().food;

    }


    /**
     * Converts this Jackson-friendly adapted day object into the model's {@code Day} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted day.
     */
    public Input toModelType() throws IllegalValueException {

        final CalorieCount modelCalorieCount = new CalorieCount(calorieCount);
        final Time modelTime = new Time(time);
        final Food modelFood = new Food(food);
        return new Input(modelTime, modelFood, modelCalorieCount);
    }

}
