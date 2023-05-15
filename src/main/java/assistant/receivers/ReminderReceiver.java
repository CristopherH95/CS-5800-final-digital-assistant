package assistant.receivers;

import assistant.interfaces.CommandReceiver;
import assistant.records.ReminderRequest;

import java.util.Objects;

public class ReminderReceiver implements CommandReceiver<ReminderRequest> {
    private static ReminderReceiver instance;

    public static ReminderReceiver getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ReminderReceiver();
        }
        return instance;
    }

    @Override
    public void invoke(ReminderRequest input) {
        System.out.printf(
            "Setting reminder '%s' in %d %s%n",
            input.name(),
            input.time(),
            input.units()
        );
    }
}
