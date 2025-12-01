package at.foundry.core.exceptions;

public class PluginException extends RuntimeException {

    public PluginException(String message) {
        super(message);
    }

    public PluginException() {
        super("Error occurred during Plugin execution");
    }

}
