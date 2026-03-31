package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Button button = new Button("Click me!");

        button.setOnAction(e -> System.out.println("Clicked!"));

        Scene scene = new Scene(button, 400, 200);

        stage.setTitle("JavaFX Maven App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
