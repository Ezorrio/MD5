package io.ezorrio.md5.controller;

import io.ezorrio.md5.App;
import io.ezorrio.md5.crypto.MD5Repo;
import io.ezorrio.md5.manager.DialogManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

public class VerifyController implements Initializable {
    @FXML
    private TextField sum;
    @FXML
    private Button verify;
    @FXML
    private Button verify_file;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        verify.setOnAction(e -> {
            if (MD5Repo.isSumValid(sum.getText())) {
                DialogManager.showInfoDialog("MD5 correct!");
            } else {
                DialogManager.showInfoDialog("MD5 incorrect!");
            }
        });

        verify_file.setOnAction(e->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose hash file");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setSelectedExtensionFilter(extFilter);
            File file = fileChooser.showOpenDialog(App.getWindowManager().getStage()).getAbsoluteFile();
            try {
                String data = Files.readAllLines(file.toPath()).toString();
                data = data.replaceAll("\\[", "").replaceAll("]","");
                System.out.println("Hash from file" + data);
                if (MD5Repo.isSumValid(data)) {
                    DialogManager.showInfoDialog("MD5 correct!");
                } else {
                    DialogManager.showInfoDialog("MD5 incorrect!");
                }
            } catch (IOException e1) {
                e1.printStackTrace();
                DialogManager.showErrorDialog("Error reading file");
            }
        });
    }
}
