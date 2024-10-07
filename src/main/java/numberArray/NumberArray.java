package numberArray;

import exceptions.InvalidInputException;

import java.util.Arrays;
import java.util.Random;

/**
 * The NumberArray class provides methods to manage an array of numbers.
 * It includes functionalities to generate random arrays, create arrays manually,
 * add and remove numbers, find the maximum and minimum numbers, and clear the array.
 */
public class NumberArray {

    /**
     * Generates a random array of integers within the specified length range.
     *
     * @param minLen the minimum length of the array
     * @param maxLen the maximum length of the array
     * @return an array of random integers
     * @throws IllegalArgumentException if minLen is greater than maxLen or if any length is less than or equal to 0
     */
    public static int[] generateRandomArray(int minLen, int maxLen) {
        if (minLen <= 0 || maxLen <= 0 || minLen > maxLen) {
            throw new IllegalArgumentException("Invalid length range: minLen must be <= maxLen and the lengths must be greater than 0.");
        }

        Random random = new Random();
        int length = random.nextInt(maxLen - minLen + 1) + minLen;

        return random.ints(length)
                .toArray();
    }

    /**
     * Creates an array manually from a comma-separated string of numbers.
     *
     * @param input the comma-separated string of numbers
     * @return an array of integers
     * @throws InvalidInputException if the input is null, empty, or contains invalid numbers
     */
    public static int[] createArrayManually(String input) throws InvalidInputException {
        if (input == null || input.isEmpty()) {
            throw new InvalidInputException("Input cannot be null or empty.");
        }

        String[] stringNumbers = input.split(",\\s*");

        int[] numbers = new int[stringNumbers.length];
        for (int i = 0; i < stringNumbers.length; i++) {
            try {
                numbers[i] = Integer.parseInt(stringNumbers[i]);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Invalid number: " + stringNumbers[i]);
            }
        }

        return numbers;
    }

    /**
     * Prints the elements of the array to the console.
     *
     * @param array the array to be printed
     */
    public static void printArray(int[] array) {
        if (array == null || array.length == 0) {
            System.out.println("There are no numbers in the array.");
            return;
        }
        Arrays.stream(array)
                .forEach(number -> System.out.print(number + " "));
        System.out.println();
    }


    /**
     * Adds a number to the array.
     *
     * @param array  the original array
     * @param number the number to be added
     * @return a new array with the added number
     */
    public static int[] addNumber(int[] array, int number) {
        int[] newArray = Arrays.copyOf(array, array.length + 1);
        newArray[array.length] = number;
        return newArray;
    }


    /**
     * Finds the maximum number in the array.
     *
     * @param array the array to be searched
     * @return the maximum number in the array
     * @throws IllegalArgumentException if the array is empty
     */
    public static int findMax(int[] array) {
        return Arrays.stream(array).max().orElseThrow(() -> new IllegalArgumentException("Array is empty"));
    }

    /**
     * Finds the minimum number in the array.
     *
     * @param array the array to be searched
     * @return the minimum number in the array
     * @throws IllegalArgumentException if the array is empty
     */
    public static int findMin(int[] array) {
        return Arrays.stream(array).min().orElseThrow(() -> new IllegalArgumentException("Array is empty"));
    }

    /**
     * Removes a number from the array. If removeAll is true, all occurrences of the number are removed.
     * Otherwise, only the first occurrence is removed.
     *
     * @param array     the original array
     * @param number    the number to be removed
     * @param removeAll whether to remove all occurrences of the number
     * @return a new array with the number removed
     */
    public static int[] removeNumber(int[] array, int number, boolean removeAll) {
        if (Arrays.stream(array).noneMatch(n -> n == number)) {
            System.out.println("Number " + number + " does not exist in the array.");
            return array;
        }

        if (removeAll) {
            return Arrays.stream(array)
                    .filter(n -> n != number)
                    .toArray();

        } else {
            boolean[] removed = {false};
            return Arrays.stream(array)
                    .filter(n -> {
                        if (n == number && !removed[0]) {
                            removed[0] = true;
                            return false;
                        }
                        return true;
                    })
                    .toArray();
        }

    }


    /**
     * Clears the array.
     *
     * @return null, representing an empty array
     */
    public static int[] clearArray() {
        return null;
    }
}
