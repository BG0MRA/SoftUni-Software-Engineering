package E07ReflectionsAndAnotations.harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Class<RichSoilLand> clazz = RichSoilLand.class;
        Field[] fields = clazz.getDeclaredFields();

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equals("HARVEST")) {

            for (Field field : fields) {
                if (isMatchingModifier(field, command)) {
                    System.out.println(fieldInfo(field));
                }
            }
            command = scanner.nextLine();
        }


    }

    private static boolean isMatchingModifier(Field field, String command) {
        if (command.equals("private")) {
            return Modifier.isPrivate(field.getModifiers());
        } else if (command.equals("protected")) {
            return Modifier.isProtected(field.getModifiers());
        } else if (command.equals("public")) {
            return Modifier.isPublic(field.getModifiers());
        } else {
            return true;
        }
    }

    private static String fieldInfo(Field field) {
        String fieldModifiers = Modifier.toString(field.getModifiers());
        String fieldType = field.getType().getSimpleName();
        String fieldName = field.getName();
        return fieldModifiers + " " + fieldType + " " + fieldName;

    }

}
