package io.ezorrio.md5.controller;

import io.ezorrio.md5.App;
import io.ezorrio.md5.crypto.MD5;
import io.ezorrio.md5.crypto.MD5Repo;
import io.ezorrio.md5.manager.DialogManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Objects;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    @FXML
    private MenuItem bRestriction;
    @FXML
    private MenuItem bClose;
    @FXML
    private MenuItem bAbout;

    @FXML
    private Label currentFile;
    @FXML
    private TextField inputFilePass;
    @FXML
    private Button generateFileSum;
    @FXML
    private Button verifyFileSum;
    @FXML
    private CheckBox useFilePass;
    @FXML
    private Button chooseFile;
    @FXML
    private Button saveFileSum;
    @FXML
    private TextArea outputFile;
    @FXML
    private Button generateTextSum;
    @FXML
    private Button verifyTextSum;
    @FXML
    private Button saveTextSum;
    @FXML
    private CheckBox useTextPass;
    @FXML
    private TextArea inputText;
    @FXML
    private TextArea outputText;
    @FXML
    private TextField inputTextPass;

    private File inputFile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chooseFile.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Choose file");
            inputFile = chooser.showOpenDialog(App.getWindowManager().getStage()).getAbsoluteFile();
            currentFile.setText("Current file: " + inputFile.getName());
        });

        generateFileSum.setOnAction(e -> {
            if (useFilePass.isSelected() && !isValidPass(inputFilePass.getText())) {
                DialogManager.showErrorDialog("Passphrase does not match requirements");
                return;
            }

            try {
                if (Objects.isNull(inputFile)) {
                    DialogManager.showErrorDialog("No file specified!");
                    return;
                }
                String data = new String(Files.readAllBytes(inputFile.toPath()));
                String key = useFilePass.isSelected() ? inputFilePass.getText() : "";
                outputFile.setText(new MD5().getMD5ofStr(data + key));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        useFilePass.setOnAction(e -> inputFilePass.setDisable(!useFilePass.isSelected()));

        saveFileSum.setOnAction(e -> {
            if (useFilePass.isSelected() && !isValidPass(inputFilePass.getText())) {
                DialogManager.showErrorDialog("Passphrase does not match requirements");
                return;
            }
            try {
                if (Objects.isNull(inputFile)) {
                    DialogManager.showErrorDialog("No file specified!");
                    return;
                }
                String data = new String(Files.readAllBytes(inputFile.toPath()));
                String key = useFilePass.isSelected() ? inputFilePass.getText() : null;
                saveToFile(data, key);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        verifyFileSum.setOnAction(e -> {
            if (useFilePass.isSelected() && !isValidPass(inputFilePass.getText())) {
                DialogManager.showErrorDialog("Passphrase does not match requirements");
                return;
            }
            try {
                if (Objects.isNull(inputFile)) {
                    DialogManager.showErrorDialog("No file specified!");
                    return;
                }
                String inputData = new String(Files.readAllBytes(inputFile.toPath()));
                if (useFilePass.isSelected()) {
                    inputData += inputFilePass.getText();
                }
                System.out.println("Text data: " + inputData);
                MD5Repo.setSum(new MD5().getMD5ofStr(inputData));
                App.getWindowManager().showVerifyScene();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });


        generateTextSum.setOnAction(e -> {
            if (useTextPass.isSelected() && !isValidPass(inputTextPass.getText())) {
                DialogManager.showErrorDialog("Passphrase does not match requirements");
                return;
            }
            String data = inputText.getText();
            String key = useTextPass.isSelected() ? inputTextPass.getText() : "";
            outputText.setText(new MD5().getMD5ofStr(data + key));
        });
        useTextPass.setOnAction(e -> inputTextPass.setDisable(!useTextPass.isSelected()));

        saveTextSum.setOnAction(e -> {
            if (useTextPass.isSelected() && !isValidPass(inputTextPass.getText())) {
                DialogManager.showErrorDialog("Passphrase does not match requirements");
                return;
            }
            String data = inputText.getText();
            String key = useTextPass.isSelected() ? inputTextPass.getText() : "";
            saveToFile(data, key);
        });

        verifyTextSum.setOnAction(e -> {
            if (useTextPass.isSelected() && !isValidPass(inputTextPass.getText())) {
                DialogManager.showErrorDialog("Passphrase does not match requirements");
                return;
            }
            try {
                String inputData = inputText.getText();
                if (useTextPass.isSelected()) {
                    inputData += inputTextPass.getText();
                }
                System.out.println("Text data: " + inputData);
                MD5Repo.setSum(new MD5().getMD5ofStr(inputData));
                App.getWindowManager().showVerifyScene();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        bClose.setOnAction(e -> Platform.exit());

        bRestriction.setOnAction(e -> {
            try {
                App.getWindowManager().showGeneratorScene();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        bAbout.setOnAction(e -> DialogManager.showInfoDialog("About", "Information security.\nMD5 hashsum",
                "Author: Emin Guliev \nGroup: A-13-14"));
    }

    private void saveToFile(String data, String key) {
        if (data.isEmpty()) {
            return;
        }

        System.out.println("Saving: " + "data: " + data + " key: " + key);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(App.getWindowManager().getStage()).getAbsoluteFile();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
            writer.append(new MD5().getMD5ofStr(key != null ? data + key : data));
            writer.close();
            DialogManager.showInfoDialog("File saved!");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private boolean isValidPass(String pass) {
        return App.getPrefs().getMinLength() <= pass.length()
                && (!App.getPrefs().hasDigit() || pass.matches(".*\\d+.*"))
                && (!App.getPrefs().hasUpperCase() || pass.matches(".*[A-Z].*") || pass.matches(".*[А-Я].*"))
                && (!App.getPrefs().hasLowerCase() || pass.matches(".*[a-z].*") || pass.matches(".*[а-я].*"));
    }
}
