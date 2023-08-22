package com.presupuestos2.controller.tabs;

import com.presupuestos2.controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.text.DecimalFormat;

public class CalculadoraTabController extends Controller {

    private String answer;
    private static int precision = 2;

    private void insertarTexto(String txt) {
        pantalla.insertText(pantalla.getCaretPosition(), txt);
    }

    private void shiftMod(Button b, String s1, EventHandler<ActionEvent> e1, String s2, EventHandler<ActionEvent> e2) {
        if(!shift.isSelected()) {
            b.setFont(new Font("System", 30));
            b.setText(s1);
            b.setOnAction(e1);
        } else {
            b.setFont(new Font("System", 20));
            b.setText(s2);
            b.setOnAction(e2);
        }
    }

    private void reemplazarCaracteres() {
        pantalla.setText(pantalla.getText().replaceAll(",", "."));
        pantalla.setText(pantalla.getText().replaceAll("%", "*1/(100)*"));
        pantalla.setText(pantalla.getText().replaceAll("√", "sqrt"));
    }

    public static void setPrecision(int p) {
        precision = p;
    }

    @FXML
    protected Button suma, mult, div;
    @FXML
    protected ToggleButton shift;
    @FXML
    protected TextField pantalla;
    @FXML
    protected ContextMenu pantallaCM;

    // Nos aseguramos que la pantalla siempre tenga el foco
    @FXML
    protected void anchorPaneMC() {
        pantalla.requestFocus();
        pantalla.end();
    }


    // FLECHAS DE DIRECCIÓN
    @FXML
    protected void rightArrowAP() {
        pantalla.requestFocus();
        pantalla.positionCaret(pantalla.getCaretPosition() + 1);
    }
    @FXML
    protected void leftArrowAP() {
        pantalla.requestFocus();
        pantalla.positionCaret(pantalla.getCaretPosition() - 1);
    }


    // OPERADORES
    @FXML
    protected void shiftC() {
        shiftMod(suma,
                "+", e -> {
                    e.consume();
                    insertarTexto("+");
                    suma.tooltipProperty().set(new Tooltip("Suma"));
                },
                "abs", e -> {
                    e.consume();
                    insertarTexto("abs()");
                    pantalla.positionCaret(pantalla.getCaretPosition() - 1);
                    suma.tooltipProperty().set(new Tooltip("Valor absoluto"));
                } );

        shiftMod(mult,
                "×", e -> {
                    e.consume();
                    insertarTexto("*");
                    suma.tooltipProperty().set(new Tooltip("Multiplicacion"));
                },
                "^", e -> {
                    e.consume();
                    insertarTexto("^");
                    suma.tooltipProperty().set(new Tooltip("Potencia"));
                });

        shiftMod(div,
                "÷", e -> {
                    e.consume();
                    insertarTexto("/");
                    suma.tooltipProperty().set(new Tooltip("Division"));
                },
                "√", e -> {
                    e.consume();
                    insertarTexto("√()");
                    pantalla.positionCaret(pantalla.getCaretPosition() - 1);
                    suma.tooltipProperty().set(new Tooltip("Raiz cuadrada"));
                });
    }
    @FXML
    protected void resAP() {
        // El MenuItem del historial
        MenuItem mi = new MenuItem();
        mi.setText(pantalla.getText() + " = ");

        // Reemplaza los operadores especiales por su expresión equivalente
        reemplazarCaracteres();

        try {
            Expression e = new ExpressionBuilder(pantalla.getText()).build();
            DecimalFormat df = new DecimalFormat("#." + "#".repeat(precision));
            pantalla.setText(df.format(e.evaluate()));
        } catch(Exception e) {
            pantalla.setText("ERR");
        }

        answer = pantalla.getText();

        // Se termina de crear el MenuItem
        mi.setText(mi.getText() + answer);
        mi.setOnAction(e -> {
            e.consume();
            // Se queda con el resultado
            insertarTexto(mi.getText().substring(mi.getText().indexOf(" = ") + 3));
        });
        pantallaCM.getItems().add(mi);
    }
    @FXML
    protected void delAP() {pantalla.clear();}
    @FXML
    protected void borrarAP() {pantalla.deletePreviousChar();}
    @FXML
    protected void ansAP() {insertarTexto(answer);}
    @FXML
    protected void puntoAP() {insertarTexto(",");}
    @FXML
    protected void sumaAP() {insertarTexto("+");}
    @FXML
    protected void restaAP() {insertarTexto("-");}
    @FXML
    protected void multAP() {insertarTexto("*");}
    @FXML
    protected void divAP() {insertarTexto("/");}
    @FXML
    protected void par1AP() {insertarTexto("(");}
    @FXML
    protected void par2AP() {insertarTexto(")");}
    @FXML
    protected void porcAP() {insertarTexto("%");}


    // NÚMEROS
    @FXML
    protected void _1AP() {insertarTexto("1");}
    @FXML
    protected void _2AP() {insertarTexto("2");}
    @FXML
    protected void _3AP() {insertarTexto("3");}
    @FXML
    protected void _4AP() {insertarTexto("4");}
    @FXML
    protected void _5AP() {insertarTexto("5");}
    @FXML
    protected void _6AP() {insertarTexto("6");}
    @FXML
    protected void _7AP() {insertarTexto("7");}
    @FXML
    protected void _8AP() {insertarTexto("8");}
    @FXML
    protected void _9AP() {insertarTexto("9");}
    @FXML
    protected void _0AP() {insertarTexto("0");}
    @FXML
    protected void _00AP() {insertarTexto("00");}
}
