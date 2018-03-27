package com.desitsa.admin;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class ViewController {

    private static ViewController instance;
    private Stage stage;
    private HashMap<String, Scene> views;

    /**
     * Obtiene la instancia del controlador de vistas.
     */
    public static ViewController getInstance(Stage stage) {
        if (instance == null) instance = new ViewController(stage);
        return instance;
    }

    private ViewController(Stage stage) {
        this.stage = stage;
        views = new HashMap<>();
    }

    public void setView(Class c) throws IOException, IllegalArgumentException {
        setView(c, false);
    }

    /**
     * Cambia de vista
     * @param c clase controladora de la vista
     * @param newView indica si se tiene que crear una nueva vista o usar una existente si es que existe.
     * @throws IOException
     * @throws IllegalArgumentException
     */
    public void setView(Class c, boolean newView) throws IOException, IllegalArgumentException {
        String name = c.getSimpleName();
        int index = name.toLowerCase().indexOf("controller");

        // Si la clase termina en Controller, pero no es éste gestor, es válido.
        if (index != -1 && !name.equals(getClass().getSimpleName()))
            setView(name.substring(0, index), newView);
        else
            throw new IllegalArgumentException("La clase no es controladora de una vista");
    }

    public void setView(String viewName) throws IOException {
        setView(viewName, false);
    }

    /**
     * Cambia de vista
     * @param viewName nombre de la vista
     * @param newView indica si se tiene que crear una nueva vista o usar una existente si es que existe.
     * @throws IOException
     */
    public void setView(String viewName, boolean newView) throws IOException {
        // Obtengo la escena
        Scene s = views.get(viewName);

        // Si es nula la creo
        if (s == null || newView) {
            Parent root = FXMLLoader.load(getClass().getResource("/views/" + viewName + ".fxml"));
            System.out.println("/views/" + viewName + ".fxml");
            s = new Scene(root);
            views.put(viewName, s);
        }

        stage.setScene(s);
        stage.show();
        stage.centerOnScreen();
    }

    public Stage getStage() {
        return stage;
    }

    public void setTitle(String title) {
        stage.setTitle(title);
    }
}
