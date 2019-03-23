package com.pop.uitils;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.sun.istack.internal.Nullable;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @link 使用时候，请使用itext-4.2.1 版本
 * 这个设置
 */
public class PDFUtils {

    private StylePackage stylePackage;

    private  Document document;//主文档
    private  PdfWriter pdfWriter;//写入者

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

    public Document addElement(Element e) throws DocumentException {document.add(e);return document;}

    //流程是创建了Writer才可以打开
    private  void setOutputStream(OutputStream os) throws DocumentException {
        pdfWriter = PdfWriter.getInstance(document,os);
    }

    public  void close(){
        document.close();
        pdfWriter.close();
    }

    public static PDFUtils getInstance(OutputStream os) throws IOException, DocumentException {
        osi = os;
        return PDFHolder.pdf;
    }
    private static  OutputStream osi;
    private static class PDFHolder{
        private static final PDFUtils pdf=new PDFUtils();
    }
    private PDFUtils() {
        try {
            document = new Document();
            stylePackage = new StylePackage();
            setOutputStream(osi);
            document.open();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
        }
        //--------具体的流程------start

        //generatePDF();

        //--------具体的流程------end
    }

    private void generatePDF() {
    }

    //设置页眉
    public void setDocumentHeader(){}

    //设置页脚 Paragraph


    //段落
    public Paragraph createParagraph(String context) throws DocumentException {
        Paragraph paragraph = new Paragraph(context);

            return stylePackage.setDefaultParagraphStyle(paragraph);

            //document.add(paragraph);

    }
    /**
     * 样式打包
     */
    public class StylePackage{
        private  BaseFont baseFont;//自定义字体
        private Font contextTitle;
        private  Font contextFont;//正文字体
        private  Font tableFont;//适用于
        private  Font tableTileFont;

        private  final Float SPACE_NUMBER_FIVE = 5f;//对于前后间距比较好看的位置
        private final Float INDENT_FIRST_TWO = 2f;//对于首行缩进比较好看的位置

        //------字体的设置-----end
        //------关于是否缩进，居中的设置

        /**
         * 默认的段落格式
         * 设置字体为正文字体，首行缩进2f，左对齐，上下间距5f
         * @param paragraph
         * @return
         */
        public Paragraph setDefaultParagraphStyle(Paragraph paragraph){
            if(ObjectUtils.checkObjcetIsNull(paragraph)){return null;}
            paragraph.setFont(contextFont);
            paragraph.setFirstLineIndent(INDENT_FIRST_TWO);
            paragraph.setAlignment(Element.ALIGN_LEFT);
            paragraph.setSpacingAfter(SPACE_NUMBER_FIVE);
            paragraph.setSpacingBefore(SPACE_NUMBER_FIVE);
            return paragraph;
        }

        /**
         * 设置段落样式->设置段落的对齐情况
         * @param paragraph 段落
         * @param alignType 居中类型 Element.ALIGN_CENTER
         * @param font 使用的文字类型
         * @return  段落
         */
        public Paragraph setParagraphStyle(Paragraph paragraph,@Nullable Font font,@Nullable Integer alignType){
            if(ObjectUtils.checkObjcetIsNull(paragraph)){return null;}
            if(ObjectUtils.checkObjcetIsNotNull(font)){ paragraph.setFont(font); }
            if(NumberUtils.checkNumberIsNotNull(alignType)){paragraph.setAlignment(alignType);}
            return paragraph;
        }

        /**
         * 设置段落样式->设置段落的首行居中，前后空白
         * @param paragraph
         * @param fistLine 首行缩进
         * @param absapce  前留白和后留白 长度为2
         * @return
         */
        public Paragraph setParagraphStyle(Paragraph paragraph,@Nullable Float fistLine ,@Nullable Float... absapce){
            if(ObjectUtils.checkObjcetIsNull(paragraph)){return null;}
            if(NumberUtils.checkNumberIsNotNull(fistLine)){ paragraph.setFirstLineIndent(fistLine);}
            if(NumberUtils.checkNumbersIsNotNull(absapce)){
                if(absapce.length>2){return null;}
                paragraph.setSpacingBefore(absapce[0]);
                paragraph.setSpacingAfter(absapce[1]);
            }
            return paragraph;
        }

        public Font getContextTitle() {
            return contextTitle;
        }

        public void setContextTitle(Font contextTitle) {
            this.contextTitle = contextTitle;
        }

        public BaseFont getBaseFont() {
            return baseFont;
        }

        public void setBaseFont(BaseFont baseFont) {
            this.baseFont = baseFont;
        }


        public Font getContextFont() {
            return contextFont;
        }

        public void setContextFont(Font contextFont) {
            this.contextFont = contextFont;
        }

        public Font getTableFont() {
            return tableFont;
        }

        public void setTableFont(Font tableFont) {
            this.tableFont = tableFont;
        }

        public Font getTableTileFont() {
            return tableTileFont;
        }

        public void setTableTileFont(Font tableTileFont) {
            this.tableTileFont = tableTileFont;
        }
        public StylePackage() throws IOException, DocumentException {
            doInitFontStyle();
        }

        private  void doInitFontStyle() throws IOException, DocumentException {
            baseFont = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
            contextFont = new Font(baseFont, 10, Font.BOLD);
            contextTitle = new Font(baseFont,16, Font.NORMAL);
            tableTileFont=new Font(baseFont,12,Font.BOLD);
            tableFont = new Font(baseFont,11,Font.NORMAL);
        }

    }
}
