package com.example.fitness_application;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;


public class Main extends Application {


    public static  double screen_width;
    public static  double screen_height;
    private static void get_screen_parameters() {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        screen_width = screenBounds.getWidth();
        screen_height = screenBounds.getHeight();
    }
    @Override
    public void start(Stage stage) throws IOException, InterruptedException  {
        logo_controller.generate_logoscreen(stage);
        stage.show();
    }

    public static void main(String[] args) {
        get_screen_parameters();
        launch();
    }
}