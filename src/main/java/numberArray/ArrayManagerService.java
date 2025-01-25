package numberArray;

import exceptions.InvalidInputException;


/**
 * Initializes the array management system and handles user interactions.
 * The method displays a menu of options and performs the corresponding actions based on user input.
 */
public class ArrayManagerService {

    public static final int MIN_RANDOM_ARRAY_LENGTH = 2;
    public static final int MAX_RANDOM_ARRAY_LENGTH = 20;

    private final UserInteractionService userInteractionService;


    /**
     * Constructs an ArrayManagerService with a new UserInteractionService instance.
     */
    public ArrayManagerService() {
        userInteractionService = new UserInteractionService();
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
            int choice = userInteractionService.getUserChoice("""
                    Choose an option:
                    1. Create array manually
                    2. Generate random array
                    Enter your choice:""");
            return switch (choice) {
                case 1 -> createArrayManually();
                case 2 -> NumberArray.generateRandomArray(MIN_RANDOM_ARRAY_LENGTH, MAX_RANDOM_ARRAY_LENGTH);
                default -> {
                    userInteractionService.printlnMessage("Invalid choice. Please try again.");
                    yield null;
                }
            };
        }
    }


    /**
     * Prompts the user to enter numbers separated by commas and creates an array from the input.
     *
     * @return the created array
     */
    private int[] createArrayManually() {
        while (true) {
            userInteractionService.printlnMessage("Enter numbers separated by commas (e.g., 1, 2, 3): ");
            String input = userInteractionService.readLine();
            try {
                return NumberArray.createArrayManually(input);
            } catch (InvalidInputException e) {
                userInteractionService.printlnMessage(e.getMessage());
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
        userInteractionService.printlnMessage("Enter a number to add: ");
        int[] newArray = NumberArray.addNumber(array, userInteractionService.readInt());
        userInteractionService.printlnMessage("Number was added to the array.");
        return newArray;
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
        userInteractionService.printlnMessage("Enter a number to remove: ");
        int number = userInteractionService.readInt();

        boolean numberExists = NumberArray.containsNumber(array, number);

        if (!numberExists) {
            userInteractionService.printlnMessage("Number " + number + " does not exist in the array.");
            return array;
        }

        while (true) {
                userInteractionService.printlnMessage("Remove all occurrences? (yes/no): ");
                String input = userInteractionService.readLine().toLowerCase();
                if (input.equals("yes") || input.equals("no")) {
                    boolean removeAll = input.equals("yes");
                    int[] newArray = NumberArray.removeNumber(array, number, removeAll);
                    userInteractionService.printlnMessage("Number " + number + " was removed from the array.");
                    return newArray;
                } else {
                    userInteractionService.printlnMessage("Invalid choice. Please enter 'yes' or 'no'.");
                }
        }
    }

    /**
     * Clears the array.
     *
     * @return null, representing an empty array
     */
    public int[] clearArray() {
        return NumberArray.clearArray();
    }

}