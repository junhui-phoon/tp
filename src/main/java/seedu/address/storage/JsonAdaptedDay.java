package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.day.Date;
import seedu.address.model.day.Day;
import seedu.address.model.day.Weight;
import seedu.address.model.day.calorie.Input;
import seedu.address.model.day.calorie.Output;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Day}.
 */
class JsonAdaptedDay {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Day's %s field is missing!";

    private final String date;
    private final String weight;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedInput> inputList = new ArrayList<>();
    private final List<JsonAdaptedOutput> outputList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedDay} with the given day details.
     */
    @JsonCreator
    public JsonAdaptedDay(@JsonProperty("date") String date, @JsonProperty("weight") String weight,
                          @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                          @JsonProperty("inputList") List<JsonAdaptedInput> inputList,
                          @JsonProperty("outputList") List<JsonAdaptedOutput> outputList) {
        this.date = date;
        this.weight = weight;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (inputList != null) {
            this.inputList.addAll(inputList);
        }
        if (outputList != null) {
            this.outputList.addAll(outputList);
        }
    }

    /*
    @JsonCreator
    public JsonAdaptedDay(@JsonProperty("date") String date, @JsonProperty("weight") String weight,
                          @JsonProperty("email") String email, @JsonProperty("address") String address,
                          @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.date = date;
        this.weight = weight;
        this.email = email;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }
*/

    /**
     * Converts a given {@code Date} into this class for Jackson use.
     */
    public JsonAdaptedDay(Day source) {
        date = source.getDate().value;
        weight = source.getWeight().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        inputList.addAll(source.getCalorieInputList().stream()
                .map(JsonAdaptedInput::new)
                .collect(Collectors.toList()));
        outputList.addAll(source.getCalorieOutputList().stream()
                .map(JsonAdaptedOutput::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted day object into the model's {@code Day} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted day.
     */
    public Day toModelType() throws IllegalValueException {
        final List<Tag> dayTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            dayTags.add(tag.toModelType());
        }

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);

        if (weight == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Weight.class.getSimpleName()));
        }
        if (!Weight.isValidWeight(weight)) {
            throw new IllegalValueException(Weight.MESSAGE_CONSTRAINTS);
        }
        final Weight modelWeight = new Weight(weight);

        final List<Input> dayInputs = new ArrayList<>();
        for (JsonAdaptedInput input : inputList) {
            dayInputs.add(input.toModelType());
        }

        final List<Output> dayOutputs = new ArrayList<>();
        for (JsonAdaptedOutput output : outputList) {
            dayOutputs.add(output.toModelType());
        }

        final Set<Tag> modelTags = new HashSet<>(dayTags);
        return new Day(modelDate, modelWeight, modelTags);
    }

}
