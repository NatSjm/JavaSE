package numberArray;

import exceptions.InvalidInputException;

import java.util.Scanner;

public class ArrayManager {

    public static final int MIN_RANDOM_ARRAY_LENGTH = 2;
    public static final int MAX_RANDOM_ARRAY_LENGTH = 20;

    private final Scanner scanner;

    public ArrayManager() {
        scanner = new Scanner(System.in);
    }

    public int[] initArray() throws InvalidInputException {
        System.out.println("Welcome to the array management system!");

        while (true) {
            int choice = getUserChoice("Choose an option: \n1. Create array manually \n2. Generate random array \n3. Exit\nChoose a number: ");
            switch (choice) {
                case 1:
                    return createArrayManually();
                case 2:
                    return NumberArray.generateRandomArray(MIN_RANDOM_ARRAY_LENGTH, MAX_RANDOM_ARRAY_LENGTH);
                case 3:
                    System.out.println("Thank you for using the system. Goodbye!");
                    return null;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    public int getUserChoice(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    private int[] createArrayManually() {
        while (true) {
            System.out.print("Enter numbers separated by commas (e.g., 1, 2, 3): ");
            String input = scanner.nextLine();
            try {
                return NumberArray.createArrayManually(input);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void printArray(int[] array) {
        NumberArray.printArray(array);
    }

    public int[] addNumberToArray(int[] array) {
        while (true) {
            System.out.print("Enter a number to add: ");
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                scanner.nextLine();
                int[] newArray = NumberArray.addNumber(array, number);
                System.out.println("Number was added to the array.");
                return newArray;
            } else {
                System.out.println("Invalid input. Please enter a valid integer number.");
                scanner.next();
            }
        }
    }

    public int findMax(int[] array) {
        return NumberArray.findMax(array);
    }

    public int findMin(int[] array) {
        return NumberArray.findMin(array);
    }

    public int[] removeNumberFromArray(int[] array) {
        while (true) {
            System.out.print("Enter a number to remove: ");
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Remove all occurrences? (yes/no): ");
                boolean removeAll = scanner.nextLine().equalsIgnoreCase("yes");
                return NumberArray.removeNumber(array, number, removeAll);
            } else {
                System.out.println("Invalid input. Please enter a valid integer number.");
                scanner.next();
            }
        }
    }

    public int[] clearArray() {
        return NumberArray.clearArray();
    }

    public Scanner getScanner() {
        return scanner;
    }
}