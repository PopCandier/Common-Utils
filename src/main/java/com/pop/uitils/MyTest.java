package com.pop.uitils;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Pop
 * @date 2019/3/23 0:34
 */
public class MyTest {

    @Test
    public void TestPdfUtils() throws IOException, DocumentException {
        File file = new File("/demo.pdf");
        System.out.println(file.getAbsolutePath());
        FileOutputStream os = new FileOutputStream(file);
        PDFUtils document = PDFUtils.getInstance(os);
        BaseFont baseFont = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
        Font contextFont = new Font(baseFont, 20, Font.NORMAL);
        Paragraph p = document.createParagraph("Pop@Pipi");
        p.setFont(contextFont);
        document.addElement(p);
        document.addElement(document.createParagraph("哈哈哈1"));
        document.close();
        os.flush();
        os.close();
    }
}
