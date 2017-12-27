package io.ezorrio.md5.manager;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class DialogManager {

    public static void showInfoDialog(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, text);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            alert.close();
        }
    }

    public static void showInfoDialog(String title, String subtitle, String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, text);
        alert.setTitle(title);
        alert.setHeaderText(subtitle);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            alert.close();
        }
    }

    public static void showErrorDialog(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, text);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            alert.close();
        }
    }
}
