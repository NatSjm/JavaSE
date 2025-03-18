package numberArray;

/**
 * Represents menu options for the Array Management System.
 */
public enum MenuOption {
    PRINT_ARRAY(1, "Print array"),
    ADD_NUMBER(2, "Add number to array"),
    FIND_MAX(3, "Find max number"),
    FIND_MIN(4, "Find min number"),
    REMOVE_NUMBER(5, "Remove number from array"),
    CLEAR_ARRAY(6, "Clear array"),
    EXIT(7, "Exit");

    private final int code;
    private final String description;

    MenuOption(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static MenuOption fromCode(int code) {
        for (MenuOption option : values()) {
            if (option.code == code) {
                return option;
            }
        }
        throw new IllegalArgumentException("Invalid menu option code: " + code);
    }
}

