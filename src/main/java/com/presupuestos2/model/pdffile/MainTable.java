package com.presupuestos2.model.pdffile;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.presupuestos2.model.Budget;

public class MainTable extends PdfPTable {

        final private PdfPCell content;

        public MainTable(Budget b)
        {
                super(1);
                setSpacingBefore(10);
                setSpacingAfter(10);
                setWidthPercentage(85);

                content = new PdfPCell();

                content.addElement(new Text("Presupuesto", ALIGN_CENTER, (int) Text.getDefaultFont().getSize() + 2));
                content.addElement(new InputText("Cliente", b.getCliente()));
                content.addElement(new InputText("Fecha", b.getFecha()));
                content.addElement(new Text("Trabajos:"));
                addList(b.getTrabajos());

                if (!(b.getDetalles().length == 0)) {
                        content.addElement(new Text("Detalles:"));
                        addList(b.getDetalles());
                }

                content.addElement(new InputText("TOTAL PRESUPUESTO", b.getTotal(), Text.getSpacing() + 10));

                addCell(content);
        }

        private void addList(String[] list) {
                for (String elem : list) {
                        content.addElement(new Item(elem, '•'));
                }
        }
}
