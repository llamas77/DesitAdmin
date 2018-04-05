package com.desitsa.admin;

import com.desitsa.admin.controllers.LoginController;
import com.desitsa.admin.models.Usuario;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    private static Main instance;
    private Stage stage;
    private ViewManager viewManager;
    private DBAccess dbAccess;

    @Override
    public void start(Stage primaryStage) throws Exception {
        instance = this;
        stage = primaryStage;
        viewManager = ViewManager.getInstance();

        // Configuraciones generales de la ventana
        stage.setResizable(false);
        stage.setTitle("Desit SA");

        // Inicialización del acceso a datos
        dbAccess = DBAccess.getInstance();

        // Primera ventana (login)
        setScene(getView(LoginController.class, "main"));

        Usuario u = new Usuario(null, "pedro", "gomez", "39691748", "asdasd");
        u.create();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Cambia la escena entera del Stage, por una vista (Parent) en especìfico.
     *
     * @param n vista
     */
    public void setScene(Parent n) {
        stage.close(); // TODO: es necesario? que diferencia hay? verlo..
        stage.setScene(new Scene(n));
        stage.show();
        stage.centerOnScreen();
    }

    public Parent getView(Class c) {
        return getView(c, null);
    }

    /**
     * Obtiene una vista
     *
     * Si se indica ID, puede existir y devolverse directamente... o puede no existir, crearse, guardarse y devolverse.
     * Si no se indica ID, se crea siempre sin guardar nada
     *
     * @param c clase controladora de la vista.
     * @param id identificador (opcional)
     * @return vista
     */
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

    public DBAccess getDbAccess() {
        return dbAccess;
    }

    public static Main getInstance() {
        return instance;
    }

}
