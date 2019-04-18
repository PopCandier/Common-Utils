package com.pop.uitils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @ClassName: HTML
 * @Author: Pop
 * @Description: html element
 * @Date: 2019/4/16 10:39
 * @Version: 1.0
 */
public  abstract  class HTML {
    //规定HTML页面
    //标志符号为
    private static final StringBuilder BODY = new StringBuilder();
    private static final String SPACE = " ";
    private static final Pattern PATTERN = Pattern.compile("\\[|\\]|,");
    /*************************************
     * html 元素的定义 start
    ****************************************/
    private static final String _PARAM = "?";
    private static final String _SUFFIX = "</?>";
    private static final String _PREFIX_HEAD = "<?";
    private static final String _PREFIX_END = ">";
    private static final String _BR="<br/>";

    //a 标签
    private static final String _A = "a";



    //属性
    private static final String ID="id=\"?\"";
    private static final String CLASS="class=\"?\"";
    private static final String HREF="href=\"?\"";
    private static final String CONTENT=_PARAM;


    //事件
    private static final String ON_CLICK="OnClick=\"?\"";

    /*************************************
     * html 元素的定义 end
     ****************************************/

    public static void main(String[] args) {

        String body = HTML.getBody();

        A a = new A("http://www.baidu.com","test","一段测试");
        String s=a.doConnect();

        new A("http://www.baidu.com","test","一段测试").br().append();
        new A("http://www.baidu.com","test","一段测试").br().append();
        new A("http://www.baidu.com","test","一段测试").br().append();
        new A("http://www.baidu.com","test","一段测试").br().append();
        System.out.println(HTML.getBody());

        //增加之前需要clear


    }

    public enum Type{
        _A(new A());
        private Object object;
        public Object getObject() {
            return object;
        }
        private Type(Object object){
            this.object=object;
        }
    }

    /**
     * 填写参数的 id class
     * @param type 元素的类型
     *
     */
    public static BaseElement create(Type type){
        //获得要创建的元素是什么
        BaseElement  baseElement=(BaseElement) type.getObject();
        return baseElement;
    }

    /**
     * 清空
     */
    private static void clear(){
        BODY.setLength(0);
    }

    private static String setValue(String target,String value){
        return SPACE+target.replace(_PARAM,value);
    }

    private static void append(String element){
        BODY.append(element);
    }

    public static void br(){
        BODY.append(_BR);
    }

    public static String getBody(){
        try{ return BODY.toString();}finally { clear(); }
    }

    private abstract static class  BaseElement implements HTMLEvent,Cloneable{
        protected StringBuilder result;
        protected String content="";
        protected String id="";
        protected String name="";
        protected String onClick="";
        protected List<String> classNames = new ArrayList<String>();

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
        //先假设id，和name都是唯一的
        protected BaseElement setId(String id) {
            this.id = id;
            return this;
        }
        protected BaseElement setName(String name) {
            this.name = name;
            return this;
        }
        protected BaseElement addClassName(String className) {
            classNames.add(className+SPACE);
            return this;
        }
        protected String getClassName(){
            String result = "";
            if(classNames.isEmpty()){return result;}
            result = classNames.toString().replaceAll(PATTERN.pattern(),"");
            return result;
        }

        public void clear(){
            result.setLength(0);
        }

        public BaseElement append(){
            HTML.append(this.doConnect());
            return this;
        }
        public BaseElement br(){
            HTML.br();
            return this;
        }
        /**
         * 拼装完成的HTML元素
         * @return
         */
        abstract String doConnect();
    }

    public static class A extends BaseElement{
        private String href="";
        public A(){result=new StringBuilder();}
        public A(String href){
            this();
            this.href = href;
        }
        public A(String href,String className,String content){
            this(href);
            this.addClassName(className);
            this.content=content;
        }
        public A setHref(String href) {
            this.href = href;
            return  this;
        }
        public A setContent(String content){
            this.content = content;
            return this;
        }
        @Override
        public HTMLEvent setOnClick(String clickName) {
            this.onClick = clickName;
            return this;
        }
        @Override
        public String doConnect() {
           return result.append(setValue(_PREFIX_HEAD,_A)).append(setValue(ID,this.id))
                    .append(setValue(CLASS,getClassName())).append(setValue(HREF,this.href))
                    .append(setValue(ON_CLICK,this.onClick)).append(_PREFIX_END)
                    .append(setValue(CONTENT,this.content))
                    .append(setValue(_SUFFIX,_A)).toString();
        }
    }

    /**
     * HTML的事件
     */
    private interface HTMLEvent{
        HTMLEvent setOnClick(String clickName);
    }
}
