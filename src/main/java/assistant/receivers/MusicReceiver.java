package assistant.receivers;

import assistant.interfaces.CommandReceiver;
import assistant.records.MusicRequest;

import java.util.Objects;

public class MusicReceiver implements CommandReceiver<MusicRequest> {
    private static MusicReceiver instance;

    public static MusicReceiver getInstance() {
        if (Objects.isNull(instance)) {
            instance = new MusicReceiver();
        }
        return instance;
    }

    @Override
    public void invoke(MusicRequest input) {
        System.out.printf("Playing '%s' by '%s'...%n", input.song(), input.artist());
    }
}
