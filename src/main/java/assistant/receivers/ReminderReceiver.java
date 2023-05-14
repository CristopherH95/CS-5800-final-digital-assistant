package assistant.receivers;

import assistant.exceptions.CommandReceiverException;
import assistant.interfaces.CommandReceiver;
import assistant.records.ReminderRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReminderReceiver implements CommandReceiver {
    // Matches strings like: set reminder to turn off oven triggering in 2 hours
    private static final Pattern reminderPattern = Pattern.compile(
        "(?:set\\s+reminder\\s+)(?<name>.*?)(?:triggering\\s+in)\\s+(?<time>\\d+)\\s+(?<units>hours?|minutes?)",
        Pattern.CASE_INSENSITIVE
    );

    @Override
    public void invoke(String input) {
        ReminderRequest reminderRequest = parseReminderRequest(input);
        System.out.printf(
            "Setting reminder '%s' in %d %s",
            reminderRequest.name(),
            reminderRequest.time(),
            reminderRequest.units()
        );
    }

    @Override
    public Pattern getInputPattern() {
        return reminderPattern;
    }

    private ReminderRequest parseReminderRequest(String input) {
        Matcher reminderMatcher = reminderPattern.matcher(input);
        if (!reminderMatcher.find()) {
            throw new CommandReceiverException(String.format("Failed to set reminder for input '%s'", input));
        }

        String name = reminderMatcher.group("name");
        int time = Integer.parseInt(reminderMatcher.group("time"));
        String units = reminderMatcher.group("units");

        return new ReminderRequest(
            name,
            time,
            units.toLowerCase()
        );
    }
}
