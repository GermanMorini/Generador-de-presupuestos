package com.presupuestos2.controller.tabs;

import com.presupuestos2.MainApplication;
import com.presupuestos2.controller.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DatosTabController extends MainController implements Initializable {

        @Override
        public void initialize(URL location, ResourceBundle resources) {
                MainController.cliente = cliente;
                MainController.fecha = fecha;
                MainController.total = total;
                MainController.destino = destino;

                destino.setText(MainApplication.getSavePath());
        }

        @FXML private TextField cliente, total, destino;
        @FXML private DatePicker fecha;

        @FXML
        private void destinoC() {
                elegirDestino.fire();
        }
}
