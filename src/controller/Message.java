package controller;

public enum Message {
    NULL_ID("NULL ID!"),
    ID_FORMAT_INCORRECT("ID has an incorrect format!"),
    ID_ALREADY_EXISTS("This ID already exists!"),
    BARCODE_ALREADY_EXISTS("This barcode already exists!"),
    ID_NOT_EXIST("This ID doesn't exist!"),
    ERROR_OCCURRED("An unexpected error occurred!!!"),
    SUCCESS("Success!"),
    USERNAME_CAN_ONLY_CONTAIN_CHARACTERS_OR_NUMBERS("Username can only contain characters or numbers!"),
    ACCOUNT_DOES_NOT_EXIST("Account does not exist!"),
    LOGIN_SUCCESSFUL("Login successful!"),
    PASSWORD_INCORRECT("Incorrect password!");
    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
