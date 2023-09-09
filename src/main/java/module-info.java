module com.presupuestos2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires itextpdf;
    requires exp4j;
    requires java.desktop;

    opens com.presupuestos2 to javafx.fxml;
    exports com.presupuestos2;
    opens com.presupuestos2.model to javafx.fxml;
    exports com.presupuestos2.model;
    opens com.presupuestos2.controller to javafx.fxml;
    exports com.presupuestos2.controller;
    opens com.presupuestos2.controller.menubar to javafx.fxml;
    exports com.presupuestos2.controller.menubar;
    opens com.presupuestos2.controller.tabs to javafx.fxml;
    exports com.presupuestos2.controller.tabs;
}