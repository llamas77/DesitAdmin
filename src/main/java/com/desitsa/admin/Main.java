package com.desitsa.admin;

import com.desitsa.admin.controllers.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    private ViewController viewController;

    @Override
    public void start(Stage primaryStage) throws Exception {

        viewController = ViewController.getInstance(primaryStage);
        viewController.setTitle("Desit SA");

        viewController.setView("Menu", true);

    }

    public static void main(String[] args) {
        launch(args);
    }

}
