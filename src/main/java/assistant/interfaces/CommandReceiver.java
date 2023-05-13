package assistant.interfaces;

import java.util.regex.Pattern;

public interface CommandReceiver {
    void invoke(String input);
    Pattern getInputPattern();
}
