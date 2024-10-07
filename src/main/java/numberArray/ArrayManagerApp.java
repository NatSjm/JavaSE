package numberArray;

import exceptions.InvalidInputException;


/**
 * The ArrayManagerApp class provides a console-based application for managing an array of numbers.
 * It allows users to perform various operations such as printing the array, adding numbers, finding
 * the maximum and minimum numbers, removing numbers, and clearing the array.
 */
public class ArrayManagerApp {

    private static final ArrayManagerService arrayManagerService = new ArrayManagerService();
    private static int[] currentArray;

    /**
     * Initializes the array management system and handles user interactions.
     * The method displays a menu of options and performs the corresponding actions based on user input.
     */
    public static void init() {
        System.out.println("Welcome to the array management system!");
        try {
            currentArray = arrayManagerService.initArray();
        } catch (InvalidInputException e) {
            System.err.println(e.getMessage());
        }

        if (currentArray == null) {
            return;
        }

        while (true) {
            try {
                int choice = arrayManagerService.getUserChoice("Choose an option: \n1. Print array \n2. Add number to array \n3. Find max number \n4. Find min number \n5. Remove number from array \n6. Clear array \n7. Exit\nChoose a number: ");
                switch (choice) {
                    case 1:
                        arrayManagerService.printArray(currentArray);
                        break;
                    case 2:
                        currentArray = arrayManagerService.addNumberToArray(currentArray);
                        break;
                    case 3:
                        System.out.println("Max number: " + arrayManagerService.findMax(currentArray));
                        break;
                    case 4:
                        System.out.println("Min number: " + arrayManagerService.findMin(currentArray));
                        break;
                    case 5:
                        currentArray = arrayManagerService.removeNumberFromArray(currentArray);
                        break;
                    case 6:
                        currentArray = arrayManagerService.clearArray();
                        System.out.println("Array cleared.");
                        break;
                    case 7:
                        System.out.println("Thank you for using the system. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }

                if (currentArray == null || currentArray.length == 0) {
                    try {
                        currentArray = arrayManagerService.initArray();
                    } catch (InvalidInputException e) {
                        System.err.println(e.getMessage());
                    }
                }
            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
                arrayManagerService.getScanner().nextLine();
            }
        }
    }
}
