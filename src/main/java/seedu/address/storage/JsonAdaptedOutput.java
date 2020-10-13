package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.day.calorie.CalorieCount;
import seedu.address.model.day.calorie.Exercise;
import seedu.address.model.day.calorie.Output;
import seedu.address.model.day.calorie.Time;


/**
 * Jackson-friendly version of {@link seedu.address.model.day.calorie.Calorie}.
 */
class JsonAdaptedOutput {

    private final String calorieCount;
    private final String time;
    private final String exercise;

    /**
     * Constructs a {@code JsonAdaptedDay} with the given day details.
     */
    @JsonCreator
    public JsonAdaptedOutput(@JsonProperty("calorieCount") String calorieCount, @JsonProperty("time") String time,
                             @JsonProperty("exercise") String exercise) {
        this.calorieCount = calorieCount;
        this.time = time;
        this.exercise = exercise;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedOutput(Output source) {
        calorieCount = source.getCalorieCount().calorieCount;
        time = source.getTime().time;
        exercise = source.getExercise().exercise;

    }


    /**
     * Converts this Jackson-friendly adapted day object into the model's {@code Day} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted day.
     */
    public Output toModelType() throws IllegalValueException {

        final CalorieCount modelCalorieCount = new CalorieCount(calorieCount);
        final Time modelTime = new Time(time);
        final Exercise modelExercise = new Exercise(exercise);
        return new Output(modelTime, modelExercise, modelCalorieCount);
    }

}
