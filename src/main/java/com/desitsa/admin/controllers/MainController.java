package com.desitsa.admin.controllers;

import com.desitsa.admin.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private BorderPane bpMain;

    @FXML
    private MenuBar mbMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: solo de test!!! BORRAR
        bpMain.setCenter(Main.getInstance().getView(LoginController.class, "login"));
    }

}
