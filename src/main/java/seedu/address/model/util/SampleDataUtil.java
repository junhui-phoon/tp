package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.day.Date;
import seedu.address.model.day.Day;
import seedu.address.model.day.Weight;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Day[] getSampleDay() {
        return new Day[] {
            new Day(new Date("101320"), new Weight("80"), getTagSet("colleagues", "friends")),
            new Day(new Date("101420"), new Weight("45"),
                getTagSet("colleagues", "friends"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Day sampleDay : getSampleDay()) {
            sampleAb.addDay(sampleDay);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
