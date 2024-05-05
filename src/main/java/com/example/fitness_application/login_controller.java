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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class login_controller {
    @FXML
    private TextField emailid_text;
    @FXML
    private PasswordField password_text;
    @FXML
    private static AnchorPane login_page;
    public static void generate_loginscreen( Event event) throws InterruptedException, IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login_view.fxml"));
        Scene login_scene = new Scene(fxmlLoader.load(), Main.screen_width-50, Main.screen_height-50);
        stage.setScene(login_scene);
        stage.setMaximized(false);
        stage.setMaximized(true);





    }

    @FXML
    void sign_in_page(ActionEvent event) throws IOException, InterruptedException {

        try{

            sign_controller.generate_signinscreen(event);

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
    void login_button(ActionEvent event) {
        try (BufferedReader br = new BufferedReader(new FileReader(".\\text_files\\user_password.txt")))
        {
            String line;
            int flag = 0;
            while ((line = br.readLine()) != null)
            {
                if(line.split("::::::")[0].equals(emailid_text.getText().trim()) && line.split("::::::")[1].equals(password_text.getText().trim()))
                {
                    flag = 1;
                    break;
                }
            }
            br.close();
            if (flag == 1)
            {
                try {
                    Monday_controller.generate_monday_screen(event);
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
            else
            {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("User not found");
                errorAlert.showAndWait();
            }
        } catch (IOException e) {
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
