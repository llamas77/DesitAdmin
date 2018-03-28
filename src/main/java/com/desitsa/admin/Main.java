package com.desitsa.admin;

import com.desitsa.admin.controllers.LoginController;
import com.desitsa.admin.controllers.MainController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    private static Main instance;
    private Stage stage;
    private ViewManager viewManager;

    @Override
    public void start(Stage primaryStage) throws Exception {
        instance = this;
        stage = primaryStage;
        viewManager = ViewManager.getInstance(primaryStage);

        // Configuraciones generales de la ventana
        stage.setResizable(false);
        stage.setTitle("Desit SA");

        // Primera ventana (login)
        setScene(getView(MainController.class, "main"));
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Cambia la escena
     * @param n
     */
    public void setScene(Parent n) {
        stage.setScene(new Scene(n));
        stage.show();
        stage.centerOnScreen();
    }

    public Parent getView(Class c) {
        return getView(c, null);
    }

    public Parent getView(Class c, String id) {
        Parent p = null;
        try {
            p = viewManager.getView(c, id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }

    public Stage getStage() {
        return stage;
    }

    public static Main getInstance() {
        return instance;
    }

}
