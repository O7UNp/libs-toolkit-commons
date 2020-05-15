package dev.xethh.libs.toolkits.commons.threadLocal;

import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;

public abstract class XEContext {
    private static final ThreadLocal<Map<String, Object>> map = new ThreadLocal<Map<String, Object>>(){
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    protected static void put(String key, Object value){
        map.get().remove(key);
        if(MDC.get(key)!=null) MDC.remove(key);

        map.get().put(key, value);
        MDC.put(key, value.toString());
    }
    protected static void remove(String key){
        map.get().remove(key);
    }

    protected static Object get(String key){
        return map.get().get(key);
    }

    protected static <T> T getAs(String key){
        Object obj = map.get().get(key);
        return (T) map.get().get(key);
    }

    protected static boolean contains(String key){
        return map.get().containsKey(key);
    }

    protected static Map<String, Object> copy(){
        Map<String, Object> copy = new HashMap<>();
        map.get().entrySet().forEach(entry->copy.put(entry.getKey(), entry.getValue()));
        return copy;
    }

}
