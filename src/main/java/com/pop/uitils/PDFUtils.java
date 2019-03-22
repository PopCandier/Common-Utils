package com.pop.uitils;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @link 使用时候，请使用itext-4.2.1 版本
 * 这个设置
 */
public class PDFUtils {

    private static Document document;//主文档
    private static PdfWriter pdfWriter;//写入者

    //-----------------------------页眉页脚
    private static HeaderFooter header;
    private static HeaderFooter footer;
    //-----------------------------



    /**
     * 返回一个document
     * @return document
     */
    public  Document getDocumentInstance(){
        return document;
    }

    //流程是创建了Writer才可以打开
    private static void setOutputStream(OutputStream os) throws DocumentException {
        pdfWriter = PdfWriter.getInstance(document,os);
    }

    public static void close(){
        pdfWriter.close();
        document.close();
    }

    public PDFUtils(OutputStream os) throws DocumentException {

        document = new Document();
        setOutputStream(os);
        document.open();

        //--------具体的流程------start

        //generatePDF();

        //--------具体的流程------end
    }

    private void generatePDF() {
    }

    //设置页眉
    public void setDocumentHeader(){}

    //设置页脚

    /**
     * 样式打包
     */
    public static class StylePackage{
        private static BaseFont baseFont;//自定义字体
        private static Font font;
        private static Font contextFont;//正文字体

        private static Font tableFont;
        private static Font tableTileFont;

        private static void initFontStyle(){
            font = new Font(baseFont, 10, Font.NORMAL);

            tableTileFont=new Font(baseFont,12,Font.BOLD);
            tableFont = new Font(baseFont,11,Font.NORMAL);

        }

        static {
            try {
                baseFont = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
                initFontStyle();
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public static void setFontSize(Integer size){
            font.setSize(size);

        }
        public static void  setFontStyle(Integer fontStyle){
            font.setStyle(fontStyle);
        }
    }
}
