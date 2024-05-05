package com.example.fitness_application;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class calorie_cal_controller implements Initializable
{
    @FXML
    private Label cal_label;

    @FXML
    private Label cal_label1;

    @FXML
    private Label cal_label11;

    @FXML
    private Label cal_label111;

    @FXML
    private Label cal_label1111;

    @FXML
    private Label cal_label11111;

    @FXML
    private Label cal_label111111;
    @FXML
    private ImageView loading_image;
    private String[] ages = { "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100"};
    private String[] genders = {"male","female"};
    private String[] height = {"100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113", "114", "115", "116", "117", "118", "119", "120", "121", "122", "123", "124", "125", "126", "127", "128", "129", "130", "131", "132", "133", "134", "135", "136", "137", "138", "139", "140", "141", "142", "143", "144", "145", "146", "147", "148", "149", "150", "151", "152", "153", "154", "155", "156", "157", "158", "159", "160", "161", "162", "163", "164", "165", "166", "167", "168", "169", "170", "171", "172", "173", "174", "175", "176", "177", "178", "179", "180", "181", "182", "183", "184", "185", "186", "187", "188", "189", "190", "191", "192", "193", "194", "195", "196", "197", "198", "199", "200", "201", "202", "203", "204", "205", "206", "207", "208", "209", "210", "211", "212", "213", "214", "215", "216", "217", "218", "219", "220", "221", "222", "223", "224", "225", "226", "227", "228", "229", "230"};
    private String[] weight = {"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113", "114", "115", "116", "117", "118", "119", "120", "121", "122", "123", "124", "125", "126", "127", "128", "129", "130", "131", "132", "133", "134", "135", "136", "137", "138", "139", "140", "141", "142", "143", "144", "145", "146", "147", "148", "149", "150", "151", "152", "153", "154", "155", "156", "157", "158", "159", "160"};
    private String[] activity_levels= {"level_1", "level_2", "level_3", "level_4", "level_5", "level_6"};
    @FXML
    private ComboBox<String> activity_box;

    @FXML
    private ComboBox<String> age_box;

    @FXML
    private ComboBox<String> gender_box;

    @FXML
    private ComboBox<String> height_box;


    @FXML
    private ComboBox<String> weight_box;

    @FXML
    void back_button(ActionEvent event) {
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
    void get_calories(ActionEvent event) {
        loading_image.setVisible(true);
        if(weight_box.getValue() == null || height_box.getValue() == null || age_box.getValue() == null || gender_box.getValue() == null || activity_box.getValue() == null )
            {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("INVALID ENTRIES !");
                errorAlert.showAndWait();
                loading_image.setVisible(false);
                return;
            }
        else {
            try
            {
                cal_label.setText("");
                cal_label1.setText("");
                cal_label11.setText("");
                cal_label111.setText("");
                cal_label1111.setText("");
                cal_label11111.setText("");
                cal_label111111.setText("");
                API_manager.calculate_calories(age_box.getValue().trim(),gender_box.getValue().trim(),height_box.getValue().trim(),weight_box.getValue().trim(),activity_box.getValue().trim());
                JSONParser jsonParser = new JSONParser();
                FileReader reader = new FileReader(".\\json_files\\calories_count.json");
                Object obj = jsonParser.parse(reader);
                JSONObject response = (JSONObject) obj;
                JSONObject data=  (JSONObject)response.get("data");
                JSONObject goals = (JSONObject)data.get("goals");
                try
                    {
                        cal_label.setText(goals.get("maintain weight").toString()+ " kcal");

                    }
                catch (Exception e)
                    {
                        e.getCause();
                    }
                try
                {
                    JSONObject mild_weight_loss = (JSONObject)goals.get("Mild weight loss");
                    cal_label1.setText(mild_weight_loss.get("calory").toString()+ " kcal");
                }
                catch (Exception e)
                {
                    e.getCause();
                }
                try
                {
                    JSONObject weight_loss = (JSONObject)goals.get("Weight loss");
                    cal_label11.setText(weight_loss.get("calory").toString()+ " kcal");
                }
                catch (Exception e)
                {
                    e.getCause();
                }
                try
                {
                    JSONObject extreme_weight_loss = (JSONObject)goals.get("Extreme weight loss");
                    cal_label111.setText(extreme_weight_loss.get("calory").toString()+ " kcal");

                }
                catch (Exception e)
                {
                    e.getCause();
                }
                try
                {
                    JSONObject mild_weight_gain = (JSONObject)goals.get("Mild weight gain");
                    cal_label1111.setText(mild_weight_gain.get("calory").toString()+ " kcal");
                }
                catch (Exception e)
                {
                    e.getCause();
                }
                try
                {
                    JSONObject weight_gain = (JSONObject)goals.get("Weight gain");
                    cal_label11111.setText(weight_gain.get("calory").toString()+ " kcal");
                }
                catch (Exception e)
                {
                    e.getCause();
                }
                try
                {
                    JSONObject extreme_weight_gain = (JSONObject)goals.get("Extreme weight gain");
                    cal_label111111.setText(extreme_weight_gain.get("calory").toString()+ " kcal");
                }
                catch (Exception e)
                {
                    e.getCause();
                }

            }
            catch (ConnectException ce)
            {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Failed to connect to the server. Please make sure you are connected to the internet.");
                errorAlert.showAndWait();
            }
            catch (Exception e)
            {
                System.out.println(e.getClass());
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("An unknown error has occurred !");
                errorAlert.showAndWait();
            }
            finally {
                loading_image.setVisible(false);
            }

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
    void nutrition_button(ActionEvent event) {
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



    public static void generate_calorie_calculator_page( Event event) throws InterruptedException, IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("calorie_calculator.fxml"));
        Scene login_scene = new Scene(fxmlLoader.load(), Main.screen_width-50, Main.screen_height-50);
        stage.setScene(login_scene);
        stage.setMaximized(false);
        stage.setMaximized(true);




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        weight_box.getItems().addAll(weight);
        age_box.getItems().addAll(ages);
        gender_box.getItems().addAll(genders);
        height_box.getItems().addAll(height);
        activity_box.getItems().addAll(activity_levels);

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
