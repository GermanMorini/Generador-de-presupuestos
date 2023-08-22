package com.presupuestos2.model.pdffile;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

public class Item extends Text {

    private static Font itemFont = FontFactory.getFont(
            getDefaultFont().getFamilyname(),
            getDefaultFont().getSize() - 2,
            Font.NORMAL
    );

    public Item(String text, char symbol) {
        super(symbol + " " + text, itemFont, getSpacing() / 4, 40);
    }
}
