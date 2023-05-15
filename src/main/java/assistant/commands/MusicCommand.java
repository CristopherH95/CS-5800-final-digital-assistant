package assistant.commands;

import assistant.exceptions.CommandException;
import assistant.receivers.MusicReceiver;
import assistant.records.MusicRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MusicCommand extends CommandTemplate {
    // Matches strings like: play yesterday by artist the beatles
    private static final Pattern musicPattern = Pattern.compile(
        "(?:play\\s+)(?<song>.*?)(?:by\\s+artist\\s+)(?<artist>.*)",
        Pattern.CASE_INSENSITIVE
    );
    MusicReceiver receiver;

    public MusicCommand(String input) {
        super(input);
        receiver = MusicReceiver.getInstance();
    }

    @Override
    protected boolean validateInput(String input) {
        Matcher matcher = musicPattern.matcher(input);
        return matcher.matches();
    }

    @Override
    protected void notifyUser(String input) {
        System.out.println(String.format("Executing MusicCommand with input [%s]", input));
    };

    @Override
    protected void invokeReceiver(String input) {
        MusicRequest musicRequest = parseMusicRequest(input);
        receiver.invoke(musicRequest);
    }

    private MusicRequest parseMusicRequest(String input) {
        Matcher musicMatcher = musicPattern.matcher(input);
        if (!musicMatcher.find()) {
            throw new CommandException(String.format("Failed to play music with input '%s'", input));
        }

        String song = musicMatcher.group("song");
        String artist = musicMatcher.group("artist");

        return new MusicRequest(
            song.trim(),
            artist.trim()
        );
    }
}
