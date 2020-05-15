package dev.xethh.libs.toolkits.commons.threadLocal;

import java.util.Map;

public class XEContextRaw{
    public static void put(String key, Object value){
        if(contains(key))
            XEContext.remove(key);
        XEContext.put(key,value);
    }
    public static void remove(String key){
        if(XEContext.contains(key))
            XEContext.remove(key);
    }

    public static Object get(String key){
        return XEContext.get(key);
    }

    public static <T> T getAs(String key){
        return XEContext.getAs(key);
    }

    public static boolean contains(String key){
        return XEContext.contains(key);
    }

    public static Map<String, Object> copy(){
        return XEContext.copy();
    }
}
