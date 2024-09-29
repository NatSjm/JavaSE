package numberArray;

import exceptions.InvalidInputException;

import java.util.Arrays;
import java.util.Random;


public class NumberArray {

    public static int[] generateRandomArray(int minLen, int maxLen) {
        if (minLen <= 0 || maxLen <= 0 || minLen > maxLen) {
            throw new IllegalArgumentException("Invalid length range: minLen must be <= maxLen and the lengths must be greater than 0.");
        }

        Random random = new Random();
        int length = random.nextInt(maxLen - minLen + 1) + minLen;

        return random.ints(length)
                .toArray();
    }




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

    public static void printArray(int[] array) {
        if (array == null || array.length == 0) {
            System.out.println("There are no numbers in the array.");
            return;
        }
        Arrays.stream(array)
                .forEach(number -> System.out.print(number + " "));
        System.out.println();
    }

    public static int[] addNumber(int[] array, int number) {
        int[] newArray = Arrays.copyOf(array, array.length + 1);
        newArray[array.length] = number;
        return newArray;
    }

    public static int findMax(int[] array) {
        return Arrays.stream(array).max().orElseThrow(() -> new IllegalArgumentException("Array is empty"));
    }

    public static int findMin(int[] array) {
        return Arrays.stream(array).min().orElseThrow(() -> new IllegalArgumentException("Array is empty"));
    }

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

    public static int[] clearArray() {
        return null;
    }
}
