package com.presupuestos2.model.pdffile;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

public class InputText extends Text {

        public InputText(String title, String text) {
                super(title);
                setFont(FontFactory.getFont(font.getFamilyname(), font.getSize(), Font.NORMAL));
                add(": " + text);
        }

        public InputText(String title, String text, int spacing) {
                super(title, spacing);
                setFont(FontFactory.getFont(font.getFamilyname(), font.getSize(), Font.NORMAL));
                add(": " + text);
        }
}
