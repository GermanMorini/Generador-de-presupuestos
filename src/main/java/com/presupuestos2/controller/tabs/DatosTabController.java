package com.presupuestos2.controller.tabs;

import com.presupuestos2.MainApplication;
import com.presupuestos2.controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

    @FXML protected TextField cliente, total, destino;
    @FXML protected DatePicker fecha;

    @FXML
    protected void destinoC() {
        elegirDestino.fire();
    }

    @FXML
    protected void guardarBtnAP() {
        guardar.fire();
    }

    @FXML
    protected void guardarComoBtnAP() {
        guardarComo.fire();
    }

    @FXML
    protected void cargarBtnAP() {
        cargar.fire();
    }
}
