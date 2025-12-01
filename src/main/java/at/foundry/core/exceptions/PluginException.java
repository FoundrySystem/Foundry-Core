package at.foundry.core.exceptions;

/**
 * Generic Plugin Exception
 */
public class PluginException extends RuntimeException {

    /**
     * Throws a Plugin Exception
     * @param message a custom message to display in console
     */
    public PluginException(String message) {
        super(message);
    }

    /**
     * Throws a Plugin Exception with the standard message
     */
    public PluginException() {
        super("Error occurred during Plugin execution");
    }

}
