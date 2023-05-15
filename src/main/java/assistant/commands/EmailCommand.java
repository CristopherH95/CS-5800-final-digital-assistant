package assistant.commands;

import assistant.exceptions.CommandException;
import assistant.receivers.EmailReceiver;
import assistant.records.EmailRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailCommand extends CommandTemplate {
    // Matches strings like: "compose email hello world to email address abc@gmail.com with subject hello"
    private static final Pattern emailPattern = Pattern.compile(
        "(?:compose\\s+email)(?<body>.*?)" +
                "(?:to\\s+email\\s+address)\\s+(?<address>[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.\\-]+@[a-zA-Z0-9.\\-]+)" +
                "\\s+(?:with\\s+subject)(?<subject>.+)",
        Pattern.CASE_INSENSITIVE
    );
    EmailReceiver receiver;

    public EmailCommand(String input) {
        super(input);
        receiver = EmailReceiver.getInstance();
    }

    @Override
    protected boolean validateInput(String input) {
        Matcher matcher = emailPattern.matcher(input);
        return matcher.matches();
    }

    @Override
    protected void notifyUser(String input) {
        System.out.println(String.format("Executing EmailCommand with input [%s]", input));
    }

    @Override
    protected void invokeReceiver(String input) {
        EmailRequest emailRequest = parseEmailRequest(input);
        receiver.invoke(emailRequest);
    }

    private EmailRequest parseEmailRequest(String input) {
        Matcher requestMatcher = emailPattern.matcher(input);
        if (!requestMatcher.find()) {
            throw new CommandException(String.format("Failed to compose email with input '%s'", input));
        }
        String body = requestMatcher.group("body");
        String address = requestMatcher.group("address");
        String subject = requestMatcher.group("subject");

        return new EmailRequest(
            body.trim(),
            address.trim(),
            subject.trim()
        );
    }
}
