package com.pop.uitils;

import com.lowagie.toolbox.plugins.HtmlBookmarks;
import org.junit.Assert;

import java.util.*;
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
    //下拉选择框
    private static final String _SELECT="select";
    private static final String _OPTION="option";


    //属性
    private static final String ID="id=\"?\"";
    private static final String CLASS="class=\"?\"";
    private static final String HREF="href=\"?\"";
    private static final String CONTENT=_PARAM;
    private static final String VALUE="value=\"?\"";


    //事件
    private static final String ON_CLICK="OnClick=\"?\"";
    private static final String ON_CHANGE="OnChange=\"?\"";
    /*************************************
     * html 元素的定义 end
     ****************************************/

    private static class HTMLHolder{
        public static final Map<Class<? extends BaseElement>,BaseElement> HTML_REGISTER_MAP
                = new HashMap<>();
        static {
            HTML_REGISTER_MAP.put(HTML.A.class,new HTML.A());
            HTML_REGISTER_MAP.put(HTML.Option.class,new HTML.Option());
            HTML_REGISTER_MAP.put(HTML.Select.class,new HTML.Select());
        }
        public static BaseElement getHtmlElement(Class<? extends BaseElement> key){ return HTML_REGISTER_MAP.get(key); }
    }

    public static void main(String[] args) {


//        String body = HTML.getBody();
//
//       HTML.A a1 = HTML.A.getInstance();
//       a1.setContent("test").setHref("www.baidu.com");
//        for(int i =0;i<10;i++){
//            a1.setContent(i+"");
//            a1.appendToHTML();
//        }
//        System.out.println(HTML.getBody());

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
    }
    /**
     * 清空
     */
    private static void clear(){
        BODY.setLength(0);
    }

    private static String setValueHtml(String target,String value){
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
    /**
     * HTML的事件
     */
    private interface HTMLMouseEvent{
        BaseElement setOnClick(String clickName);

    }
    private interface HTMLExtendsEvent{
        BaseElement setOnChange(String clickName);
    }
    private abstract static class  BaseElement implements HTMLMouseEvent{
        protected StringBuilder result;
        protected String content="";
        protected String id="";
        protected String name="";
        protected String style="";
        protected String value="";

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getOnClick() {
            return onClick;
        }

        protected String onClick="";
        protected List<String> classNames = new ArrayList<String>();

        public BaseElement() { this.result =new StringBuilder(); }

        //先假设id，和name都是唯一的
        public BaseElement setId(String id) {
            this.id = id;
            return this;
        }
        public BaseElement setName(String name) {
            this.name = name;
            return this;
        }
        public BaseElement addClassName(String className) {
            classNames.add(className+SPACE);
            return this;
        }
        public String getClassName(){
            String result = "";
            if(classNames.isEmpty()){return result;}
            result = classNames.toString().replaceAll(PATTERN.pattern(),"");
            return result;
        }

        public void clear(){
             classNames.clear();
             result.setLength(0);
        }

        /**
         * 将本元素添加到HTML的Body中
         * @return 元素本身
         */
        public BaseElement appendToHTML(){
            HTML.append(this.doConnect());
            clear();
            return this;
        }
        public BaseElement br(){
            HTML.br();
            return this;
        }
        @Override
        public BaseElement setOnClick(String clickName) {
            this.onClick = clickName;
            return this;
        }
        /**
         * 拼装完成的HTML元素
         * @return
         */
        abstract String doConnect();
    }

    public static class A extends BaseElement{
        public static A getInstance(){ return (A) HTMLHolder.getHtmlElement(A.class);}
        private String href="";
        private A(){}
        public String getHref() {
            return href;
        }
        public A setHref(String href) {
            this.href = href;
            return  this;
        }
        @Override
        public String doConnect() {
           return result.append(setValueHtml(_PREFIX_HEAD,_A)).append(setValueHtml(ID,this.id))
                    .append(setValueHtml(CLASS,getClassName())).append(setValueHtml(HREF,this.href))
                    .append(setValueHtml(ON_CLICK,this.onClick)).append(_PREFIX_END)
                    .append(setValueHtml(CONTENT,this.content))
                    .append(setValueHtml(_SUFFIX,_A)).toString();
        }

    }

    private static class Option extends BaseElement{
        @Override
        String doConnect() {
            return result.append(setValueHtml(_PREFIX_HEAD,_OPTION)).append(setValueHtml(VALUE,this.value))
                    .append(_PREFIX_END)
                    .append(setValueHtml(CONTENT,this.content))
                    .append(setValueHtml(_SUFFIX,_OPTION)).toString();
        }
    }

    public static class Select<T> extends BaseElement implements HTMLExtendsEvent{
        private StringBuffer options = new StringBuffer();
        private Option option = (Option) HTMLHolder.getHtmlElement(Option.class);
        public static Select getInstance(){return (Select) HTMLHolder.getHtmlElement(Select.class);}
        @Override
        public BaseElement setOnChange(String clickName) {
            return this;
        }

        private String onChange = "";
        /**
         * 传入某个集合，并规定要设置到Value和content是具体哪一个字段名称
         * 不过尽量保持较少数量的列表
         * @param collection 某一个集合
         * @param content 代表内容的字段
         * @param value 代表value的字段
         * @return
         */
        public  Select appendOption(Collection<T> collection,String content,String value){
            for(T t:collection){//判断空暂时没考虑
                Object val=ReflectUtils.getValueByField(t,value);
                Object cont = ReflectUtils.getValueByField(t,content);
                option.setValue(val.toString());
                option.setContent(cont.toString());
                options.append(option.doConnect());
                option.clear();
            }
            return this;
        }

        /**
         * key为 option中的value的值
         * value 为 option中content的值
         * @param collection
         * @return
         */
        public Select appendOption(Map<String,String> collection){
            for(Map.Entry<String,String> entry:collection.entrySet()){

                option.setValue(entry.getKey());
                option.setContent(entry.getValue());
                options.append(option.doConnect());
                option.clear();
            }
            return this;
        }

        @Override
        String doConnect() {
            return result.append(setValueHtml(_PREFIX_HEAD,_SELECT)).append(setValueHtml(ID,this.id))
                    .append(setValueHtml(CLASS,getClassName())).append(setValueHtml(ON_CHANGE,this.onChange))
                    .append(setValueHtml(ON_CLICK,this.onClick)).append(_PREFIX_END)
                    .append(setValueHtml(CONTENT,options.toString()))
                    .append(setValueHtml(_SUFFIX,_SELECT)).toString();
        }


    }
}
