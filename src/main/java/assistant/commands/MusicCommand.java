package assistant.commands;

import assistant.receivers.MusicReceiver;

public class MusicCommand extends CommandTemplate {
    MusicReceiver receiver;

    public MusicCommand(String input, MusicReceiver receiver) {
        super(input);
        this.receiver = receiver;
    }

    @Override
    protected void notifyUser(String input) {
        System.out.println(String.format("Executing MusicCommand with input [%s]", input));
    };

    @Override
    protected void invokeReceiver(String input) {
        receiver.invoke(input);
    };
}
