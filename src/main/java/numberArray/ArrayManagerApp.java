package numberArray;

import exceptions.InvalidInputException;



/**
 * The ArrayManagerApp class provides a console-based application for managing an array of numbers.
 * It allows users to perform various operations such as printing the array, adding numbers, finding
 * the maximum and minimum numbers, removing numbers, and clearing the array.
 * The application also handles user interactions and manages the array state.
 */
public class ArrayManagerApp {

    private static final UserInteractionService userInteractionService = new UserInteractionService();
    private static final ArrayManagerService arrayManagerService = new ArrayManagerService();
    private static int[] currentArray;



    /**
     * Initializes the array management system and handles user interactions.
     * The method displays a menu of options and performs the corresponding actions based on user input.
     * It continues to run until the user chooses to exit the application.
     */
    public static void init() {
        userInteractionService.printlnMessage("Welcome to the array management system!");
        while (true) {
            try {
                if (isArrayEmpty()) {
                    initializeArray();
                }
                handleMenuOptions();

            } catch (Exception e) {
                userInteractionService.printlnMessage("An unexpected error occurred. " + e.getMessage());
            }
        }
    }

    /**
     * Checks if the current array is empty or null.
     *
     * @return true if the array is empty or null, false otherwise
     */
    private static boolean isArrayEmpty() {
        return currentArray == null || currentArray.length == 0;
    }



    /**
     * Initializes the array by prompting the user to create it manually or generate a random array.
     * Handles any input exceptions that may occur during initialization.
     */
    private static void initializeArray() {
        try {
            currentArray = arrayManagerService.initArray();
        } catch (InvalidInputException e) {
            userInteractionService.printlnMessage("Error initializing the array: " + e.getMessage());
        }
    }


    /**
     * Displays the menu options to the user and handles the selected option.
     * If an invalid option is selected, it prompts the user to try again.
     */
    private static void handleMenuOptions() {
        try {
            MenuOption selectedOption = getMenuChoice();
            executeMenuOption(selectedOption);
        } catch (InvalidInputException e) {
           userInteractionService.printlnMessage("Invalid choice. Please try again.");
        }
    }



    /**
     * Displays the current array and prompts the user to select a menu option.
     * Handles invalid menu option selections.
     *
     * @return the selected menu option
     * @throws InvalidInputException if the input is invalid
     */
    private static MenuOption getMenuChoice() throws InvalidInputException {
        final String menu = """
                Choose an option:
                1. Print array
                2. Add number to array
                3. Find max number
                4. Find min number
                5. Remove number from array
                6. Clear array
                7. Exit
                """;
        userInteractionService.printlnMessage("Current array: ");
        arrayManagerService.printArray(currentArray);
        while (true) {
            try {
                int choice = userInteractionService.getUserChoice(menu);
                return MenuOption.fromCode(choice);
            } catch (IllegalArgumentException e) {
                userInteractionService.printlnMessage("Invalid choice. Please select a valid option.");
            }
        }
    }


    /**
     * Executes the action corresponding to the selected menu option.
     * Handles any exceptions that may occur during the execution of the menu option.
     *
     * @param option the selected menu option
     */
    private static void executeMenuOption(MenuOption option) {
        try {
            switch (option) {
                case PRINT_ARRAY -> arrayManagerService.printArray(currentArray);
                case ADD_NUMBER -> currentArray = arrayManagerService.addNumberToArray(currentArray);
                case FIND_MAX -> System.out.println("Max number: " + arrayManagerService.findMax(currentArray));
                case FIND_MIN -> System.out.println("Min number: " + arrayManagerService.findMin(currentArray));
                case REMOVE_NUMBER -> currentArray = arrayManagerService.removeNumberFromArray(currentArray);
                case CLEAR_ARRAY -> {
                    currentArray = arrayManagerService.clearArray();
                    System.out.println("Array cleared.");
                }
                case EXIT -> {
                    userInteractionService.printlnMessage("Thank you for using the system. Goodbye!");
                    System.exit(0);
                }
                default -> userInteractionService.printlnMessage("Invalid choice, please try again.");
            }
            if (isArrayEmpty()) {
                userInteractionService.printlnMessage("Array is empty. Reinitializing...");
                initializeArray();
            }
        } catch (Exception e) {
            userInteractionService.printlnMessage("Unexpected error occurred: " + e.getMessage());
        }
    }
}
