package assistant.commands;

import assistant.exceptions.CommandException;
import assistant.receivers.PhoneReceiver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneCallCommand extends CommandTemplate {
    // Matches strings like:
    //      => call 1-123-123-1234
    //      => call 123-123-1234
    private static final Pattern phoneCallPattern = Pattern.compile(
        "call ((\\d-?)?\\d{3}-?\\d{3}-?\\d{4})",
        Pattern.CASE_INSENSITIVE
    );
    PhoneReceiver receiver;

    public PhoneCallCommand(String input) {
        super(input);
        receiver = PhoneReceiver.getInstance();
    }

    @Override
    protected boolean validateInput(String input) {
        Matcher matcher = phoneCallPattern.matcher(input);
        return matcher.matches();
    }

    @Override
    protected void notifyUser(String input) {
        System.out.println(String.format("Executing PhoneCallCommand with input [%s]", input));
    }

    @Override
    protected void invokeReceiver(String input) {
        String phoneNumber = parsePhoneNumber(input);
        receiver.invoke(phoneNumber);
    }

    private String parsePhoneNumber(String input) {
        Matcher inputMatcher = phoneCallPattern.matcher(input);
        if (!inputMatcher.find()) {
            throw new CommandException(String.format("Failed to make phone call on input '%s'", input));
        }
        return inputMatcher.group(1).trim();
    }
}
