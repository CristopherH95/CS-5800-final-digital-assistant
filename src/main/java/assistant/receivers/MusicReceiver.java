package assistant.receivers;

import assistant.exceptions.CommandReceiverException;
import assistant.interfaces.CommandReceiver;
import assistant.records.MusicRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MusicReceiver implements CommandReceiver {
    private static final Pattern musicPattern = Pattern.compile(
        "(?:play\\s+)(?<song>.*?)(?:by\\s+artist\\s+)(?<artist>.*)",
        Pattern.CASE_INSENSITIVE
    );

    @Override
    public void invoke(String input) {
        MusicRequest musicRequest = parseMusicRequest(input);
        System.out.printf("Playing '%s' by '%s'...%n", musicRequest.song(), musicRequest.artist());
    }

    @Override
    public Pattern getInputPattern() {
        return musicPattern;
    }

    private MusicRequest parseMusicRequest(String input) {
        Matcher musicMatcher = musicPattern.matcher(input);
        if (!musicMatcher.find()) {
            throw new CommandReceiverException(String.format("Failed to play music with input '%s'", input));
        }

        String song = musicMatcher.group("song");
        String artist = musicMatcher.group("artist");

        return new MusicRequest(
            song,
            artist
        );
    }
}
