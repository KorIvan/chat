package com.tassta.view;


import com.tassta.model.ApplicationStub;
import com.tassta.model.UserImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginViewImpl {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label alertLabel;

    @FXML
    private void initialize() {
        usernameField.textProperty().bindBidirectional(passwordField.textProperty());
    }

    @FXML
    private void login() throws IOException {
        if (!usernameField.getText().isEmpty()||!passwordField.getText().isEmpty()) {
            ApplicationStub.setLoggedUser(new UserImpl(1000,this.usernameField.getText(),true,null));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/chat.fxml"));
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene scene=new Scene(loader.load());
            scene.getStylesheets().clear();
            scene.getStylesheets().add("/style.css");
            stage.setScene(scene);

           // ChatViewImpl controller = loader.<ChatViewImpl>getController();
            //controller.passUsername(this.usernameField.getText());

            stage.show();
        } else alertLabel.setText("Enter username and pass");
    }

    @FXML
    private void cancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}
