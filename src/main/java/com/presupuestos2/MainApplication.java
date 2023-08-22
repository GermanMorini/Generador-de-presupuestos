package com.presupuestos2;

import com.presupuestos2.model.other.Dialog;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainApplication extends Application {

    private static String savePath = System.getProperty("user.home");

    public static void setSavePath(String savePath) {
        MainApplication.savePath = savePath;
    }

    public static String getSavePath() {
        return savePath;
    }

    @Override
    public void start(Stage stage) throws IOException {
        URL sceneLocation = MainApplication.class.getResource("main-view.fxml");
        Scene scene = new Scene(FXMLLoader.load(sceneLocation), 800, 700);
        stage.setTitle("Presupuestos");
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> {
            e.consume();
            Dialog.showConfirmDialog("Deseas salir?", "Salir", response -> {
                if (response == ButtonType.OK) {
                    stage.close();
                }
            });
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
