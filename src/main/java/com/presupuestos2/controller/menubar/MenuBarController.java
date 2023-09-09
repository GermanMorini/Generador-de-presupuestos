package com.presupuestos2.controller.menubar;

import com.itextpdf.text.DocumentException;
import com.presupuestos2.MainApplication;
import com.presupuestos2.controller.Controller;
import com.presupuestos2.model.Budget;
import com.presupuestos2.model.other.Dialog;
import com.presupuestos2.model.other.FileManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.presupuestos2.MainApplication.getSavePath;
import static com.presupuestos2.MainApplication.setSavePath;
import static com.presupuestos2.model.other.Dialog.showDirectoryChooserDialog;
import static com.presupuestos2.model.other.Dialog.showMessageDialog;

public class MenuBarController extends Controller implements Initializable {

        @Override
        public void initialize(URL location, ResourceBundle resources) {
                Controller.guardar = guardar;
                Controller.guardarLabel = guardarLabel;
                Controller.guardarComo = guardarComo;
                Controller.guardarComoLabel = guardarComoLabel;
                Controller.elegirDestino = elegirDestino;
                Controller.cargar = cargar;
        }

        @FXML protected MenuItem guardar, guardarComo, elegirDestino, cargar;
        @FXML protected Label guardarLabel, guardarComoLabel;

        @FXML
        protected void archivoAP() {
                if (allFieldsCompleted()) {
                        guardar.setDisable(false);
                        guardarComo.setDisable(false);
                        guardarLabel.setText("");
                        guardarComoLabel.setText("");
                } else {
                        guardar.setDisable(true);
                        guardarComo.setDisable(true);
                        guardarLabel.setText("(faltan rellenar campos)");
                        guardarComoLabel.setText("(faltan rellenar campos)");
                }
        }

        @FXML
        protected void elegirDestinoAP() {
                File selectedDirectory = showDirectoryChooserDialog("Elegir destino");

                if (selectedDirectory != null) {
                        setSavePath(selectedDirectory.getPath());
                        destino.setText(selectedDirectory.getPath());
                } else {
                        showMessageDialog(Alert.AlertType.WARNING, "Advertencia", "No se ha seleccionado ninguna ruta.\nLa ruta de guardado no ha cambiado\n" + getSavePath(), "Advertencia");
                }
        }

        @FXML
        protected void cargarAP() {
                try {
                        File selectedFile = Dialog.showFileChooserDialog("Cargar presupuesto", new File(System.getProperty("user.home")));
                        Budget b = FileManager.loadFile(selectedFile.getPath());
                        destino.setText(selectedFile.getParent());

                        Controller.clearEntries();
                        Controller.fillEntries(b);
                } catch (IOException e) {
                        e.printStackTrace();
                        Dialog.showMessageDialog(Alert.AlertType.ERROR, e.getClass().getSimpleName(), e.getMessage(), "Error");
                }
        }

        @FXML
        protected void guardarAP() {
                try {
                        String savepath = MainApplication.getSavePath() + File.separator + cliente.getText().strip() + ".pdf";
                        FileManager.createFile(savepath, Controller.generateBudget());

                        Dialog.showMessageDialog(Alert.AlertType.INFORMATION, "Información", "PDF generado con éxito en " + savepath, "Información");
                } catch  (IOException | DocumentException e) {
                        e.printStackTrace();
                        Dialog.showMessageDialog(Alert.AlertType.ERROR, e.getClass().getSimpleName(), "Error al generar el PDF", "Error");
                }
        }

        @FXML
        protected void guardarComoAP() {
                String oldSavePath = MainApplication.getSavePath();
                elegirDestinoAP();

                if (!oldSavePath.equals(MainApplication.getSavePath())) {
                        guardarAP();
                }
        }

        @FXML
        protected void borrarTodoAP() {
                Dialog.showConfirmDialog("Deseas borrar todas las entradas?", "Advertencia", response -> {
                        if (response == javafx.scene.control.ButtonType.OK) Controller.clearEntries();
                });
        }

        @FXML
        protected void colorAP() {
                //TODO: escribir función para el menu item color
                System.out.println("TODO: escribir función para el menu item color");
        }
}
