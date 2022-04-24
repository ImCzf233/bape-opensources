package mc.bape.utils;

import java.lang.reflect.*;
import java.util.*;

public class ReflectionUtil
{
    public static Object getFieldValue(final Object obj, final String... fields) {
        final Class<?> clazz = obj.getClass();
        for (final String string : fields) {
            try {
                final Field f = clazz.getDeclaredField(string);
                if (f != null) {
                    f.setAccessible(true);
                    try {
                        return f.get(obj);
                    }
                    catch (IllegalArgumentException | IllegalAccessException ex2) {
                        final Exception e = ex2;
                        e.printStackTrace();
                        return null;
                    }
                }
            }
            catch (NoSuchFieldException e2) {}
        }
        return null;
    }
    
    public static Object getFieldValue(final Class<?> clazz, final String... fields) {
        for (final String string : fields) {
            try {
                final Field f = clazz.getDeclaredField(string);
                if (f != null) {
                    f.setAccessible(true);
                    try {
                        return f.get(null);
                    }
                    catch (IllegalArgumentException | IllegalAccessException ex2) {
                        final Exception e = ex2;
                        e.printStackTrace();
                        return null;
                    }
                }
            }
            catch (NoSuchFieldException e2) {}
        }
        return null;
    }
    
    public static void setFieldValue(final Object obj, final Object value, final String... fields) {
        final Class<?> clazz = obj.getClass();
        for (final String string : fields) {
            try {
                final Field f = clazz.getDeclaredField(string);
                if (f != null) {
                    f.setAccessible(true);
                    try {
                        f.set(obj, value);
                    }
                    catch (IllegalArgumentException | IllegalAccessException ex2) {
                        final Exception e = ex2;
                        e.printStackTrace();
                        return;
                    }
                }
            }
            catch (NoSuchFieldException e2) {}
        }
    }
    
    public static void setFieldValue(final Class<?> clazz, final Object value, final String... fields) {
        for (final String string : fields) {
            try {
                final Field f = clazz.getDeclaredField(string);
                if (f != null) {
                    f.setAccessible(true);
                    try {
                        f.set(null, value);
                    }
                    catch (IllegalArgumentException | IllegalAccessException ex2) {
                        final Exception e = ex2;
                        e.printStackTrace();
                    }
                }
            }
            catch (NoSuchFieldException e2) {}
        }
    }
    
    public static <T> T copy(final T src, final T dst) {
        for (final Field f : getAllFields(src.getClass())) {
            if (!Modifier.isFinal(f.getModifiers())) {
                if (!Modifier.isStatic(f.getModifiers())) {
                    f.setAccessible(true);
                    try {
                        f.set(dst, f.get(src));
                    }
                    catch (IllegalArgumentException | IllegalAccessException ex2) {
                        final Exception e = ex2;
                        e.printStackTrace();
                    }
                }
            }
        }
        return dst;
    }
    
    public static Field[] getAllFields(Class<?> clazz) {
        final ArrayList<Field> fields = new ArrayList<Field>();
        do {
            for (final Field f : clazz.getDeclaredFields()) {
                fields.add(f);
            }
            clazz = clazz.getSuperclass();
        } while (clazz != Object.class && clazz != null);
        return fields.toArray(new Field[0]);
    }
}
