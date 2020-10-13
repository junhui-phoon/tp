package seedu.address.model.day.calorie;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Time {
    public static final String MESSAGE_CONSTRAINTS =
            "Time should be in the format of HHMM and it should not be blank";

    public static final String VALIDATION_REGEX = "^[0-9]{4}$";

    public final String time;

    /**
     * Constructs a {@code Time}.
     *
     * @param time A valid Time.
     */
    public Time(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        this.time = time;
    }

    /**
     * Returns if a given string is a valid Time.
     */
    public static boolean isValidTime(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            int hour = Integer.parseInt(test.substring(0, 2));
            int min = Integer.parseInt(test.substring(2, 4));
            return hour <= 23 && min <= 59;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return time;
    }
}
