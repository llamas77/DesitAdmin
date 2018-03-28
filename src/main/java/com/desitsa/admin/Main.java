package com.desitsa.admin;

import com.desitsa.admin.controllers.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    private ViewManager viewManager;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Configuraciones generales de la ventana
        primaryStage.setResizable(false);

        viewManager = ViewManager.getInstance(primaryStage);
        viewManager.setTitle("Desit SA");

        viewManager.setView(LoginController.class, true);


    }

    public static void main(String[] args) {
        launch(args);
    }

}
