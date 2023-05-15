package driver;

import assistant.commands.*;

public class Main {
    public static void main(String[] args) {
        testEmailCommand();
        testMusicCommand();
        testPhoneCallCommand();
        testReminderCommand();
    }

    private static void testEmailCommand() {
        String label = "SEND EMAIL";
        String input = "compose email hello! to email address abc@gmail.com with subject hello";
        EmailCommand emailCommand = new EmailCommand(input);
        runCommandTest(label, input, emailCommand);
    }

    private static void testMusicCommand() {
        String label = "PLAY MUSIC";
        String input = "play yesterday by artist the beatles";
        MusicCommand musicCommand = new MusicCommand(input);
        runCommandTest(label, input, musicCommand);
    }

    private static void testPhoneCallCommand() {
        String label = "CALL PHONE NUMBER";
        String input = "call 123-123-1234";
        PhoneCallCommand phoneCallCommand = new PhoneCallCommand(input);
        runCommandTest(label, input, phoneCallCommand);
    }

    private static void testReminderCommand() {
        String label = "SET REMINDER";
        String input = "set reminder to turn off oven triggering in 2 hours";
        ReminderCommand reminderCommand = new ReminderCommand(input);
        runCommandTest(label, input, reminderCommand);
    }

    private static <T extends CommandTemplate> void runCommandTest(String label, String input, T commandInstance) {
        System.out.printf("***** START: %s *****%n", label);
        System.out.printf("TEST INPUT: '%s'%n", input);
        System.out.println("RUNNING...");
        commandInstance.run();
        System.out.printf("***** END: %s *****%n", label);
    }
}