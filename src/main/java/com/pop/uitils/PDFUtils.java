package com.pop.uitils;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.sun.istack.internal.Nullable;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @link 使用时候，请使用itext-4.2.1 版本
 * 这个设置
 */
public class PDFUtils {

    private StylePackage stylePackage;
    private TableMaker tableMaker;

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

    public TableMaker getTableMaker() {
        return tableMaker;
    }

    private PDFUtils() {
        try {
            if(null!=PDFHolder.pdf){
                throw new RuntimeException("can not create more instance");
            }
            document = new Document();
            stylePackage = new StylePackage();
            tableMaker = new TableMaker();
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


    /**
     * 每个表格生成的时候必须定义一共有多少格子
     *
     */
    public void createTable(){

    }

    //专门制作单元格的类，主要用于快捷创建表格
    //public List<PdfCell> generateColumCells(Integer number)

    //段落
    public Paragraph createParagraph(String context) throws DocumentException {
        //Paragraph paragraph = new Paragraph(context,stylePackage.getContextTitle());
        Paragraph paragraph = new Paragraph(context,stylePackage.getContextFont());
        try { return stylePackage.setDefaultParagraphStyle(paragraph);
        }finally { document.add(paragraph); }
        //自己定制问题
    }
    public Paragraph createParagraph(String context,@Nullable Integer alignType) throws DocumentException {
        //Paragraph paragraph = new Paragraph(context,stylePackage.getContextTitle());
        Paragraph paragraph = new Paragraph(context,stylePackage.getContextFont());
        try { return stylePackage.setParagraphStyle(paragraph,alignType);
        }finally { document.add(paragraph); }
        //自己定制问题
    }


    /**
     * 样式打包
     */
    private class StylePackage{
        private  BaseFont baseFont;//自定义字体
        private  Font contextTitle;
        private  Font contextFont;//正文字体
        private  Font tableFont;//适用于
        private  Font tableTileFont;

        private  final Float SPACE_NUMBER_FIVE = 2f;//对于前后间距比较好看的位置
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
            //paragraph.setFont(contextTitle);
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
         *
         * @return  段落
         */
        public Paragraph setParagraphStyle(Paragraph paragraph,@Nullable Integer alignType){
            if(ObjectUtils.checkObjcetIsNull(paragraph)){return null;}
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

        //------------------表格的样式申明
        private static final int ONEHUNDRED=100;
        private static final float FIVE=5f;
        private static final float THREE=3f;
        /**
         * 默认 表格内容居中，最大宽高度
         * @param table
         * @param total 总比例
         * @param present 分别比例
         * @return
         */
        public PdfPTable setDefaultTaleStyle(PdfPTable table,Float total,float... present) throws DocumentException {
            table.setWidthPercentage(ONEHUNDRED);// 宽度为100%
            table.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);// 居中
            table.setTotalWidth(total);
            table.setWidths(present);
            return table;
        }

        public PdfPCell setDefaultCellStyle(PdfPCell cell,int col,@Nullable Integer align){
            cell.setColspan(col);
            cell.setPaddingTop(THREE);
            cell.setPaddingBottom(FIVE);
            if(NumberUtils.checkNumberIsNull(align)){
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                return cell; }
            cell.setHorizontalAlignment(align);
            return cell;
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
            contextFont = new Font(baseFont, 10, Font.NORMAL);
            contextTitle = new Font(baseFont,16, Font.BOLD);
            tableTileFont=new Font(baseFont,12,Font.BOLD);
            tableFont = new Font(baseFont,11,Font.NORMAL);
        }

    }

    public class TableMaker{
        private PdfPTable table;
        private int columns=0;

        private TableMaker() { }

        public void clear(){
            table = null;//for gc
        }

        public PdfPTable getTable() {
            return table;
        }

        public TableMaker createTable(Integer colum, float totalNumber, float... precent) throws DocumentException {
            columns = colum;
            table = new PdfPTable(columns);
            stylePackage.setDefaultTaleStyle(table,totalNumber,precent);
            return tableMaker;
        }

        public TableMaker generateCell(String context,Integer align,int col){
            PdfPCell cell = new PdfPCell(new Paragraph(context,stylePackage.getTableFont()));
            stylePackage.setDefaultCellStyle(cell,col,align);
            table.addCell(cell);
            return tableMaker;
        }
        public TableMaker generateCell(String context,@Nullable Integer align,int col,Font font){
            PdfPCell cell = new PdfPCell(new Paragraph(context,font));
            stylePackage.setDefaultCellStyle(cell,col,align);
            table.addCell(cell);
            return tableMaker;
        }
        public TableMaker generateTableTitle(String context){
            generateCell(context,null,columns,stylePackage.getTableTileFont());
            return tableMaker;
        }

    }
}
