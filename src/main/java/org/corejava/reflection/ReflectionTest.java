package org.corejava.reflection;

import static java.lang.System.out;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class ReflectionTest {

    public static void main(String[] args) 
        throws ReflectiveOperationException
    {
        String name;
        if(args.length > 0) name = args[0];
        else {
            var in = new Scanner(System.in);
            System.out.println("Enter class name (e.g. java.util.Date): ");
            name = in.next();
        }

        Class cl = Class.forName(name);
        Class supercl = cl.getSuperclass();
        String modifiers = Modifier.toString(cl.getModifiers());
        if(modifiers.length() > 0) out.print(modifiers + " ");
        System.out.print("class " + name);
        if(supercl != null && supercl != Object.class) System.out.print(" extends "
                + supercl.getName());
        
        out.print("\n{\n");
        printConstructors(cl);
        out.println();
        printMethods(cl);
        out.println();
        printFields(cl);
        out.println("}");
    }

    private static void printConstructors(Class cl) {
        Constructor[] constructors = cl.getDeclaredConstructors();

        for(Constructor c : constructors) {
            String name = c.getName();
            out.print("    ");
            String modifiers = Modifier.toString(c.getModifiers());
            if(modifiers.length() > 0) out.print(modifiers + " ");
            out.print(name + "(");

            Class[] paramTypes = c.getParameterTypes();
            for(int j = 0; j < paramTypes.length; j++){
                if(j > 0) out.print(", ");
                out.print(paramTypes[j].getName());
            }
            out.println(");");
        }
    }

    private static void printMethods(Class cl) {
        Method[] methods = cl.getDeclaredMethods();
        for(Method m : methods) {
            Class retType = m.getReturnType();
            String name = m.getName();
            out.print("    ");
            String modifiers = Modifier.toString(m.getModifiers());
            if(modifiers.length() > 0) out.print(modifiers + " ");
            out.print(retType.getName() + " " + name + "(");

            Class[] paramTypes = m.getParameterTypes();
            for(int j = 0; j < paramTypes.length; j++){
                if(j > 0) out.print(", ");
                out.print(paramTypes[j].getName());
            }
            out.println(");");
        }
    }

    private static void printFields(Class cl) {
        Field[] fields = cl.getDeclaredFields();

        for(Field f : fields) {
            Class type = f.getType();
            String name = f.getName();
            out.print("    ");
            String modifiers = Modifier.toString(f.getModifiers());
            if(modifiers.length() > 0) out.print(modifiers + " ");
            out.println(type.getName() + " " + name + ";");
        }
        
    }
    
}