package com.ncu.example;
import com.ncu.example.view.SampleView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.stage.Stage;

import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ExampleApplication extends AbstractJavaFxApplicationSupport {
    public static void main(String[] args){
        launch(ExampleApplication.class, SampleView.class,args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
    }
}
