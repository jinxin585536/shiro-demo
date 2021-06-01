package com.example.demo.common.http;

import com.example.demo.common.exception.MessageException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @Author: jinxin
 * @Date: 2020/12/3 9:47
 * @Description: 处理对象工具
 */
public class FiledsUtils {

    private static final Logger logger = LoggerFactory.getLogger(FiledsUtils.class);

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 根据属性名获取属性值
     * */
    public static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = strFirst2Upper(fieldName.toLowerCase());
            String getter = "get" + firstLetter;
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            logger.error("没有该属性"+fieldName);
            return null;
        }
    }



    public static Object setValue(Object obj, String fieldName, String value) throws MessageException {
        Class returnType;
        Method m;
        try {
            if (StringUtils.isEmpty(fieldName) || "null".equals(fieldName)) {
                return obj;
            }
            returnType = obj.getClass()
                    .getDeclaredMethod("get" + strUpper(fieldName.toLowerCase()))
                    .getReturnType();
            m = obj.getClass().getDeclaredMethod(
                    "set" + strUpper(fieldName.toLowerCase()), returnType);

            Object a = null;
            if (StringUtils.isNotBlank(value)) {
                if (returnType.equals(String.class)) {
                    a = value;
                } else if (returnType.equals(Integer.class)) {
                    a = Integer.parseInt(value);
                } else if (returnType.equals(Double.class)) {
                    a = Double.parseDouble(value);
                } else if (returnType.equals(Long.class)) {
                    a = Long.parseLong(value);
                } else if (returnType.equals(Short.class)) {
                    a = Short.parseShort(value);
                } else if (returnType.equals(Boolean.class)) {
                    if (value.equals("是")) {
                        a = true;
                    } else if (value.equals("否")) {
                        a = false;
                    } else {
                        a = Boolean.getBoolean(value);
                    }
                } else if (returnType.equals(Date.class)) {
                    a = new SimpleDateFormat("yyyy-MM-dd").parse(value);
                } else  if (returnType.equals(BigDecimal.class)) {
                    a = new BigDecimal(value);
                }
            }
            m.invoke(obj, a);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessageException(fieldName+"数据转化失败");
        }

    }

    /**
     * 获取属性名数组
     * */
    public static String[] getFiledName(Object o){
        Field[] fields=o.getClass().getDeclaredFields();
        String[] fieldNames=new String[fields.length];
        for(int i=0;i<fields.length;i++){
            System.out.println(fields[i].getType());
            fieldNames[i]=fields[i].getName();
        }
        return fieldNames;
    }

    /**
     * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
     * */
    public static List getFiledsInfo(Object o){
        Field[] fields=o.getClass().getDeclaredFields();
        String[] fieldNames=new String[fields.length];
        List list = new ArrayList();
        Map infoMap=null;
        for(int i=0;i<fields.length;i++){
            infoMap = new HashMap();
            infoMap.put("type", fields[i].getType().toString());
            infoMap.put("name", fields[i].getName());
            infoMap.put("value", getFieldValueByName(fields[i].getName(), o));
            list.add(infoMap);
        }
        return list;
    }

    /**
     * 获取对象的所有属性值，返回一个对象数组
     * */
    public static Object[] getFiledValues(Object o){
        String[] fieldNames=getFiledName(o);
        Object[] value=new Object[fieldNames.length];
        for(int i=0;i<fieldNames.length;i++){
            value[i]=getFieldValueByName(fieldNames[i], o);
        }
        return value;
    }

    /** 下划线转驼峰 */
    public static String lineToHump(String str) {
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 方法说明：对字符串的第一个字符进行大写转换,如果第二个字符为"_"不做操作
     *  如果有"_",则"_"后字母大写
     * @return
     */
    public static String strUpper(String arg){
        if (arg == null) {
            return arg;
        }
        arg = arg.trim();
        int len = arg.length();
        if (len == 0) {
            return arg;
        } else if (len == 1) {
            return arg.toUpperCase();
        } else {
            if(arg.substring(1,2).equals("_")){
                return lineToHump(arg);
            }else{
                arg=arg.substring(0, 1).toUpperCase() + arg.substring(1, len);
                return lineToHump(arg);
            }
        }


    }

    /**
     * 方法说明：对字符串的第一个字符进行大写转换,如果第二个字符为大写不做操作
     *
     * @param arg
     * @return
     */
    public static String strFirst2Upper(String arg) {
        if (arg == null) {
            return arg;
        }
        arg = arg.trim();
        int len = arg.length();
        if (len == 0) {
            return arg;
        } else if (len == 1) {
            return arg.toUpperCase();
        } else {
            if (Character.isUpperCase(arg.charAt(1))) {
                return arg;
            } else {
                return arg.substring(0, 1).toUpperCase() + arg.substring(1, len);
            }
        }
    }

    /**
     * 方法说明：将全角字符转换成半角字符
     *
     * @param input
     * @param flag
     * @return
     */
    public static String Q2BChange(String input, boolean flag) {
        StringBuilder result = new StringBuilder();
        if(StringUtils.isEmpty(input)){
            return null;
        }
        char[] str = input.toCharArray();
        for(int i=0;i<str.length;i++ ){
            int code = str[i];//获取当前字符的unicode编码
            if (code >= 65281 && code <= 65373)//在这个unicode编码范围中的是所有的英文字母以及各种字符
            {
                result.append((char) (str[i] - 65248));//把全角字符的unicode编码转换为对应半角字符的unicode码
            }else if (code == 12288)//空格
            {
                result.append((char) (str[i] - 12288 + 32));
            }else if(code == 65377){
                result.append((char) (12290));
            }else if(code == 12539){
                result.append((char) (183));
            }else if(code == 8482 && flag==true){//如果是特殊字符TM 并且是需要转换的所作操作

            }else if(code ==8226) { //特殊字符 ‘·’的转化
                result.append((char) (183));
            }else if('《' == (char)code){
                result.append('<');
            }else if('》' == (char)code){
                result.append('>');
            }else if('【' == (char)code){
                result.append('[');
            }else if('】' == (char)code){
                result.append(']');
            }else if('“' == (char)code){
                result.append('"');
            }else if('”' == (char)code){
                result.append('"');
            }else if('‘' == (char)code){
                result.append('\'');
            }else if('’' == (char)code){
                result.append('\'');
            }else{
                result.append(str[i]);
            }
        }

        return result.toString().trim();
    }
}
