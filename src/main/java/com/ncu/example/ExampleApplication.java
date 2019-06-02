package com.ncu.example;
import com.ncu.example.Controler.Manager;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class ExampleApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(ExampleApplication.class, args);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("计分系统!");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }
}
