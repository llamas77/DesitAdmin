package com.desitsa.admin;

/**
 *
 */
public interface Model {
    /**
     * Crea un registro de la tabla
     */
    void create();

    /**
     * Modifica un registro de la tabla
     */
    void update();

    /**
     * Elimina un registro de la tabla
     */
    void delete();

    /**
     * Actualiza el objeto, indicando si existe o no
     * @return
     */
    boolean updateObject();
}
