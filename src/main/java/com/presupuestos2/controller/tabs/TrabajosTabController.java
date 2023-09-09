package com.presupuestos2.controller.tabs;

import com.presupuestos2.controller.Controller;
import com.presupuestos2.model.SymbolsContextMenu;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TrabajosTabController extends Controller implements Initializable {

        @Override
        public void initialize(URL location, ResourceBundle resources) {
                Controller.trabajosTable = trabajosTable;

                SymbolsContextMenu cm = new SymbolsContextMenu(trabajosTable);

                trabajosTable.getChildren().forEach(n -> {
                        TextField tf = (TextField) n;
                        tf.setContextMenu(cm);
                });
        }

        @FXML protected VBox trabajosTable;
}
