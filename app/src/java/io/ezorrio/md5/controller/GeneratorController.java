package io.ezorrio.md5.controller;

import io.ezorrio.md5.App;
import io.ezorrio.md5.manager.DialogManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;

import java.net.URL;
import java.util.ResourceBundle;

public class GeneratorController implements Initializable {
    @FXML
    private CheckBox cbDigit;
    @FXML
    private CheckBox cbLower;
    @FXML
    private CheckBox cbUpper;
    @FXML
    private Button bSave;
    @FXML
    private Spinner spLength;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbDigit.setSelected(App.getPrefs().hasDigit());
        cbUpper.setSelected(App.getPrefs().hasUpperCase());
        cbLower.setSelected(App.getPrefs().hasLowerCase());
        spLength.getValueFactory().setValue(App.getPrefs().getMinLength());

        bSave.setOnAction(e -> {
            App.getPrefs().setHasDigit(cbDigit.isSelected());
            App.getPrefs().setHasUpperCase(cbUpper.isSelected());
            App.getPrefs().setHasLowerCase(cbLower.isSelected());
            App.getPrefs().setMinLength((Integer) spLength.getValue());
            DialogManager.showInfoDialog("Values saved!");
        });
    }
}
