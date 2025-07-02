package taskk26_Annotations.b;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Method annotations, selective method invocation
public class Main {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        Class<?> clazz = processor.getClass();

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ImportantMethod.class)) {
//                Invoke critical methods only
                try {
                    method.invoke(processor);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
