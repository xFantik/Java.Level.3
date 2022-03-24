package home_works.lesson_7_Reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class Tester {
    public static <T> void start(T o) {
        ArrayList<Method> methods = getSortedMethods(o.getClass());
        for (Method method : methods) {
            method.setAccessible(true);
            try {
                Object[] args = generateArguments(method);
                System.out.print("     Invoke method: "+method.getName());
                if (args.length > 0)
                    System.out.print(", args = " + Arrays.toString(args));
                System.out.println();
                Object result = method.invoke(o, args);
                if (result != null)
                    System.out.println("     Method result: " + result);
                System.out.println();
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private static ArrayList<Method> getSortedMethods(Class<?> c) {
        ArrayList<Method> methodsList = new ArrayList<>();
        Method[] methods = c.getDeclaredMethods();
        Method beforeSuite = null;
        Method afterSuite = null;
        for (Method method : methods) {
            method.setAccessible(true);
            if (method.isAnnotationPresent(AfterSuite.class)) {
                if (afterSuite == null) {
                    afterSuite = method;
                    continue;
                } else
                    throw new RuntimeException("Allow only one annotation @AfterSuite ");
            }
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                if (beforeSuite == null) {
                    beforeSuite = method;
                    continue;
                } else
                    throw new RuntimeException("Allow only one annotation @BeforeSuite ");
            }
            if (method.isAnnotationPresent(Test.class)) {
                if (method.getAnnotation(Test.class).priority() < 1 || method.getAnnotation(Test.class).priority() > 10) {
                    throw new RuntimeException("Priority allow in (1 .. 10) range");
                }
                methodsList.add(method);
            }
        }
        methodsList.sort((o1, o2) -> o1.getAnnotation(Test.class).priority() - o2.getAnnotation(Test.class).priority());
        if (beforeSuite != null) {
            methodsList.add(0, beforeSuite);
        }
        if (afterSuite != null) {
            methodsList.add(afterSuite);
        }
        return methodsList;
    }

    private static Object[] generateArguments(Method m) {
        Object[] args = new Object[m.getParameterCount()];
        Class<?>[] types = m.getParameterTypes();
        for (int i = 0; i < args.length; i++) {
            if (types[i].equals(String.class)) {
                args[i] = "someString";
            } else if (types[i].equals(int.class)) {
                args[i] = (int) (Math.random() * 100);
            }
        }
        return args;
    }
}
