package numberArray;

import java.util.Scanner;

/**
 * The UserInteractionService class handles user input and output operations.
 */
public class UserInteractionService {

    private final Scanner scanner;

    /**
     * Constructs a UserInteractionService with a new Scanner instance.
     */
    public UserInteractionService() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prompts the user for a choice and returns the input as an integer.
     *
     * @param prompt the prompt message to display to the user
     * @return the user's choice as an integer
     */
    public int getUserChoice(String prompt) {
        System.out.print(prompt);
        return readInt();
    }

    /**
     * Reads an integer from the user input.
     *
     * @return the integer input by the user
     */
    public int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next();
        }

        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    /**
     * Reads a line of text from the user input.
     *
     * @return the line of text input by the user
     */
    public String readLine() {
        return scanner.nextLine().trim();
    }


    /**
     * Prints a message to the console with a newline.
     *
     * @param message the message to print
     */
    public void printlnMessage(String message) {
        System.out.println(message);
    }
}