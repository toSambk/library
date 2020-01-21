package customClassloader;

import java.lang.reflect.InvocationTargetException;

public class Demo {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        CustomClassloader customClassloader = new CustomClassloader("C:\\Users\\Sam\\IdeaProjects\\iFutureTaskOne\\target\\iFutureTaskOne-1.0.0.jar", "");

        Class<?> clazz = customClassloader.loadClass("GUI");

        clazz.getDeclaredConstructor().newInstance();

    }

}
