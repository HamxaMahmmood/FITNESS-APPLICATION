package com.example.fitness_application;
import java.io.FileReader;
import java.net.ConnectException;
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
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class nutrition_controller implements Initializable {

    @FXML
    private ImageView loading_image;

    @FXML
    private  TextField fact;

    @FXML
    private TextField fact1;

    @FXML
    private TextField fact11;

    @FXML
    private TextField fact111;

    @FXML
    private TextField fact1111;

    @FXML
    private TextField fact11111;

    @FXML
    private TextField fact111111;

    @FXML
    private TextField fact1111111;

    @FXML
    private TextField fact11111111;

    @FXML
    private TextField fact111111111;

    @FXML
    private TextField fact1111111111;

    @FXML
    private TextField fact11111111111;

    @FXML
    private TextField ingredient_text;

    @FXML
    private TextField quantity_text;
    private final String[] unit_list = {"kg","grams","dozens","dozen","ml","litre","litres"};
    private final String[] nutrition_type = {"cooking","logging"};
    @FXML
    private ComboBox<String> n_type_box;
    @FXML
    private ComboBox<String> unit_box;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        n_type_box.getItems().addAll(nutrition_type);
        unit_box.getItems().addAll(unit_list);

    }


    public static void generate_nutrition_screen( Event event) throws InterruptedException, IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("nutritional_analysis.fxml"));
        Scene login_scene = new Scene(fxmlLoader.load(), Main.screen_width-50, Main.screen_height-50);
        stage.setScene(login_scene);
        stage.setMaximized(false);
        stage.setMaximized(true);




    }
    @FXML
    void back_button(ActionEvent event) {
        try {
            Monday_controller.generate_monday_screen(event);
        }
        catch (Exception e) {
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
    void get_button(ActionEvent event) {
        loading_image.setVisible(true);

        if (unit_box.getValue()== null && n_type_box.getValue() == null && (ingredient_text.getText().trim()+" ").equals(" ") && (quantity_text.getText().trim()+" ").equals(" ") )
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Invalid Entries!");
            errorAlert.showAndWait();
            loading_image.setVisible(false);
            return;
        }

            if(!(isnumeric(ingredient_text.getText())) && !(isalpha(quantity_text.getText())))
            {
                try {
                    fact.setText("");
                    fact1.setText("");
                    fact11.setText("");
                    fact111.setText("");
                    fact1111.setText("");
                    fact11111.setText("");
                    fact111111.setText("");
                    fact1111111.setText("");
                    fact11111111.setText("");
                    fact111111111.setText("");
                    fact1111111111.setText("");
                    fact11111111111.setText("");
                    String quan = "",ingr = "";
                    for (String temp1 : ingredient_text.getText().trim().split(" "))
                    {
                        ingr = ingr + temp1;
                    }
                    for (String temp2 : quantity_text.getText().trim().split(" "))
                    {
                        quan = quan + temp2;
                    }
                    API_manager.get_nutritional_analysis(ingr,quan, unit_box.getValue().trim(), n_type_box.getValue().trim());

                    JSONParser jsonParser = new JSONParser();
                    FileReader reader = new FileReader(".\\json_files\\nutritional_analysis.json");
                    Object obj = jsonParser.parse(reader);
                    JSONObject nutri_obj = (JSONObject) obj;
                    JSONObject totalNutrients = (JSONObject) nutri_obj.get("totalNutrients");;
                    try {
                            fact.setText(nutri_obj.get("totalWeight").toString() + " g");
                            fact1.setText(nutri_obj.get("calories").toString() + " kcal");
                        } catch (Exception e)
                        {
                            e.getCause();
                        }
                    try {
                            JSONObject cholestrol = (JSONObject) totalNutrients.get("CHOLE");
                            fact111.setText( cholestrol.get("quantity").toString()+" " +cholestrol.get("unit").toString());


                        }
                    catch (Exception e)
                        {
                            e.getCause();
                        }
                    try {
                        JSONObject fat = (JSONObject) totalNutrients.get("FAT");
                        fact11.setText( fat.get("quantity").toString()+" " +fat.get("unit").toString());
                        } catch (Exception e)
                        {
                            e.getCause();
                        }
                        try {
                            JSONObject carbs = (JSONObject) totalNutrients.get("CHOCDF.net");
                            fact1111.setText( carbs.get("quantity").toString()+" " +carbs.get("unit").toString());
                        } catch (Exception e)
                        {
                            e.getCause();
                        }
                        try {
                            JSONObject fibers = (JSONObject) totalNutrients.get("FIBTG");
                            fact11111.setText( fibers.get("quantity").toString()+" " +fibers.get("unit").toString());
                        } catch (Exception e)
                        {
                            e.getCause();
                        }
                        try {
                            JSONObject sugars = (JSONObject) totalNutrients.get("SUGAR");
                            fact111111.setText(sugars.get("quantity").toString()+" " +sugars.get("unit").toString());

                        } catch (Exception e)
                        {
                            e.getCause();
                        }
                        try {
                            JSONObject proteins = (JSONObject) totalNutrients.get("PROCNT");
                            fact1111111.setText( proteins.get("quantity").toString()+" " +proteins.get("unit").toString());

                        } catch (Exception e)
                        {
                            e.getCause();
                        }
                        try {
                            JSONObject calcium = (JSONObject) totalNutrients.get("CA");
                            fact11111111.setText(calcium.get("quantity").toString()+" " +calcium.get("unit").toString());
                        } catch (Exception e)
                        {
                            e.getCause();
                        }
                        try {
                            JSONObject iron = (JSONObject) totalNutrients.get("FE");
                            fact111111111.setText(iron.get("quantity").toString()+" " +iron.get("unit").toString());
                        } catch (Exception e)
                        {
                            e.getCause();
                        }
                        try {
                            JSONObject vitamin_a = (JSONObject) totalNutrients.get("VITA_RAE");
                            fact1111111111.setText( vitamin_a.get("quantity").toString()+" " +vitamin_a.get("unit").toString());
                        } catch (Exception e)
                        {
                            e.getCause();
                        }
                        try {
                            JSONObject vitamin_c = (JSONObject) totalNutrients.get("VITC");
                            fact11111111111.setText(vitamin_c.get("quantity").toString()+" " +vitamin_c.get("unit").toString());
                        } catch (Exception e)
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
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("An unknown error has occurred !\nDo not use spaces between the ingredients or quantity.");
                    errorAlert.showAndWait();
                }
                finally {
                    loading_image.setVisible(false);
                }

            }
            else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("INVALID ENTRIES !");
                errorAlert.showAndWait();
                return;
            }



;
    }
    public static boolean isnumeric(String input) {
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;

            }
        }
        return false;
    }
    public static boolean isalpha(String input) {
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                return true;
            }
        }
        return false;
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
