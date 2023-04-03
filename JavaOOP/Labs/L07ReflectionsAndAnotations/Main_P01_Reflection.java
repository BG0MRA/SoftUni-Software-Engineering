package L07ReflectionsAndAnotations;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;


public class Main_P01_Reflection {
    public static void main(String[] args)
            throws ClassNotFoundException, IOException, NoSuchMethodException,
            InvocationTargetException, InstantiationException,
            IllegalAccessException {

        Class<Reflection> clazz = Reflection.class;

        System.out.println(clazz);

        System.out.println(clazz.getSuperclass());

        Arrays.stream(clazz.getInterfaces())
                .forEach(System.out::println);

        Constructor<Reflection> consructor = clazz.getDeclaredConstructor();

        Reflection reflection = consructor.newInstance();

        System.out.println(reflection);

    }
}
