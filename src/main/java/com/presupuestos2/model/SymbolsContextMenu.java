package com.presupuestos2.model;

import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.Pane;

public class SymbolsContextMenu extends ContextMenu {

        private final String[] SYMBOLS = {"Ø", "/", "&", "$", "∡", "⟂", "∥", "∦"};

        public SymbolsContextMenu(Pane pane) {
                for(String s : SYMBOLS) {
                        MenuItem mi = new MenuItem(s);

                        mi.setOnAction(e -> {
                                e.consume();
                                TextInputControl tf = (TextInputControl) pane.getChildren().filtered(Node::isFocused).get(0);
                                tf.insertText(tf.getCaretPosition(), s);
                        });

                        getItems().add(mi);
                }
        }

        public SymbolsContextMenu() {

        }
}
