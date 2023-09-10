package com.presupuestos2.controller.tabs;

import com.presupuestos2.MainApplication;
import com.presupuestos2.controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DatosTabController extends Controller implements Initializable {

        @Override
        public void initialize(URL location, ResourceBundle resources) {
                Controller.cliente = cliente;
                Controller.fecha = fecha;
                Controller.total = total;
                Controller.destino = destino;

                destino.setText(MainApplication.getSavePath());
        }

        @FXML private TextField cliente, total, destino;
        @FXML private DatePicker fecha;

        @FXML
        private void destinoC() {
                elegirDestino.fire();
        }
}
