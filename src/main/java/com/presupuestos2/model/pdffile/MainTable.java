package com.presupuestos2.model.pdffile;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;

public class MainTable extends PdfPTable {

    final private PdfPCell content;

    public MainTable(
            String cliente,
            String fecha,
            String total,
            String[] trabajos,
            String[] detalles)
    {
        super(1);
        setSpacingBefore(10);
        setSpacingAfter(10);
        setWidthPercentage(85);

        content = new PdfPCell();

        content.addElement(new Text("Presupuesto", ALIGN_CENTER, (int) Text.getDefaultFont().getSize() + 2));
        content.addElement(new InputText("Cliente", cliente));
        content.addElement(new InputText("Fecha", fecha));
        content.addElement(new Text("Trabajos:"));
        addList(trabajos);

        if (!(detalles.length == 0)) {
            content.addElement(new Text("Detalles:"));
            addList(detalles);
        }

        content.addElement(new InputText("TOTAL PRESUPUESTO", "$" + total, Text.getSpacing() + 10));

        addCell(content);
    }

    private void addList(String[] list) {
        for (String elem : list) {
            content.addElement(new Item(elem, '•'));
        }
    }
}
