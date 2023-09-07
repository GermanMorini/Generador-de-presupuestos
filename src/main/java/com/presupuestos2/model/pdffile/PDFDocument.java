package com.presupuestos2.model.pdffile;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.presupuestos2.MainApplication;
import com.presupuestos2.model.Budget;
import com.presupuestos2.model.other.Dialog;
import javafx.scene.control.Alert;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PDFDocument extends Document {

        public PDFDocument(String savepath, Budget budget) {
                try {
                        PdfWriter.getInstance(this, new FileOutputStream(savepath));

                        open();
                        add(new Img(MainApplication.class.getResource("Data.png").getPath()).getImg());
                        add(new MainTable(budget));
                        close();

                        Dialog.showMessageDialog(Alert.AlertType.INFORMATION, "Información", "PDF generado con éxito en " + savepath, "Información");
                } catch  (IOException | DocumentException e) {
                        e.printStackTrace();
                        Dialog.showMessageDialog(Alert.AlertType.ERROR, e.getClass().getSimpleName(), "Error al generar el PDF", "Error");
                }
        }
}
