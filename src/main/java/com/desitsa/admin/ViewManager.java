package com.desitsa.admin;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

/**
 * Administrador de vistas
 */
public class ViewManager {

    private static ViewManager instance;
    private HashMap<String, Parent> views;

    /**
     * Obtiene la instancia del controlador de vistas.
     */
    public static ViewManager getInstance(Stage stage) {
        if (instance == null) instance = new ViewManager();
        return instance;
    }

    private ViewManager() {
        views = new HashMap<>();
    }

    /**
     * Obtiene una lista
     *
     * Si se indica ID, puede existir y devolverse directamente... o puede no existir, crearse, guardarse y devolverse.
     * Si no se indica ID, se crea siempre sin guardar nada
     *
     * @param c clase controladora de la vista.
     * @param id identificador (opcional)
     * @return vista
     * @throws IOException
     */
    public Parent getView(Class c, String id) throws IOException {

        // Chequea si el nombre de la clase es válido.
        String name = c.getSimpleName();
        int index = name.toLowerCase().indexOf("controller");

        if (index == -1)
            throw new IllegalArgumentException("La clase no es controladora de una vista");
        else
            name = name.substring(0, index);

        Parent p = null;


        p = views.get(id);

        // Si tiene ID y existe la vista
        if (id != null && p != null)
            return p;

        // Si tiene ID pero no existe la vista, o si no tiene ID.
        p = FXMLLoader.load(getClass().getResource("/views/" + name + ".fxml"));
        if (id != null) views.put(id, p);
        return p;
    }

    /**
     * Borra una vista guardada
     * (recomendable usar cada vez que se quiera cambiar de vista y no interese más su información)
     * @param id
     */
    public void deleteView(String id) {
        views.remove(id);
    }

}
