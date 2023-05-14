package assistant.receivers;

import assistant.exceptions.CommandReceiverException;
import assistant.interfaces.CommandReceiver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneReceiver implements CommandReceiver {
    // Matches strings like:
    //      => 1-123-123-1234
    //      => 123-123-1234
    private static final Pattern phoneCallPattern = Pattern.compile(
        "call ((\\d-?)?\\d{3}-?\\d{3}-?\\d{4})",
            Pattern.CASE_INSENSITIVE
    );

    @Override
    public void invoke(String input) {
        String phoneNumber = parsePhoneNumber(input);
        System.out.printf("Dialing %s...%n", phoneNumber);
    }

    @Override
    public Pattern getInputPattern() {
        return phoneCallPattern;
    }

    private String parsePhoneNumber(String input) {
        Matcher inputMatcher = phoneCallPattern.matcher(input);
        if (!inputMatcher.find()) {
            throw new CommandReceiverException(String.format("Failed to make phone call on input '%s'", input));
        }
        return inputMatcher.group(0);
    }
}
