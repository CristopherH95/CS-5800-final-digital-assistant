package assistant.commands;

import assistant.receivers.ReminderReceiver;

public class ReminderCommand extends CommandTemplate {
    ReminderReceiver receiver;

    public ReminderCommand(String input, ReminderReceiver receiver) {
        super(input);
        this.receiver = receiver;
    }

    @Override
    protected void notifyUser(String input) {
        System.out.println(String.format("Executing ReminderCommand with input [%s]", input));
    };

    @Override
    protected void invokeReceiver(String input) {
        receiver.invoke(input);
    };
}
