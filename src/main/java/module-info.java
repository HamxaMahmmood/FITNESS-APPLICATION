module com.example.fitness_application {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires org.junit.jupiter.api;
    requires java.net.http;
    requires json.simple;
    requires java.mail;


    opens com.example.fitness_application to javafx.fxml;
    exports com.example.fitness_application;
}