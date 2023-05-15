package assistant.receivers;

import assistant.interfaces.CommandReceiver;
import assistant.records.EmailRequest;

import java.util.Objects;

public class EmailReceiver implements CommandReceiver<EmailRequest> {
    private static EmailReceiver instance;

    public static EmailReceiver getInstance() {
        if (Objects.isNull(instance)) {
            instance = new EmailReceiver();
        }
        return instance;
    }

    @Override
    public void invoke(EmailRequest input) {
        System.out.printf(
            "Sending email '%s' to '%s' with subject '%s'...%n",
            input.body(),
            input.address(),
            input.subject()
        );
    }
}
