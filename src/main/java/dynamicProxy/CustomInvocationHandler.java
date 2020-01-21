package dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler {

    private Reader originalReader;

    public CustomInvocationHandler(Reader reader) {
        originalReader = reader;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if(method.getName().equals("close")) {
            System.out.println("Method call is intercepted by proxy...");
        }

        return method.invoke(originalReader, args);

    }
}
