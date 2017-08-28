package framework;

public class Logger {

    public static void logMessage(final String message) {
        System.out.println("~ ~ ~ [LOG] Сообщение: " + "{" + message +"} ~ ~ ~");
    }

    public static void logMessageWithParam(final String message, final String parameter){
        System.out.println("~ ~ ~ [LOG] Сообщение: " + "{" + message + " (" + parameter + ") " +"} ~ ~ ~");
    }
}
