package seedu.address.model.day.calorie;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Input extends Calorie {

    private Food food;
    /**
     * Every field must be present and not null.
     */
    public Input(Time time, Food food, CalorieCount calorieCount) {
        super(calorieCount, time);
        requireAllNonNull(food, time, calorieCount);
        this.food = food;
    }

    public Food getFood() {
        return food;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Food: ")
                .append(getFood())
                .append(" Time: ")
                .append(getTime())
                .append(" Calorie Intake: ")
                .append(getCalorieCount());

        return builder.toString();
    }

    /**
     * Returns true if both Input have the same identity and data fields.
     * This defines a stronger notion of equality between two Input.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Input)) {
            return false;
        }

        Input otherInput = (Input) other;
        return otherInput.getCalorieCount().equals(getCalorieCount())
                && otherInput.getTime().equals(getTime())
                && otherInput.getFood().equals(getFood());
    }
}
