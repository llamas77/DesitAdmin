package com.desitsa.admin.models;

import com.desitsa.admin.DBAccess;
import com.desitsa.admin.Main;
import com.desitsa.admin.Model;

import java.sql.*;

public class Usuario implements Model {
    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;
    private String contraseña;



    public Usuario(Integer id, String nombre, String apellido, String dni, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.contraseña = contraseña;
    }

    public Integer getId() { return id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }

    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getDni() { return dni; }

    public void setDni(String dni) { this.dni = dni; }

    public String getContraseña() { return contraseña; }

    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    /**
     * Busca un usuario por ID
     * @param id
     * @return
     */
    public static Usuario get(int id) {
        DBAccess db = Main.getInstance().getDbAccess();
        String sql = "SELECT usuario_ID, nombre, apellido, dni, contraseña FROM usuario WHERE usuario_ID = ?";

        try (Connection con = db.open(); PreparedStatement s = con.prepareStatement(sql)) {

            s.setInt(1, id);
            ResultSet rs = s.executeQuery();
            if (rs.next()) {
                return new Usuario(
                        rs.getInt("usuario_ID"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("dni"),
                        rs.getString("contraseña")
                );
            }

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean updateObject() {
        if (id == null) return false;

        DBAccess db = Main.getInstance().getDbAccess();
        String sql = "SELECT usuario_ID, nombre, apellido, dni, contraseña FROM usuario WHERE usuario_ID = ?";


        return false; //todo: para que compile nomas!!!
    }

    @Override
    public void create() {
        if (id != null) return; // TODO: como avisarle?

        DBAccess db = Main.getInstance().getDbAccess();
        String sql = "INSERT INTO usuario (nombre, apellido, dni, contraseña) VALUES (?, ?, ?, ?)";
        String[] generatedColumns = {"ID"};

        try (Connection con = db.open(); PreparedStatement s = con.prepareStatement(sql, generatedColumns)) {

            s.setString(1, nombre);
            s.setString(2, apellido);
            s.setString(3, dni);
            s.setString(4, contraseña);

            s.executeUpdate();

            ResultSet rs = s.getGeneratedKeys();
            if (rs.next()){
                id = rs.getInt(1);
                System.out.println(id);
            }

        }

        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    @Override
    public void update() {
        if (id == null) return;

        DBAccess db = Main.getInstance().getDbAccess();
        String sql = "UPDATE usuario SET nombre=?, apellido=?, dni=?, contraseña=? WHERE usuario_ID = ?";
        //String sql = "INSERT INTO usuario (nombre, apellido, dni, contraseña) VALUES (?, ?, ?, ?)";

        try (Connection con = db.open(); PreparedStatement s = con.prepareStatement(sql)) {

            s.setString(1, nombre);
            s.setString(2, apellido);
            s.setString(3, dni);
            s.setString(4, contraseña);

            s.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete() {

    }
}
