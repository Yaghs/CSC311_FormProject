package com.mycompany.javafx_db_example;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SplashScreenController implements Initializable {

    @FXML
    private Label loadingLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // You can perform initialization tasks here if needed
        // For example, you can set the loading label or perform any necessary setup.
        loadingLabel.setText("Loading...");

        // You might want to load any necessary resources or perform background tasks here.
    }
}