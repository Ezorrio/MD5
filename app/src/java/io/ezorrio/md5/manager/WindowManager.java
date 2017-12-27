package io.ezorrio.md5.manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowManager {
    private static Stage mStage;

    public WindowManager(Stage stage) {
        mStage = stage;
    }

    public void showMD5Scene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/scene_app.fxml"));
        mStage.setTitle("MD5 Generator");
        mStage.setScene(new Scene(root, 800, 600));
        mStage.show();
    }

    public void showVerifyScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/scene_verify.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Verify");
        stage.setScene(new Scene(root, 400, 300));
        stage.show();
    }

    public void showGeneratorScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/scene_generator.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Passphrase control");
        stage.setScene(new Scene(root, 300, 400));
        stage.show();
    }

    public Stage getStage() {
        return mStage;
    }
}
