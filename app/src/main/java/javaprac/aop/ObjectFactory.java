package javaprac.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class ObjectFactory {

    public static <T> T newInstance(Class<T> clazz) throws NoSuchMethodException,
                                                           InstantiationException,
                                                           IllegalAccessException,
                                                           InvocationTargetException {
        Annotation[] annotations = clazz.getAnnotations();
        List<IAspect> aspects = new ArrayList<IAspect>();

        for (Annotation anno : annotations) {
            if (anno instanceof Aspect) {
                IAspect newAspect = (IAspect)(((Aspect)anno).type().getConstructor().newInstance());
                aspects.add(newAspect);
            }
        }

        T inst = clazz.getConstructor().newInstance();

        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                clazz.getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                        throws IllegalAccessException, InvocationTargetException {

                        aspects.forEach(aspect -> aspect.before());
                        Object result = method.invoke(inst);
                        aspects.forEach(aspect -> aspect.after());

                        return result;
                    }
                }
        );
    }
}
