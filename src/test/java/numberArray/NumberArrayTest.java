package numberArray;

import exceptions.InvalidInputException;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;


public class NumberArrayTest {

    @Test
    public void testGenerateRandomArray() {
        int[] array = NumberArray.generateRandomArray(2, 5);
        assertTrue(array.length >= 2 && array.length <= 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateRandomArray_InvalidRange() {
        NumberArray.generateRandomArray(5, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateRandomArray_ZeroLength() {
        NumberArray.generateRandomArray(0, 5);
    }


    @Test
    public void testCreateArrayManually() throws InvalidInputException {
        int[] array = NumberArray.createArrayManually("1, 2, 3");
        assertArrayEquals(new int[]{1, 2, 3}, array);
    }

    @Test(expected = InvalidInputException.class)
    public void testCreateArrayManually_InvalidInput() throws InvalidInputException {
        NumberArray.createArrayManually("1, a, 3");
    }

    @Test(expected = InvalidInputException.class)
    public void testCreateArrayManually_EmptyInput() throws InvalidInputException {
        NumberArray.createArrayManually("");
    }


    @Test
    public void testPrintArray() {
        int[] array = {1, 2, 3};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            NumberArray.printArray(array);
            String expectedOutput = "1 2 3 \n".replace("\n", System.lineSeparator());
            String actualOutput = outContent.toString().replace("\r\n", "\n").replace("\n", System.lineSeparator());
            assertEquals(expectedOutput, actualOutput);
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void testPrintArray_EmptyArray() {
        int[] array = {};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            NumberArray.printArray(array);
            String expectedOutput = "There are no numbers in the array.\n".replace("\n", System.lineSeparator());
            String actualOutput = outContent.toString().replace("\r\n", "\n").replace("\n", System.lineSeparator());
            assertEquals(expectedOutput, actualOutput);
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void testAddNumber() {
        int[] array = {1, 2, 3};
        int[] newArray = NumberArray.addNumber(array, 4);
        assertArrayEquals(new int[]{1, 2, 3, 4}, newArray);
    }


    @Test
    public void testFindMax() {
        int[] array = {1, 2, 3};
        int max = NumberArray.findMax(array);
        assertEquals(3, max);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindMax_EmptyArray() {
        int[] array = {};
        NumberArray.findMax(array);
    }

    @Test
    public void testFindMin() {
        int[] array = {1, 2, 3};
        int min = NumberArray.findMin(array);
        assertEquals(1, min);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindMin_EmptyArray() {
        int[] array = {};
        NumberArray.findMin(array);
    }

    @Test
    public void testRemoveNumber_MultipleOccurence() {
        int[] array = {1, 2, 3, 2};
        int[] newArray = NumberArray.removeNumber(array, 2, true);
        assertArrayEquals(new int[]{1, 3}, newArray);
    }

    @Test
    public void testRemoveNumber_SingleOccurrence() {
        int[] array = {1, 2, 3, 2};
        int[] newArray = NumberArray.removeNumber(array, 2, false);
        assertArrayEquals(new int[]{1, 3, 2}, newArray);
    }

    @Test
    public void testRemoveNumber_NumberNotFound() {
        int[] array = {1, 2, 3};
        int[] newArray = NumberArray.removeNumber(array, 4, true);
        assertArrayEquals(new int[]{1, 2, 3}, newArray);
    }

    @Test
    public void testClearArray() {
        int[] array = NumberArray.clearArray();
        assertNull(array);
    }
}