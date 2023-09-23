package com.presupuestos2.controller;

import com.presupuestos2.model.Budget;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class MainController {

    protected static TextField cliente, total, destino;
    protected static DatePicker fecha;
    protected static VBox trabajosTable, detallesTable;
    protected static MenuItem guardar, guardarComo, elegirDestino, cargar;
    protected static Label guardarLabel, guardarComoLabel;

    protected static String[] getTableContent(Pane table) {
        ArrayList<String> content = new ArrayList<>();

        table.getChildren().forEach(field -> {
            TextField tf = (TextField) field;
            if (tf.getText() != null && !tf.getText().isBlank()) {
                content.add(tf.getText().strip());
            }
        });

        return content.toArray(new String[] {});
    }

    protected static void setTableContent(Pane table, String[] content) {
        for (int i = 0; i < content.length; i++) {
            ((TextField) table.getChildren().get(i)).setText(content[i]);
        }
    }

    protected static void fillEntries(Budget budget) {
        cliente.setText(budget.getCliente());
        fecha.getEditor().setText(budget.getFecha());
        total.setText(budget.getTotal());
        setTableContent(trabajosTable, budget.getTrabajos());
        setTableContent(detallesTable, budget.getDetalles());
    }

    protected static void clearEntries() {
        cliente.setText("");
        fecha.getEditor().setText("");
        total.setText("");

        trabajosTable.getChildren().forEach(field -> ((TextField) field).setText(""));
        detallesTable.getChildren().forEach(field -> ((TextField) field).setText(""));
    }

    protected static boolean allFieldsCompleted() {
        return cliente.getText() != null && !cliente.getText().isBlank() &&
                    total.getText() != null && !total.getText().isBlank() &&
                    fecha.getEditor().getText() != null && !fecha.getEditor().getText().isBlank() &&
                    getTableContent(trabajosTable).length != 0;
    }

    protected static Budget generateBudget() {
        return new Budget(
                    cliente.getText().strip(),
                    fecha.getEditor().getText().strip(),
                    total.getText().strip(),
                    getTableContent(trabajosTable),
                    getTableContent(detallesTable)
        );
    }

    @FXML private AnchorPane mainAnchorPane;
}
