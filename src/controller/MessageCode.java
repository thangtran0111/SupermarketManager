package controller;

public enum MessageCode {
    NULL_ID("NULL ID"),
    ID_FORMAT_INCORRECT("This ID isn't in the correct format"),
    ID_ALREADY_EXISTS("This ID already exists"),
    BARCODE_ALREADY_EXISTS("This barcode already exists"),
    ID_NOT_EXIST("This ID doesn't exist"),
    ERROR_OCCURRED("An error occurred"),
    SUCCESS("Success");

    private final String message;

    MessageCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
