package assistant.receivers;

import assistant.interfaces.CommandReceiver;

import java.util.Objects;

public class PhoneReceiver implements CommandReceiver<String> {
    private static PhoneReceiver instance;

    public static PhoneReceiver getInstance() {
        if (Objects.isNull(instance)) {
            instance = new PhoneReceiver();
        }
        return instance;
    }

    @Override
    public void invoke(String input) {
        System.out.printf("Dialing %s...%n", input);
    }
}
