package numberArray;

import exceptions.InvalidInputException;

import java.util.Scanner;

/**
 * Initializes the array management system and handles user interactions.
 * The method displays a menu of options and performs the corresponding actions based on user input.
 */
public class ArrayManagerService {

    public static final int MIN_RANDOM_ARRAY_LENGTH = 2;
    public static final int MAX_RANDOM_ARRAY_LENGTH = 20;

    private final Scanner scanner;

    /**
     * Constructs an ArrayManagerService with a new Scanner instance.
     */
    public ArrayManagerService() {
        scanner = new Scanner(System.in);
    }


    /**
     * Initializes the array based on user input.
     * The user can choose to create the array manually, generate a random array, or exit.
     *
     * @return the initialized array or null if the user chooses to exit
     * @throws InvalidInputException if the input is invalid
     */
    public int[] initArray() throws InvalidInputException {

        while (true) {
            int choice = getUserChoice("Choose an option: \n1. Create array manually \n2. Generate random array \nChoose a number: ");
            switch (choice) {
                case 1:
                    return createArrayManually();
                case 2:
                    return NumberArray.generateRandomArray(MIN_RANDOM_ARRAY_LENGTH, MAX_RANDOM_ARRAY_LENGTH);
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }


    /**
     * Prompts the user for a choice and returns the input as an integer.
     *
     * @param prompt the prompt message to display to the user
     * @return the user's choice as an integer
     */
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

    /**
     * Prompts the user to enter numbers separated by commas and creates an array from the input.
     *
     * @return the created array
     */
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


    /**
     * Prints the elements of the array to the console.
     *
     * @param array the array to be printed
     */
    public void printArray(int[] array) {
        NumberArray.printArray(array);
    }


    /**
     * Prompts the user to enter a number and adds it to the array.
     *
     * @param array the original array
     * @return a new array with the added number
     */
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

    /**
     * Finds the maximum number in the array.
     *
     * @param array the array to be searched
     * @return the maximum number in the array
     */
    public int findMax(int[] array) {
        return NumberArray.findMax(array);
    }


    /**
     * Finds the minimum number in the array.
     *
     * @param array the array to be searched
     * @return the minimum number in the array
     */
    public int findMin(int[] array) {
        return NumberArray.findMin(array);
    }

    /**
     * Prompts the user to enter a number and removes it from the array.
     * The user can choose to remove all occurrences of the number or just the first occurrence.
     *
     * @param array the original array
     * @return a new array with the number removed
     */
    public int[] removeNumberFromArray(int[] array) {
        System.out.print("Enter a number to remove: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer number.");
            scanner.next();
        }
        int number = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Remove all occurrences? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        while (!response.equals("yes") && !response.equals("no")) {
            System.out.println("Invalid choice. Please enter 'yes' or 'no'.");
            response = scanner.nextLine().trim().toLowerCase();
        }

        boolean removeAll = response.equals("yes");
        return NumberArray.removeNumber(array, number, removeAll);
    }

    /**
     * Clears the array.
     *
     * @return null, representing an empty array
     */
    public int[] clearArray() {
        return NumberArray.clearArray();
    }


    /**
     * Returns the Scanner instance used for user input.
     *
     * @return the Scanner instance
     */
    public Scanner getScanner() {
        return scanner;
    }
}