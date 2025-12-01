package at.foundry.core.notification;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.util.Duration;

public class FoundryNotification {

    public static void send(String text, NotificationType notificationType, Scene scene) {
        Popup popup = new Popup();

        Label label = new Label(text);
        final String color = switch (notificationType) {
            case ERROR -> "red";
            case INFO -> "green";
        };
        label.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; -fx-padding: 10px; -fx-background-radius: 5;");
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

    public enum NotificationType {
        INFO,
        ERROR,
    }
}
