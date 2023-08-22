package com.presupuestos2.model.pdffile;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;

public class Text extends Paragraph {

    private static Font defaultFont =  FontFactory.getFont(Font.FontFamily.HELVETICA.name(), 14, Font.UNDERLINE);
    private static int spacing = 8;
    private static int indentation = 10;
    private static int pos = ALIGN_LEFT;

    public Text(String text) {
        setSpacingBefore(spacing);
        setSpacingAfter(spacing);
        setIndentationLeft(indentation);
        setAlignment(pos);
        setFont(defaultFont);
        add(text);
    }

    public Text(String text, int spacing) {
        setSpacingBefore(spacing);
        setSpacingAfter(spacing);
        setIndentationLeft(indentation);
        setAlignment(pos);
        setFont(defaultFont);
        add(text);
    }

    public Text(String text, int pos, int size) {
        setSpacingBefore(spacing);
        setSpacingAfter(spacing);
        setIndentationLeft(indentation);
        setAlignment(pos);
        setFont(FontFactory.getFont(defaultFont.getFamilyname(), size, defaultFont.getStyle()));
        add(text);
    }

    public Text(String text, Font font, int spacing, int indentation) {
        setSpacingBefore(spacing);
        setSpacingAfter(spacing);
        setIndentationLeft(indentation);
        setAlignment(pos);
        setFont(font);
        add(text);
    }

    public static Font getDefaultFont() {
        return defaultFont;
    }

    public static int getSpacing() {
        return spacing;
    }

    public static int getIndentation() {
        return indentation;
    }

    public static int getPos() {
        return pos;
    }
}
