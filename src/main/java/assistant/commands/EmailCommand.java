package assistant.commands;

import assistant.receivers.EmailReceiver;

public class EmailCommand extends CommandTemplate {
    EmailReceiver receiver;

    public EmailCommand(String input, EmailReceiver receiver) {
        super(input);
        this.receiver = receiver;
    }

    @Override
    protected void notifyUser(String input) {
        System.out.println(String.format("Executing EmailCommand with input [%s]", input));
    };

    @Override
    protected void invokeReceiver(String input) {
        receiver.invoke(input);
    };
}
