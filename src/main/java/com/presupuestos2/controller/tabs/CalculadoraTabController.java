package com.presupuestos2.controller.tabs;

import com.presupuestos2.controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class CalculadoraTabController extends Controller implements Initializable {

        @Override
        public void initialize(URL location, ResourceBundle resources) {
                precision.getEditor().setText("1");
        }

        private String answer;
        private static int PRECISION = 2;

        private void insertText(String txt) {
                pantalla.insertText(pantalla.getCaretPosition(), txt);
        }

        // PERMITE AÑADIR LAS FUNCIONALIDADES A LAS TECLAS QUE CAMBIAN AL APRETAR SHIFT
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

        // REEMPLAZA CIERTOS CARACTERES POR SU EXPRESIÓN MATEMÁTICA
        private void replaceChars() {
                pantalla.setText(pantalla.getText().replaceAll(",", "."));
                pantalla.setText(pantalla.getText().replaceAll("%", "*1/(100)*"));
                pantalla.setText(pantalla.getText().replaceAll("√", "sqrt"));
                pantalla.setText(pantalla.getText().replaceAll("º", "*(pi/180)"));
                pantalla.setText(pantalla.getText().replaceAll("π", "pi"));
        }

        @FXML
        private Button suma, mult, div, resta, par1, par2, punto, _0, _00;
        @FXML
        private Spinner<String> precision;
        @FXML
        private ToggleButton shift;
        @FXML
        private TextField pantalla;
        @FXML
        private ContextMenu pantallaCM;


        // MANTIENE EL FOCO EN LA PANTALLA
        @FXML
        private void anchorPaneMC() {
                pantalla.requestFocus();
                pantalla.end();
        }
        @FXML
        private void precisionMC() {
                System.out.println("awdawd");
        }


        // FLECHAS DE DIRECCIÓN
        @FXML
        private void rightArrowAP() {
                pantalla.requestFocus();
                pantalla.positionCaret(pantalla.getCaretPosition() + 1);
        }
        @FXML
        private void leftArrowAP() {
                pantalla.requestFocus();
                pantalla.positionCaret(pantalla.getCaretPosition() - 1);
        }


        // OPERADORES
        @FXML
        private void shiftC() {
                shiftMod(suma,
                            "+", e -> {
                                    e.consume();
                                    insertText("+");
                            },
                            "abs", e -> {
                                    e.consume();
                                    insertText("abs()");
                                    pantalla.positionCaret(pantalla.getCaretPosition() - 1);
                            });

                shiftMod(resta,
                            "-", e -> {
                                    e.consume();
                                    insertText("-");
                                },
                            "tan", e -> {
                                    e.consume();
                                    insertText("tan(º)");
                                    pantalla.positionCaret(pantalla.getCaretPosition() - 2);
                            });

                shiftMod(mult,
                            "×", e -> {
                                    e.consume();
                                    insertText("*");
                            },
                            "^", e -> {
                                    e.consume();
                                    insertText("^");
                            });

                shiftMod(div,
                            "÷", e -> {
                                    e.consume();
                                    insertText("/");
                            },
                            "√", e -> {
                                    e.consume();
                                    insertText("√()");
                                    pantalla.positionCaret(pantalla.getCaretPosition() - 1);
                            });

                shiftMod(par1,
                            "(", e -> {
                                    e.consume();
                                    insertText("(");
                                },
                            "sin", e -> {
                                    e.consume();
                                    insertText("sin(º)");
                                    pantalla.positionCaret(pantalla.getCaretPosition() - 2);
                            });

                shiftMod(par2,
                            ")", e -> {
                                    e.consume();
                                    insertText(")");
                                },
                            "cos", e -> {
                                    e.consume();
                                    insertText("cos(º)");
                                    pantalla.positionCaret(pantalla.getCaretPosition() - 2);
                            });

                shiftMod(punto,
                            ",", e -> {
                                    e.consume();
                                    insertText(",");
                                },
                            "º", e -> {
                                    e.consume();
                                    insertText("º");
                            });

                shiftMod(_0,
                            "0", e -> {
                                    e.consume();
                                    insertText("0");
                                },
                            "e", e -> {
                                    e.consume();
                                    insertText("e");
                            });

                shiftMod(_00,
                            "00", e -> {
                                    e.consume();
                                    insertText("00");
                                },
                            "π", e -> {
                                    e.consume();
                                    insertText("π");
                            });
        }
        @FXML
        private void resAP() {
                // El MenuItem del historial
                MenuItem mi = new MenuItem();
                mi.setText(pantalla.getText() + " = ");

                // Reemplaza los operadores especiales por su expresión equivalente
                replaceChars();

                try {
                        Expression e = new ExpressionBuilder(pantalla.getText()).build();
                        DecimalFormat df = new DecimalFormat("#." + "#".repeat(PRECISION));
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
                        insertText(mi.getText().substring(mi.getText().indexOf(" = ") + 3));
                });
                pantallaCM.getItems().add(mi);
        }
        @FXML
        private void delAP() {pantalla.clear();}
        @FXML
        private void borrarAP() {pantalla.deletePreviousChar();}
        @FXML
        private void ansAP() {insertText(answer);}
        @FXML
        private void puntoAP() {insertText(",");}
        @FXML
        private void sumaAP() {insertText("+");}
        @FXML
        private void restaAP() {insertText("-");}
        @FXML
        private void multAP() {insertText("*");}
        @FXML
        private void divAP() {insertText("/");}
        @FXML
        private void par1AP() {insertText("(");}
        @FXML
        private void par2AP() {insertText(")");}
        @FXML
        private void porcAP() {insertText("%");}


        // NÚMEROS
        @FXML
        private void _1AP() {insertText("1");}
        @FXML
        private void _2AP() {insertText("2");}
        @FXML
        private void _3AP() {insertText("3");}
        @FXML
        private void _4AP() {insertText("4");}
        @FXML
        private void _5AP() {insertText("5");}
        @FXML
        private void _6AP() {insertText("6");}
        @FXML
        private void _7AP() {insertText("7");}
        @FXML
        private void _8AP() {insertText("8");}
        @FXML
        private void _9AP() {insertText("9");}
        @FXML
        private void _0AP() {insertText("0");}
        @FXML
        private void _00AP() {insertText("00");}
}
