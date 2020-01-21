package customClassloader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class CustomClassloader extends ClassLoader {

    private static String WARNING = "Warning: No jar file found.";
    private HashMap<String, Class<?>> cache = new HashMap<>();
    private String jarName;
    private String packageName;

    public CustomClassloader(String jarName, String packageName) {
        this.jarName = jarName;
        this.packageName = packageName;

        cache();
    }

    private void cache() {
        try {
            JarFile jarFile = new JarFile(jarName);
            Enumeration entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry) entries.nextElement();
                if (match(normalize(jarEntry.getName()), packageName)) {
                    byte[] classData = loadClassData(jarFile, jarEntry);
                    if (classData != null) {
                        Class<?> clazz = defineClass(stripClassName(normalize(jarEntry.getName())), classData, 0, classData.length);
                        cache.put(clazz.getName(), clazz);
                    }
                }
            }
        } catch (IOException exc) {
            System.out.println(WARNING);
        }
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {

        Class<?> result = cache.get(name);

        if (result == null) {
            result = cache.get(packageName + "." + name);
        }

        if (result == null) {
            result = super.findSystemClass(name);
        }

        System.out.println("===loadClass(" + name + ")");

        return result;
    }

    private boolean match(String className, String packageName) {
        return className.startsWith(packageName) && className.endsWith(".class");
    }

    private String normalize(String className) {
        return className.replace(File.separator, ".");
    }

    private String stripClassName(String fullClassName) {
        return fullClassName.substring(0, fullClassName.length() - 6);
    }

    private byte[] loadClassData(JarFile jarFile, JarEntry jarEntry) throws IOException {
        long size = jarEntry.getSize();
        if (size == -1 || size == 0) {
            return null;
        }
        byte[] data = new byte[(int) size];
        InputStream in = jarFile.getInputStream(jarEntry);
        in.read(data);
        return data;
    }
}
