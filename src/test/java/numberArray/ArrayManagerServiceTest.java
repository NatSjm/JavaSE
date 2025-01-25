package numberArray;

import exceptions.InvalidInputException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

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

    private void setInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    private String normalizeOutput(String output) {
        return output.replace("\r\n", "\n").trim();
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
    public void initArray_invalidChoice_returnsNull() throws InvalidInputException {
        String input = "3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ArrayManagerService service = new ArrayManagerService();
        int[] array = service.initArray();
        assertNull(array);
    }

    @Test
    public void createArrayManually_invalidInput_retriesUntilValid() throws Exception {
        String input = "invalid\n1, 2, 3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ArrayManagerService service = new ArrayManagerService();

        Method method = ArrayManagerService.class.getDeclaredMethod("createArrayManually");
        method.setAccessible(true);

        int[] array = (int[]) method.invoke(service);
        assertArrayEquals(new int[]{1, 2, 3}, array);
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
        setInput("4\n");
        ArrayManagerService service = new ArrayManagerService();
        int[] array = {1, 2, 3};
        int[] newArray = service.addNumberToArray(array);


        assertArrayEquals(new int[]{1, 2, 3, 4}, newArray);

        String expectedOutput = "Enter a number to add: \nNumber was added to the array.";

        String actualOutput = normalizeOutput(outContent.toString());

        assertEquals(expectedOutput, actualOutput);
    }
    @Test
    public void addNumberToArray_invalidInput_retriesUntilValid() {
        String input = "invalid\n4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ArrayManagerService service = new ArrayManagerService();
        int[] array = {1, 2, 3};
        int[] newArray = service.addNumberToArray(array);
        assertArrayEquals(new int[]{1, 2, 3, 4}, newArray);
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

        String expectedOutput = "Enter a number to remove: \nRemove all occurrences? (yes/no): \nNumber 2 was removed from the array.";
        String actualOutput = normalizeOutput(outContent.toString());

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testRemoveNumberFromArray_removeAllOccurrences() {
        String input = "2\nyes\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ArrayManagerService service = new ArrayManagerService();
        int[] array = {1, 2, 3, 2};
        int[] newArray = service.removeNumberFromArray(array);
        assertArrayEquals(new int[]{1, 3}, newArray);
        String expectedOutput = "Enter a number to remove: \nRemove all occurrences? (yes/no): ";
        assertTrue(normalizeOutput(outContent.toString()).contains(expectedOutput));


    }

    @Test
    public void removeNumberFromArray_numberNotInArray_returnsOriginalArray() {
        String input = "5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ArrayManagerService service = new ArrayManagerService();
        int[] array = {1, 2, 3};
        int[] newArray = service.removeNumberFromArray(array);

        assertArrayEquals(array, newArray);

        String expectedOutput = "Enter a number to remove: \nNumber 5 does not exist in the array.";
        String actualOutput = normalizeOutput(outContent.toString());



        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testRemoveNumberFromArray_invalidYesNoInput() {
        String input = "2\nmaybe\nno\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ArrayManagerService service = new ArrayManagerService();
        int[] array = {1, 2, 3, 2};
        int[] newArray = service.removeNumberFromArray(array);
        assertArrayEquals(new int[]{1, 3, 2}, newArray);
        String expectedOutput = "Enter a number to remove: \nRemove all occurrences? (yes/no): \nInvalid choice. Please enter 'yes' or 'no'. ";
        String actualOutput = normalizeOutput(outContent.toString());
        assertTrue(actualOutput.contains(expectedOutput.trim()));
    }

    @Test
    public void testClearArray() {
        ArrayManagerService service = new ArrayManagerService();
        int[] clearedArray = service.clearArray();
        assertNull(clearedArray);
    }
}