package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.day.Day;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Day.
 */
public class DayUtil {

    /**
     * Returns an add command string for adding the {@code day}.
     */
    public static String getAddCommand(Day day) {
        return AddCommand.COMMAND_WORD + " " + getDayDetails(day);
    }

    /**
     * Returns the part of command string for the given {@code day}'s details.
     */
    public static String getDayDetails(Day day) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_DATE + day.getDate().value + " ");
        sb.append(PREFIX_WEIGHT + day.getWeight().value + " ");
        day.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditDayDescriptor}'s details.
     */
    public static String getEditDayDescriptorDetails(EditCommand.EditDayDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getDate().ifPresent(date -> sb.append(PREFIX_DATE).append(date.value).append(" "));
        descriptor.getWeight().ifPresent(weight -> sb.append(PREFIX_WEIGHT).append(weight.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
