package com.presupuestos2.controller;

import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Controller {

    protected static TextField cliente, total, destino;
    protected static DatePicker fecha;
    protected static VBox trabajosTable, detallesTable;
    protected static MenuItem guardar, guardarComo, elegirDestino, cargar;
    protected static Label guardarLabel;
    protected static Label guardarComoLabel;

    public static String[] getTableContent(Pane table) {
        ArrayList<String> content = new ArrayList<>();

        table.getChildren().forEach(field -> {
            TextField tf = (TextField) field;
            if (tf.getText() != null && !tf.getText().isBlank()) {
                content.add(tf.getText().strip());
            }
        });

        return content.toArray(new String[] {});
    }

    public static void setTableContent(Pane table, String[] content) {
        for (int i = 0; i < content.length; i++) {
            ((TextField) table.getChildren().get(i)).setText(content[i]);
        }
    }
}
