package assistant.logging;

import assistant.exceptions.LoggerException;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AssistantCommandLogger {
    private final FileHandler logFileHandler;
    private final Logger logger;
    private static AssistantCommandLogger instance = null;

    private AssistantCommandLogger() throws IOException {
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        logFileHandler = new FileHandler("command.log", true);
        logFileHandler.setFormatter(simpleFormatter);
        logger = Logger.getLogger("command-system");
        logger.addHandler(logFileHandler);
    }

    public static AssistantCommandLogger getInstance() {
        if (Objects.isNull(instance)) {
            try {
                instance = new AssistantCommandLogger();
            } catch (IOException e) {
                throw new LoggerException("Failed to initialize logging system", e);
            }
        }

        return instance;
    }

    public void logMessage(Level level, String message) {
        logger.log(level, message);
    }

    public void close() {
        logger.removeHandler(logFileHandler);
        logFileHandler.close();
    }
}
