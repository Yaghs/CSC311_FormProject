package com.mycompany.javafx_db_example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class Log_password_controller {

    @FXML
    private Button loginButton;
    @FXML
    private Button SignUpButton;
    @FXML
    private void handleLogInButtonAction(ActionEvent event) {
        try {
            // Load the FXML for the new scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("db_interface_gui.fxml"));
            Parent root = loader.load();

            // Get the current stage (window) from the event that triggered the method
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the current stage
            stage.setScene(new Scene(root));

            // Optional: set the title of the window and show the stage again

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSignUpButtonAction(ActionEvent event) {
        try {
            // Load the FXML for the new scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Registration.fxml"));
            Parent root = loader.load();

            // Get the current stage (window) from the event that triggered the method
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the current stage
            stage.setScene(new Scene(root));

            // Optional: set the title of the window and show the stage again

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
