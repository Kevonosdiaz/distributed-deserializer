package Objects;

import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public class TemplateCreator {
    public static Simple createSimple() {
    // Ask user for params of Simple object
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter the following parameters for the Simple object:");
    System.out.println("Please enter an integer value for the int field:");
    int intField = scanner.nextInt();
    System.out.println("Please enter a double value for the double field:");
    double doubleField = scanner.nextDouble();
    System.out.println("Please enter a character value for the char field:");
    char charField = scanner.next().charAt(0);

    // Create Simple object
    return new Simple(intField, doubleField, charField);
}

    public static Complex createComplex() {
        // Ask user for params of Complex object
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the following parameters for the Complex object:");
        System.out.println("Please enter an integer value for the int field:");
        int intField = scanner.nextInt();
        System.out.println("Please enter a double value for the double field:");
        double doubleField = scanner.nextDouble();
        System.out.println("Please enter a string value for the string field:");
        String stringField = scanner.next();
        Simple simpleField = createSimple();
        return new Complex(intField, doubleField, stringField, simpleField);
    }

    public static Object[] createObjectArray() {
        // Ask user for size of object array
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the size of the array:");
        int size = scanner.nextInt();
        Object[] objectArray = new Object[size];
        // Loop size times, asking user to add either a simple or complex object
        for (int i = 0; i < size; i++) {
            System.out.println("Would you like to add a Simple or Complex object? (simple/complex)");
            String userChoice = scanner.next();
            if (userChoice.equals("simple")) {
                Simple simple = createSimple();
                objectArray[i] = simple;
            } else {
                Complex complex = createComplex();
                objectArray[i] = complex;
            }
        }
        return objectArray;
    }
    public static int[] createIntArray() {
        // Ask user for size of int array
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the size of the array:");
        int size = scanner.nextInt();
        int[] intArray = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.println("Please enter an integer value for the array at index " + i + ":");
            intArray[i] = scanner.nextInt();
        }
        return intArray;
    }

    public static char[] createCharArray() {
        // Ask user for size of char array
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the size of the array:");
        int size = scanner.nextInt();
        char[] charArray = new char[size];
        for (int i = 0; i < size; i++) {
            System.out.println("Please enter a character value for the array at index " + i + ":");
            charArray[i] = scanner.next().charAt(0);
        }
        return charArray;
    }

    public static HashSet<?> createHashSet() {
        // Prompt user for data type of HashSet
        Scanner scanner = new Scanner(System.in);
        String dataType = "";
        do {
            System.out.println("Please enter the data type of the HashSet (int, double, or char):");
            dataType = scanner.next();
        } while (Objects.equals(dataType, "int") || Objects.equals(dataType, "double") || Objects.equals(dataType, "char")) ;
        // Keep getting input until user enters a valid data type

        if (Objects.equals(dataType, "int")) return createHashSetInt();
        if (Objects.equals(dataType, "double")) return createHashSetDouble();
        return createHashSetChar();
    }

    // Consider just merging or extracting from all three since only the loop differs

    public static HashSet<Integer> createHashSetInt() {
        // Ask user for size of HashSet
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the size of the HashSet:");
        int size = scanner.nextInt();
        HashSet<Integer> hashSet = new HashSet<>(size);
        for (int i = 0; i < size; i++) {
            System.out.println("Please enter an integer value for the HashSet at index " + i + ":");
            hashSet.add(scanner.nextInt());
        }
        return hashSet;
    }

    public static HashSet<Double> createHashSetDouble() {
        // Ask user for size of HashSet
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the size of the HashSet:");
        int size = scanner.nextInt();
        HashSet<Double> hashSet = new HashSet<>(size);
        for (int i = 0; i < size; i++) {
            System.out.println("Please enter a double value for the HashSet at index " + i + ":");
            hashSet.add(scanner.nextDouble());
        }
        return hashSet;
    }

    public static HashSet<Character> createHashSetChar() {
        // Ask user for size of HashSet
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the size of the HashSet:");
        int size = scanner.nextInt();
        HashSet<Character> hashSet = new HashSet<>(size);
        for (int i = 0; i < size; i++) {
            System.out.println("Please enter a character value for the HashSet at index " + i + ":");
            hashSet.add(scanner.next().charAt(0));
        }
        return hashSet;
    }
}
