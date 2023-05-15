package assistant.commands;

import assistant.exceptions.CommandException;
import assistant.receivers.ReminderReceiver;
import assistant.records.ReminderRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReminderCommand extends CommandTemplate {
    // Matches strings like: set reminder turn off oven triggering in 2 hours
    private static final Pattern reminderPattern = Pattern.compile(
        "(?:set\\s+reminder\\s+)(?<name>.*?)(?:triggering\\s+in)\\s+(?<time>\\d+)\\s+(?<units>hours?|minutes?)",
        Pattern.CASE_INSENSITIVE
    );
    ReminderReceiver receiver;

    public ReminderCommand(String input) {
        super(input);
        receiver = ReminderReceiver.getInstance();
    }

    @Override
    protected boolean validateInput(String input) {
        Matcher matcher = reminderPattern.matcher(input);
        return matcher.matches();
    }

    @Override
    protected void notifyUser(String input) {
        System.out.println(String.format("Executing ReminderCommand with input [%s]", input));
    }

    @Override
    protected void invokeReceiver(String input) {
        ReminderRequest reminderRequest = parseReminderRequest(input);
        receiver.invoke(reminderRequest);
    }

    private ReminderRequest parseReminderRequest(String input) {
        Matcher reminderMatcher = reminderPattern.matcher(input);
        if (!reminderMatcher.find()) {
            throw new CommandException(String.format("Failed to set reminder for input '%s'", input));
        }

        String name = reminderMatcher.group("name");
        int time = Integer.parseInt(reminderMatcher.group("time"));
        String units = reminderMatcher.group("units");

        return new ReminderRequest(
            name.trim(),
            time,
            units.toLowerCase()
        );
    }
}
