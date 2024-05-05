package com.example.fitness_application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class logo_controller {


    @FXML
    private static AnchorPane logo_pane;


    public static void generate_logoscreen(Stage stage) throws IOException, InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("logo.fxml"));
        Scene logo_scene = new Scene(fxmlLoader.load(), Main.screen_width - 50, Main.screen_height - 50);
        stage.setScene(logo_scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setMaximized(false);
        stage.setMaximized(true);



    }

    @FXML
    void buttonclick(ActionEvent event) throws IOException, InterruptedException {
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


}
