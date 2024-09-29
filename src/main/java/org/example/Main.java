package org.example;

import exceptions.InvalidInputException;
import numberArray.ArrayManager;

public class Main {
    public static void main(String[] args) {
        ArrayManager arrayManager = new ArrayManager();
        int[] currentArray = null;

        try {
            currentArray = arrayManager.initArray();
        } catch (InvalidInputException e) {
            System.err.println(e.getMessage());
        }

        if (currentArray == null) {
            return;
        }

        while (true) {
            try {
                int choice = arrayManager.getUserChoice("Choose an option: \n1. Print array \n2. Add number to array \n3. Find max number \n4. Find min number \n5. Remove number from array \n6. Clear array \n7. Exit\nChoose a number: ");
                switch (choice) {
                    case 1:
                        arrayManager.printArray(currentArray);
                        break;
                    case 2:
                        currentArray = arrayManager.addNumberToArray(currentArray);
                        break;
                    case 3:
                        System.out.println("Max number: " + arrayManager.findMax(currentArray));
                        break;
                    case 4:
                        System.out.println("Min number: " + arrayManager.findMin(currentArray));
                        break;
                    case 5:
                        currentArray = arrayManager.removeNumberFromArray(currentArray);
                        break;
                    case 6:
                        currentArray = arrayManager.clearArray();
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
                        currentArray = arrayManager.initArray();
                    } catch (InvalidInputException e) {
                        System.err.println(e.getMessage());
                    }
                }
            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
                arrayManager.getScanner().nextLine();
            }
        }
    }
}