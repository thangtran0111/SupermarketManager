package model;

public class Account {
    private char[] username = new char[64];
    private String passwd;
    private String employeeID;

    public Account() {
    }
    public Account(char[] username, String passwd, String maNhanVien) {
        this.username = username;
        this.passwd = passwd;
        this.employeeID = maNhanVien;
    }

    public String getPasswd() {
        return passwd;
    }

}
