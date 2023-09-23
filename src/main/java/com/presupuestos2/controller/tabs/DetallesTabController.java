package com.presupuestos2.controller.tabs;

import com.presupuestos2.controller.MainController;
import com.presupuestos2.model.SymbolsContextMenu;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class DetallesTabController extends MainController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainController.detallesTable = detallesTable;

        SymbolsContextMenu cm = new SymbolsContextMenu(detallesTable);

        detallesTable.getChildren().forEach(n -> {
            TextField tf = (TextField) n;
            tf.setContextMenu(cm);
        });
    }

    @FXML private VBox detallesTable;
}
