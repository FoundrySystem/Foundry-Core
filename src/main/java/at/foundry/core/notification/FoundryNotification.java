package at.foundry.core.notification;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.util.Duration;

/**
 * Notification for the UI
 */
public class FoundryNotification {

    /**
     * Sends a Toast message to the UI
     * @param text the text to display
     * @param notificationType see {@link at.foundry.core.notification.FoundryNotification.NotificationType }
     * @param scene the scene you want it to be display (should be the top level)
     */
    public static void send(String text, NotificationType notificationType, Scene scene) {
        Popup popup = new Popup();

        Label label = new Label(text);
        label.setStyle("-fx-background-color: " + notificationType.backgroundColor +
                "; -fx-text-fill: white; -fx-padding: 10px; -fx-background-radius: 5;");
        popup.getContent().add(label);

        var window = scene.getWindow();

        popup.show(window);

        double centerX = window.getX() + (window.getWidth() - label.getWidth()) / 2;
        double topY = window.getY() + 50;
        popup.setX(centerX);
        popup.setY(topY);

        FadeTransition ft = new FadeTransition(Duration.seconds(5), label);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setOnFinished(e -> popup.hide());
        ft.play();
    }

    /**
     * Notification type to display
     */
    public enum NotificationType {
        INFO("green"),
        ERROR("red");

        private final String backgroundColor;

        NotificationType(String backgroundColor) {
            this.backgroundColor = backgroundColor;
        }
    }
}
