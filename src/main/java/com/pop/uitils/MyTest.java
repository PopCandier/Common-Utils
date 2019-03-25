package com.pop.uitils;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
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

    @Test
    public void TestPdfTable() throws  Exception{
        File file = new File("/demo.pdf");
        FileOutputStream os = new FileOutputStream(file);
        PDFUtils pdf = PDFUtils.getInstance(os);

        PDFUtils.TableMaker maker=pdf.getTableMaker().createTable(4,10,
                new float[]{3f,2f,4f,1f})
                .generateTableTitle("表格测试").
                generateCell("测试",null,2).generateCell("文本",null,0).
                generateCell("呵呵", PdfPCell.ALIGN_LEFT,0);

        for(int i=0;i<8;i++){
            maker.generateCell("内容"+i,null,0);
        }
        pdf.addElement(maker.getTable());
        maker.clear();

        PDFUtils.TableMaker maker1=pdf.getTableMaker().createTable(5,10,
                new float[]{2f,2f,4f,1f,1})
                .generateTableTitle("表格测试1").
                        generateCell("测试",null,2).generateCell("文本",null,0).
                        generateCell("呵呵", PdfPCell.ALIGN_LEFT,0)
                .generateCell("13413", Element.ALIGN_CENTER,0);

        for(int i=0;i<10;i++){
            maker.generateCell("内容"+i,null,0);
        }
        pdf.addElement(maker.getTable());

        pdf.close();
        os.flush();
        os.close();

    }
}
