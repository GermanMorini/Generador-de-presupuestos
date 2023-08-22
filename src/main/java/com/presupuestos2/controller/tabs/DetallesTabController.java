package com.presupuestos2.controller.tabs;

import com.presupuestos2.controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class DetallesTabController extends Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Controller.detallesTable = detallesTable;

        // CREACIÓN DEL CONTEXTMENU
        ContextMenu cm = new ContextMenu();
        String[] symbols = {"Ø", "/", "&", "$", "∡", "⟂", "∥", "∦"};
        for(String s : symbols) {
            MenuItem mi = new MenuItem(s);
            mi.setOnAction(e -> {
                e.consume();
                TextField f = (TextField) detallesTable.getChildren().filtered(Node::isFocused).get(0);
                f.insertText(f.getCaretPosition(), s);
            });
            cm.getItems().add(mi);
        }

        // AÑADIMOS EL CM A CADA TEXTFIELD
        detallesTable.getChildren().forEach(n -> {
            TextField tf = (TextField) n;
            tf.setContextMenu(cm);
        });
    }

    @FXML protected VBox detallesTable;
}
