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

    public char[] getUsername() {
        return username;
    }

    public void setUsername(char[] username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
}
