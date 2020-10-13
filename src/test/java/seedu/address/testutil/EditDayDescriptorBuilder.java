package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand;
import seedu.address.model.day.Address;
import seedu.address.model.day.Date;
import seedu.address.model.day.Day;
import seedu.address.model.day.Email;
import seedu.address.model.day.Weight;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditDayDescriptor objects.
 */
public class EditDayDescriptorBuilder {

    private EditCommand.EditDayDescriptor descriptor;

    public EditDayDescriptorBuilder() {
        descriptor = new EditCommand.EditDayDescriptor();
    }

    public EditDayDescriptorBuilder(EditCommand.EditDayDescriptor descriptor) {
        this.descriptor = new EditCommand.EditDayDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditDayDescriptor} with fields containing {@code day}'s details
     */
    public EditDayDescriptorBuilder(Day day) {
        descriptor = new EditCommand.EditDayDescriptor();
        descriptor.setDate(day.getDate());
        descriptor.setWeight(day.getWeight());
        descriptor.setTags(day.getTags());
    }

    /**
     * Sets the {@code Date} of the {@code EditDayDescriptor} that we are building.
     */
    public EditDayDescriptorBuilder withDate(String date) {
        descriptor.setDate(new Date(date));
        return this;
    }

    /**
     * Sets the {@code Weight} of the {@code EditDayDescriptor} that we are building.
     */
    public EditDayDescriptorBuilder withWeight(String weight) {
        descriptor.setWeight(new Weight(weight));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditDayDescriptor} that we are building.
     */
    public EditDayDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditDayDescriptor} that we are building.
     */
    public EditDayDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditDayDescriptor}
     * that we are building.
     */
    public EditDayDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommand.EditDayDescriptor build() {
        return descriptor;
    }
}
