package assistant.commands;

import assistant.exceptions.InvalidInputException;
import assistant.interfaces.AssistantCommand;
import assistant.logging.AssistantCommandLogger;

import java.util.logging.Level;

public abstract class CommandTemplate implements AssistantCommand {
    protected final String input;
    protected final AssistantCommandLogger logger;

    public CommandTemplate(String input) {
        this.input = input;
        logger = AssistantCommandLogger.getInstance();
    }

    @Override
    public void run() {
        if (!this.validateInput(input)) {
            throw new InvalidInputException(String.format("Input '%s' is malformed!", input));
        }
        logger.logMessage(Level.INFO, craftLogMessage(input));
        notifyUser(input);
        invokeReceiver(input);
    }

    protected String craftLogMessage(String input) {
        return String.format("Executing command with input '%s'", input);
    }

    protected abstract boolean validateInput(String input);
    protected abstract void notifyUser(String input);
    protected abstract void invokeReceiver(String input);
}
