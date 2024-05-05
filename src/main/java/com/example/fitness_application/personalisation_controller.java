package com.example.fitness_application;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class personalisation_controller implements Initializable {

    @FXML
    private ComboBox<String> age_menu;

    @FXML
    private ComboBox<String> days_menu;

    @FXML
    private ComboBox<String> gender_menu;

    @FXML
    private ComboBox<String> height_menu;

    @FXML
    private ComboBox<String> lifestyle_menu;

    public static void generate_personalisation_screen( Event event) throws InterruptedException, IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("personalisation.fxml"));
        Scene login_scene = new Scene(fxmlLoader.load(), Main.screen_width-50, Main.screen_height-50);
        stage.setScene(login_scene);
        stage.setMaximized(false);
        stage.setMaximized(true);




    }
    //CREATING ALL THE ARRAYS FOR THE DROPDOWN MENUS
    private String[] genders = {"Male   ","Female  "},lifestyles = {"Active","Balanced","Sedentary"};
    private String[] ages = { "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100"};
    private String[] days = {"3", "4", "5", "6"};
    private String[] height = {"100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113", "114", "115", "116", "117", "118", "119", "120", "121", "122", "123", "124", "125", "126", "127", "128", "129", "130", "131", "132", "133", "134", "135", "136", "137", "138", "139", "140", "141", "142", "143", "144", "145", "146", "147", "148", "149", "150", "151", "152", "153", "154", "155", "156", "157", "158", "159", "160", "161", "162", "163", "164", "165", "166", "167", "168", "169", "170", "171", "172", "173", "174", "175", "176", "177", "178", "179", "180", "181", "182", "183", "184", "185", "186", "187", "188", "189", "190", "191", "192", "193", "194", "195", "196", "197", "198", "199", "200", "201", "202", "203", "204", "205", "206", "207", "208", "209", "210", "211", "212", "213", "214", "215", "216", "217", "218", "219", "220", "221", "222", "223", "224", "225", "226", "227", "228", "229", "230", "231", "232", "233", "234", "235", "236", "237", "238", "239", "240", "241", "242", "243", "244", "245", "246", "247", "248", "249", "250"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gender_menu.getItems().addAll(genders);
        age_menu.getItems().addAll(ages);
        lifestyle_menu.getItems().addAll(lifestyles);
        days_menu.getItems().addAll(days);
        height_menu.getItems().addAll(height);

    }
    @FXML
    private void nextbutton(ActionEvent event) throws IOException, InterruptedException {
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
