package numberArray;

import exceptions.InvalidInputException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ArrayManagerServiceTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @Before
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    public void initArray_createArrayManually() throws InvalidInputException {
        String input = "1\n1, 2, 3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ArrayManagerService service = new ArrayManagerService();
        int[] array = service.initArray();
        assertArrayEquals(new int[]{1, 2, 3}, array);
    }

    @Test
    public void initArray_generateRandomArray() throws InvalidInputException {
        String input = "2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ArrayManagerService service = new ArrayManagerService();
        int[] array = service.initArray();
        assertNotNull(array);
        assertTrue(array.length >= ArrayManagerService.MIN_RANDOM_ARRAY_LENGTH && array.length <= ArrayManagerService.MAX_RANDOM_ARRAY_LENGTH);
    }

    @Test
    public void testPrintArray() {
        int[] array = {1, 2, 3};
        ArrayManagerService service = new ArrayManagerService();
        service.printArray(array);
        String expectedOutput = "1 2 3 \n".replace("\n", System.lineSeparator());
        String actualOutput = outContent.toString().replace("\r\n", "\n").replace("\n", System.lineSeparator());
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testAddNumberToArray_validInput() {
        String input = "4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ArrayManagerService service = new ArrayManagerService();
        int[] array = {1, 2, 3};
        int[] newArray = service.addNumberToArray(array);
        assertArrayEquals(new int[]{1, 2, 3, 4}, newArray);
        String expectedOutput = "Enter a number to add: Number was added to the array.\n";
        assertEquals(expectedOutput, outContent.toString().replace("\r\n", "\n"));
    }

    @Test
    public void testAddNumberToArray_invalidInput() {
        String input = "invalid\n4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ArrayManagerService service = new ArrayManagerService();
        int[] array = {1, 2, 3};
        int[] newArray = service.addNumberToArray(array);
        assertArrayEquals(new int[]{1, 2, 3, 4}, newArray);
        String expectedOutput = "Enter a number to add: Invalid input. Please enter a valid integer number.\nEnter a number to add: Number was added to the array.\n";
        assertEquals(expectedOutput, outContent.toString().replace("\r\n", "\n"));
    }

    @Test
    public void testFindMax() {
        ArrayManagerService service = new ArrayManagerService();
        int[] array = {1, 2, 3, 4, 5};
        int max = service.findMax(array);
        assertEquals(5, max);
    }

    @Test
    public void testFindMin() {
        ArrayManagerService service = new ArrayManagerService();
        int[] array = {5, 3, 8, 1, 4};
        int min = service.findMin(array);
        assertEquals(1, min);
    }

    @Test
    public void testRemoveNumberFromArray_removeFirstOccurrence() {
        String input = "2\nno\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ArrayManagerService service = new ArrayManagerService();
        int[] array = {1, 2, 3, 2};
        int[] newArray = service.removeNumberFromArray(array);
        assertArrayEquals(new int[]{1, 3, 2}, newArray);
        String expectedOutput = "Enter a number to remove: Remove all occurrences? (yes/no): ";
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    public void testRemoveNumberFromArray_removeAllOccurrences() {
        String input = "2\nyes\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ArrayManagerService service = new ArrayManagerService();
        int[] array = {1, 2, 3, 2};
        int[] newArray = service.removeNumberFromArray(array);
        assertArrayEquals(new int[]{1, 3}, newArray);
        String expectedOutput = "Enter a number to remove: Remove all occurrences? (yes/no): ";
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    public void testRemoveNumberFromArray_invalidNumberInput() {
        String input = "invalid\n2\nno\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ArrayManagerService service = new ArrayManagerService();
        int[] array = {1, 2, 3, 2};
        int[] newArray = service.removeNumberFromArray(array);
        assertArrayEquals(new int[]{1, 3, 2}, newArray);
        String expectedOutput = "Enter a number to remove: Invalid input. Please enter a valid integer number.\nRemove all occurrences? (yes/no): ";
        String actualOutput = outContent.toString().replace("\r\n", "\n").trim();
        assertTrue(actualOutput.contains(expectedOutput.trim()));
    }

    @Test
    public void testRemoveNumberFromArray_invalidYesNoInput() {
        String input = "2\nmaybe\nno\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ArrayManagerService service = new ArrayManagerService();
        int[] array = {1, 2, 3, 2};
        int[] newArray = service.removeNumberFromArray(array);
        assertArrayEquals(new int[]{1, 3, 2}, newArray);
        String expectedOutput = "Enter a number to remove: Remove all occurrences? (yes/no): Invalid choice. Please enter 'yes' or 'no'. ";
        String actualOutput = outContent.toString().replace("\r\n", "\n").trim();
        assertTrue(actualOutput.contains(expectedOutput.trim()));
    }

    @Test
    public void testClearArray() {
        ArrayManagerService service = new ArrayManagerService();
        int[] clearedArray = service.clearArray();
        assertNull(clearedArray);
    }
}