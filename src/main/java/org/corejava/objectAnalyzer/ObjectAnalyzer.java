package org.corejava.objectAnalyzer;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class ObjectAnalyzer {

    private ArrayList<Object> visited = new ArrayList<>();

    public String toString(Object obj) throws ReflectiveOperationException
    {
        if(obj == null) return "null";
        if(visited.contains(obj)) return "...";
        visited.add(obj);
        Class cl = obj.getClass();
        if(cl == String.class) return (String) obj;
        if(cl.isArray())
        {
            String r = cl.getComponentType() + "[]{";
            for(int i = 0; i < Array.getLength(obj); i++)
            {
                if(i > 0) r+=",";
                Object val = Array.get(obj, i);
                if(cl.getComponentType().isPrimitive()) r+=val;
                else r+=toString(val);
            }
            return r += "}";
        }

        String r = cl.getName();
        do{
            r += "[";
            Field[] fields = cl.getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);
            for(Field f : fields)
            {
                if(!Modifier.isAbstract(f.getModifiers()))
                {
                    if(!r.endsWith("[")) r+=",";
                    r += f.getName() + "=";
                    Class t = f.getType();
                    Object val = f.get(obj);
                    if(t.isPrimitive()) r += val;
                    else r += toString(val);
                }
            }
            r += "}";
            cl.getSuperclass();
        } while(cl != null);

        return r;
    }

}
