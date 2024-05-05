package com.example.fitness_application;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class sign_controller {
    @FXML
    private TextField emailid_text;
    @FXML
    private PasswordField password_text;
    public static void generate_signinscreen( Event event) throws InterruptedException, IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("sign_in_view.fxml"));
        Scene login_scene = new Scene(fxmlLoader.load(), Main.screen_width-50, Main.screen_height-50);
        stage.setScene(login_scene);
        stage.setMaximized(false);
        stage.setMaximized(true);




    }
    @FXML
    void back_button(ActionEvent event) {
        try
        {
            login_controller.generate_loginscreen(event);
        }
        catch (Exception e)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Resources not found");
            errorAlert.showAndWait();
        }
    }
    @FXML
    void signin_button(ActionEvent event) throws IOException, InterruptedException {
        if ((emailid_text.getText().trim()+" ").equals(" ") && (password_text.getText().trim()+" ").equals(" "))
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("INVALID ENTRIES");
            errorAlert.showAndWait();
            return;
        }
        try {
            try {
                FileWriter fileWriter = new FileWriter(".\\text_files\\user_password.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(emailid_text.getText().trim()+"::::::"+password_text.getText().trim());
                bufferedWriter.newLine();
                bufferedWriter.close();
                fileWriter.close();
                personalisation_controller.generate_personalisation_screen(event);

            } catch (IOException e) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Resources not found");
                errorAlert.showAndWait();
            }

        }
        catch (Exception e)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Resources not found");
            errorAlert.showAndWait();
        }
    }
    @FXML
    private Button cross_button;
    @FXML
    void close_button(ActionEvent event) {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }
    @FXML
    void cross_mouse_entered(MouseEvent event) {
        cross_button.setStyle("-fx-background-color: red;");

    }
    @FXML
    void cross_mouse_exited(MouseEvent event) {
        cross_button.setStyle("-fx-background-color: black;");

    }
}
