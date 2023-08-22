package com.presupuestos2.controller.menubar;

import com.presupuestos2.MainApplication;
import com.presupuestos2.controller.Controller;
import com.presupuestos2.model.Budget;
import com.presupuestos2.model.other.Dialog;
import com.presupuestos2.model.other.Stream;
import com.presupuestos2.model.pdffile.Img;
import com.presupuestos2.model.pdffile.MainTable;
import com.presupuestos2.model.pdffile.PDFDocument;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

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

    private boolean allFieldsCompleted() {
        return cliente.getText() != null && !cliente.getText().isBlank() &&
                total.getText() != null && !total.getText().isBlank() &&
                Controller.getTableContent(trabajosTable).length != 0 &&
                fecha.getEditor().getText() != null && !fecha.getEditor().getText().isBlank();
    }

    @FXML
    protected void elegirDestinoAP() {
        ElegirDestinoAction.performAction();
    }

    @FXML
    protected void cargarAP() {
        CargarAction.performAction();
    }

    @FXML
    protected void guardarAP() {
        GuardarAction.performAction();
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
            if (response == javafx.scene.control.ButtonType.OK)
                BorrarTodoAction.performAction();
        });
    }

    @FXML
    protected void colorAP() {
        //TODO: escribir función para el menu item color
        System.out.println("TODO: escribir función para el menu item color");
    }

}

class GuardarAction extends Controller {
    public static void performAction() {
        String filePath = MainApplication.getSavePath() + File.separator + cliente.getText().strip() + ".pdf";
        generatePDFDocument(filePath);
    }

    private static void generatePDFDocument(String filePath) {
        try {
            PDFDocument pdf = new PDFDocument(filePath);
            pdf.create(
                    new Img(MainApplication.class.getResource("Data.png").getPath()).getImg(),
                    new MainTable(
                            cliente.getText().strip(),
                            fecha.getEditor().getText().strip(),
                            total.getText().strip(),
                            getTableContent(trabajosTable),
                            getTableContent(detallesTable))
            );
            Dialog.showMessageDialog(Alert.AlertType.INFORMATION, "Información", "PDF generado con éxito en " + filePath, "Información");
        } catch (Exception e) {
            e.printStackTrace();
            Dialog.showMessageDialog(Alert.AlertType.ERROR, e.getClass().getSimpleName(), "Error al generar el PDF", "Error");
        }
    }
}

class ElegirDestinoAction extends Controller {
    public static void performAction() {
        File selectedDirectory = showDirectoryChooserDialog("Elegir destino");

        if (selectedDirectory != null) {
            setSavePath(selectedDirectory.getPath());
            destino.setText(selectedDirectory.getPath());
        } else {
            showMessageDialog(Alert.AlertType.WARNING, "Advertencia", "No se ha seleccionado ninguna ruta.\nLa ruta de guardado no ha cambiado\n" + getSavePath(), "Advertencia");
        }
    }
}

class CargarAction extends Controller {
    public static void performAction() {
        File selectedFile = Dialog.showFileChooserDialog("Cargar presupuesto", new File(System.getProperty("user.home")));
        if (selectedFile != null) loadBudget(selectedFile);
    }

    private static void loadBudget(File selectedFile) {
        try {
            BorrarTodoAction.performAction();
            fillEntries(Stream.readPDF(selectedFile.getPath()));
            destino.setText(selectedFile.getParent());
        } catch (IOException e) {
            e.printStackTrace();
            Dialog.showMessageDialog(Alert.AlertType.ERROR, e.getClass().getSimpleName(), e.getMessage(), "Error");
        }
    }

    private static void fillEntries(Budget budget) {
        cliente.setText(budget.getCliente());
        fecha.getEditor().setText(budget.getFecha());
        total.setText(budget.getTotal());
        Controller.setTableContent(trabajosTable, budget.getTrabajos());
        Controller.setTableContent(detallesTable, budget.getDetalles());
    }
}

class BorrarTodoAction extends Controller {
    public static void performAction() {
        cliente.setText("");
        fecha.getEditor().setText("");
        total.setText("");
        clearTable(trabajosTable);
        clearTable(detallesTable);
    }

    private static void clearTable(Pane table) {
        table.getChildren().forEach(field -> {
            ((TextField) field).setText("");
        });
    }
}
