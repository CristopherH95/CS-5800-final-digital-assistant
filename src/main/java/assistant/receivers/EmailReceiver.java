package assistant.receivers;

import assistant.exceptions.CommandReceiverException;
import assistant.interfaces.CommandReceiver;
import assistant.records.EmailRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailReceiver implements CommandReceiver {
    // Matches strings like: "compose email hello world to email address abc@gmail.com with subject hello"
    private static final Pattern emailPattern = Pattern.compile(
    "(?:compose\\s+email)(?<body>.*?)" +
            "(?:to\\s+email\\s+address)\\s+(?<address>[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.\\-]+@[a-zA-Z0-9.\\-]+)" +
            "\\s+(?:with\\s+subject)(?<subject>.+)",
        Pattern.CASE_INSENSITIVE
    );

    @Override
    public void invoke(String input) {
        EmailRequest emailRequest = parseEmailRequest(input);
        System.out.printf(
            "Sending email '%s' to '%s' with subject '%s'...%n",
            emailRequest.body(),
            emailRequest.address(),
            emailRequest.subject()
        );
    }

    @Override
    public Pattern getInputPattern() {
        return emailPattern;
    }

    private EmailRequest parseEmailRequest(String input) {
        Matcher requestMatcher = emailPattern.matcher(input);
        if (!requestMatcher.find()) {
            throw new CommandReceiverException(String.format("Failed to compose email with input '%s'", input));
        }
        String body = requestMatcher.group("body");
        String address = requestMatcher.group("address");
        String subject = requestMatcher.group("subject");

        return new EmailRequest(
            body,
            address,
            subject
        );
    }
}
