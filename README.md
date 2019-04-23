# Common-Utils

平常总结的工具类，方便开发使用。

[TOC]

------

#### ArraysUtils

[^]: 此类用于对数组的操作	"Array"

```java
public  static Number[]  generateNumberArray(Number ... params）//将传入参数转化为一个数学数组           
```

------

#### NumberUtils

[^]: 此类用于对基本类型或者装箱类型的操作	"Number"

```java
//传入的若干数学类型中是否有一个为空
public static boolean checkNumbersIsNotNull(Number... numbers)
//传入数学参数中是否不为空    
public static boolean checkNumberIsNotNull(Number number)
//传入数学参数中是否为空
public static boolean checkNumberIsNull(Number number)
```



------

#### ObjectUtils

[^]: 此类用于对Object类型的相关的基本操作	"Object"

```java
//判断一个对象是否为空
public static boolean checkObjcetIsNotNull(Object obj)
//判断一个对象是否不为空
public static boolean checkObjcetIsNull(Object obj)
//如果对象数组有一个为null，返回false;
public static boolean checkObjectsIsNotNull(Object... objs)    
```



----

#### PDFUtils

[^]: 对PDF文件进行操作，依赖于 `itext-4.2.1 `



> PDFUtils	主体类
>
> > PDFHolder 	用于生成PDFUtils
> >
> > StylePackage	对所需要的样式的封装
> >
> > TableMaker	用于快速生成表格的对象



```java
//创建一个段落，使用默认的样式
public Paragraph createParagraph(String context) throws DocumentException
//关闭document 与 Writer 的方法
public  void close()
//获得一个tableMaker
public TableMaker getTableMaker()

    
//----为TableMaker的api--start
//用于生成一个表格，参数分别为多少行的表格，分配比例，具体分配比例
public TableMaker createTable(Integer colum, float totalNumber, float... precent)
//生成一个单元格，内容，对齐方式，跨行数目
public TableMaker generateCell(String context,Integer align,int col)
//生成一个标题，改标题将会跨越所有行，并且加粗居中
public TableMaker generateTableTitle(String context)
//----------------------end
```



##### example for PDFUtils

```java
@Test
public void TestPdfUtils() throws IOException, DocumentException {
    File file = new File("/demo.pdf");
    FileOutputStream os = new FileOutputStream(file);
    PDFUtils document = PDFUtils.getInstance(os);
    document.createParagraph("Pop@Pipi");
    document.close();
    os.flush();
    os.close();
}

@Test
public void TestPdfTable() throws Exception {
        File file = new File("/demo.pdf");
        FileOutputStream os = new FileOutputStream(file);
        PDFUtils pdf = PDFUtils.getInstance(os);

        PDFUtils.TableMaker maker = pdf.getTableMaker().createTable(4, 10,
                new float[]{3f, 2f, 4f, 1f})
                .generateTableTitle("表格测试").
                        generateCell("测试", null, 2).generateCell("文本", null, 0).
                        generateCell("呵呵", PdfPCell.ALIGN_LEFT, 0);

        for (int i = 0; i < 8; i++) {
            maker.generateCell("内容" + i, null, 0);
        }
        pdf.addElement(maker.getTable());
        maker.clear();

        PDFUtils.TableMaker maker1 = pdf.getTableMaker().createTable(5, 10,
                new float[]{2f, 2f, 4f, 1f, 1})
                .generateTableTitle("表格测试1").
                        generateCell("测试", null, 2).generateCell("文本", null, 0).
                        generateCell("呵呵", PdfPCell.ALIGN_LEFT, 0)
                .generateCell("13413", Element.ALIGN_CENTER, 0);

        for (int i = 0; i < 10; i++) {
            maker.generateCell("内容" + i, null, 0);
        }
        pdf.addElement(maker.getTable());

        pdf.close();
        os.flush();
        os.close();

    }
```



#### HTML

将会拼装完成返回一个完整的HTML元素

```java
Map<String,String> map = new HashMap<String,String>(){{
            put("1111","2222");
            put("3333","2222");
            put("4444","2222");
            put("5555","2222");
            put("6666","2222");
            put("7777","2222");
        }};

        HTML.Select select = HTML.Select.getInstance();
        select.appendOption(map);
        select.setOnChange("test()");
        select.appendToHTML();
        System.out.println(HTML.getBody());
```

首先每一个元素都可以通过HTML.**.getInstance()获得，并可以set需要的任何属性

当你对一个属性设置完成后，必须`appendToHTML`，添加到当前HTML主题中去

然后通过`HTML.getBody()`获得完成拼接后的元素。

