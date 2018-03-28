package com.desitsa.admin;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

/**
 * Administrador de vistas
 */
public class ViewManager {

    private static ViewManager instance;
    private Stage stage;
    private HashMap<String, Scene> views;
    private Scene view;

    /**
     * Obtiene la instancia del controlador de vistas.
     */
    public static ViewManager getInstance(Stage stage) {
        if (instance == null) instance = new ViewManager(stage);
        return instance;
    }

    private ViewManager(Stage stage) {
        this.stage = stage;
        views = new HashMap<>();
    }

    public void setView(Class c) throws IOException, IllegalArgumentException {
        setView(c, false);
    }

    /**
     * Cambia de vista
     * @param c clase controladora de la vista.
     * @param newView indica si se tiene que crear una nueva vista o usar una existente si es que existe.
     * @throws IOException
     */
    public void setView(Class c, boolean newView) throws IOException {

        String name = c.getSimpleName();
        int index = name.toLowerCase().indexOf("controller");

        // Si la clase termina en Controller, pero no es éste gestor, es válido.
        if (index == -1)
            throw new IllegalArgumentException("La clase no es controladora de una vista");
        else
            name = name.substring(0, index);

        // Obtengo la escena
        Scene s = views.get(name);

        // Si es nula la creo
        if (s == null || newView) {
            Parent root = FXMLLoader.load(getClass().getResource("/views/" + name + ".fxml"));
            System.out.println("/views/" + name + ".fxml");
            s = new Scene(root);

            views.put(name, s);
        }

        stage.setScene(s);
        stage.show();
        stage.centerOnScreen();
        view = s;

    }

    public Stage getStage() {
        return stage;
    }

    public Scene getView() {
        return view;
    }

    public void setTitle(String title) {
        stage.setTitle(title);
    }
}
