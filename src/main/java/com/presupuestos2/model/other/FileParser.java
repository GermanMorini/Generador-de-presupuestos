package com.presupuestos2.model.other;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.presupuestos2.model.Budget;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.io.FileInputStream;
import java.util.Arrays;

public class FileParser {

        public static Budget readPDF(String savePath) {
                try {
                        PdfReader reader = new PdfReader(new FileInputStream(savePath));
                        String pdfContents = PdfTextExtractor.getTextFromPage(reader, 1);
                        return getBudget(pdfContents);
                } catch (IOException e) {
                        e.printStackTrace();
                        Dialog.showMessageDialog(Alert.AlertType.ERROR, e.getClass().getSimpleName(), e.getMessage(), "Error");
                        return null;
                }
        }

        private static Budget getBudget(String pdfContents) {
                int clienteIndx = pdfContents.indexOf("Cliente: ");
                int fechaIndx = pdfContents.indexOf("Fecha: ");
                int trabajosIndx = pdfContents.indexOf("Trabajos:");
                int detallesIndx= pdfContents.indexOf("Detalles:");
                int totalIndx = pdfContents.indexOf("TOTAL PRESUPUESTO: $");
                boolean hasDetalles = detallesIndx > 0;

                String cliente = pdfContents.substring(clienteIndx, fechaIndx - 1).split(": ")[1];
                String fecha = pdfContents.substring(fechaIndx, trabajosIndx - 1).split(": ")[1];
                String total = pdfContents.substring(totalIndx).split(": ")[1].substring(1);
                String[] trabajos = {};
                String[] detalles = {};

                if (hasDetalles) {
                        trabajos = getTableContentFromString(pdfContents, trabajosIndx, detallesIndx - 1);
                        detalles = getTableContentFromString(pdfContents, detallesIndx, totalIndx - 1);
                } else {
                        trabajos = getTableContentFromString(pdfContents, trabajosIndx, totalIndx - 1);
                }

                return new Budget(cliente, fecha, total, trabajos, detalles);
        }

        private static String[] getTableContentFromString(String pdfContents, int beginIndx, int endIndx) {
                String[] rtn;
                rtn = pdfContents.substring(beginIndx, endIndx).split("• ");

                // Eliminar el título de la tabla (primer índice)
                rtn = Arrays.copyOfRange(rtn, 1, rtn.length);

                // Retirar los saltos de línea al final de cada trabajo (el útimo trabajo no tiene)
                for (int i = 0; i < rtn.length - 1; i++) {
                        rtn[i] = rtn[i].substring(0, rtn[i].length() - 1);
                }

                return rtn;
        }
}
