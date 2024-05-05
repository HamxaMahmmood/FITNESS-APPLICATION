package com.example.fitness_application;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Monday_controller {
    public static void generate_monday_screen( Event event) throws InterruptedException, IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("monday_exercise.fxml"));
        Scene login_scene = new Scene(fxmlLoader.load(), Main.screen_width-50, Main.screen_height-50);
        stage.setScene(login_scene);
        stage.setMaximized(false);
        stage.setMaximized(true);




    }
    @FXML
    void nutritional_button(ActionEvent event) {
    try
    {
        nutrition_controller.generate_nutrition_screen(event);

    } catch (Exception e) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText(null);
        errorAlert.setContentText("Resources not found");
        errorAlert.showAndWait();

    }

}


    @FXML
    void logout_button(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("LOG OUT");
        alert.setHeaderText("Do you want to log out?");

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        String userResponse = result.map(ButtonType::getText).orElse("No");
        if (userResponse.equals("Yes"))
        {
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
        else
        {
            return;
        }

    }
    @FXML
    void calorie_calculator(ActionEvent event) {
        try
        {
            calorie_cal_controller.generate_calorie_calculator_page(event);
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
