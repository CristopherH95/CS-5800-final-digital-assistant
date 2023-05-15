package assistant.commands;

import assistant.receivers.PhoneReceiver;

public class PhoneCallCommand extends CommandTemplate {
    PhoneReceiver receiver;

    public PhoneCallCommand(String input, PhoneReceiver receiver) {
        super(input);
        this.receiver = receiver;
    }

    @Override
    protected void notifyUser(String input) {
        System.out.println(String.format("Executing PhoneCallCommand with input [%s]", input));
    };

    @Override
    protected void invokeReceiver(String input) {
        receiver.invoke(input);
    };
}
