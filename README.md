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



```java
//创建一个段落，使用默认的样式
public Paragraph createParagraph(String context) throws DocumentException
//关闭document 与 Writer 的方法
public  void close()
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
```





