package com.presupuestos2.model.other;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.presupuestos2.MainApplication;
import com.presupuestos2.model.Budget;
import com.presupuestos2.model.pdffile.Img;
import com.presupuestos2.model.pdffile.MainTable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Arrays;

public class FileManager {

        public static void createFile(String savepath, Budget budget) throws IOException, DocumentException {
                Document doc = new Document();
                PdfWriter.getInstance(doc, new FileOutputStream(savepath));

                doc.open();
                doc.add(new Img(MainApplication.class.getResource("Data.png").getPath()).getImg());
                doc.add(new MainTable(budget));
                doc.close();
        }

        public static Budget loadFile(String savePath) throws IOException {
                PdfReader reader = new PdfReader(new FileInputStream(savePath));
                String pdfContents = PdfTextExtractor.getTextFromPage(reader, 1);
                return parseString(pdfContents);
        }

        // TODO: si hago algún cambio en el modelo del PDF esto debe cambiar
        private static Budget parseString(String pdfContents) {
                short clienteIndx = (short) pdfContents.indexOf("Cliente: ");
                short fechaIndx = (short) pdfContents.indexOf("Fecha: ");
                short trabajosIndx = (short) pdfContents.indexOf("Trabajos:");
                short detallesIndx= (short) pdfContents.indexOf("Detalles:");
                short totalIndx = (short) pdfContents.indexOf("TOTAL PRESUPUESTO: ");

                String cliente = pdfContents.substring(clienteIndx, fechaIndx - 1).split(": ")[1];
                String fecha = pdfContents.substring(fechaIndx, trabajosIndx - 1).split(": ")[1];
                String total = pdfContents.substring(totalIndx).split(": ")[1];
                String[] trabajos = {};
                String[] detalles = {};

                if (detallesIndx != 0) {
                        trabajos = getTableContentFromString(pdfContents, trabajosIndx, detallesIndx - 1);
                        detalles = getTableContentFromString(pdfContents, detallesIndx, totalIndx - 1);
                } else {
                        trabajos = getTableContentFromString(pdfContents, trabajosIndx, totalIndx - 1);
                }

                return new Budget(cliente, fecha, total, trabajos, detalles);
        }

        private static String[] getTableContentFromString(String pdfContents, int beginIndx, int endIndx) {
                String[] rtn = pdfContents.substring(beginIndx, endIndx).split("• ");

                // Eliminar el título de la tabla (primer índice)
                rtn = Arrays.copyOfRange(rtn, 1, rtn.length);

                // Retirar los saltos de línea al final de cada trabajo (el último trabajo no tiene)
                for (byte i = 0; i < rtn.length - 1; i++) {
                        rtn[i] = rtn[i].substring(0, rtn[i].length() - 1);
                }

                return rtn;
        }
}
