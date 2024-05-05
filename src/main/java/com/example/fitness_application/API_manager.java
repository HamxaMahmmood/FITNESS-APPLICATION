package com.example.fitness_application;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javafx.scene.control.Alert;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class API_manager
{
    public static void get_nutritional_analysis(String ingredient, String quantity, String unit, String n_type) throws IOException, InterruptedException {


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://edamam-edamam-nutrition-analysis.p.rapidapi.com/api/nutrition-data?ingr="+quantity+"%20"+unit+"%20"+ingredient+"&nutrition-type="+n_type))
                .header("X-RapidAPI-Key", "5b072244eemshce401cb550e0ff7p1a2efdjsn4c938f621934")
                .header("X-RapidAPI-Host", "edamam-edamam-nutrition-analysis.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        JSONParser parser = new JSONParser();
        JSONObject json ;


        FileWriter fileWriter = new FileWriter(".\\json_files\\nutritional_analysis.json");
            try  {
                Object obj = parser.parse((String) response.body());
                json = (JSONObject) obj;
                fileWriter.write(json.toJSONString());
                fileWriter.flush();

            } catch (IOException | ParseException e) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("An error occurred while writing on the JSON file.");
                errorAlert.showAndWait();
            }
            finally {
                fileWriter.close();
            }





    }

    public static void calculate_calories(String age, String gender, String height, String weight, String activity_level) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://fitness-calculator.p.rapidapi.com/dailycalorie?age="+age+"&gender="+gender+"&height="+height+"&weight="+weight+"&activitylevel="+activity_level))
                .header("X-RapidAPI-Key", "5b072244eemshce401cb550e0ff7p1a2efdjsn4c938f621934")
                .header("X-RapidAPI-Host", "fitness-calculator.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        FileWriter fileWriter = new FileWriter(".\\json_files\\calories_count.json");
        JSONParser parser = new JSONParser();
        JSONObject json ;
        try  {
            Object obj = parser.parse((String) response.body());
            json = (JSONObject) obj;
            fileWriter.write(json.toJSONString());
            fileWriter.flush();

        } catch (IOException | ParseException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("An error occurred while writing on the JSON file.");
            errorAlert.showAndWait();
        }
        finally {
            fileWriter.close();
        }

    }

    public static void send_email(String user_email)
    {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("hamzamahmood165@gmail.com", "Oxford_1067");
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("hamzamahmood165@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user_email));
            message.setSubject("Thank You for Signing Up!");
            message.setText("Dear User,\n\nThank you for signing up to our fitness application! We are excited to have you on board.\n\nBest regards,\nThe Fitness App Team");
            Transport.send(message);
        } catch (MessagingException e) {
            e.getCause();
        }

    }
}
