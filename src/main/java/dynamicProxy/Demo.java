package dynamicProxy;

import java.lang.reflect.Proxy;

public class Demo {

    public static void main(String[] args) {

        Reader originalReader = new OriginalReader();

        CustomInvocationHandler invocationHandler = new CustomInvocationHandler(originalReader);

        Reader proxyReader = (Reader)Proxy.newProxyInstance(originalReader.getClass().getClassLoader(),
                                originalReader.getClass().getInterfaces(),
                                invocationHandler);

        proxyReader.close();

    }

}
