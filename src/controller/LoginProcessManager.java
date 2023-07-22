package controller;

import DAO.imple.AccountDAO;
import DAO.itf.AccountDAOInterface;
import model.Account;
import view.View;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class LoginProcessManager {
    private final AccountDAOInterface accountDAO;

    private String encode(String input) {
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return hexString.toString();
    }

    public LoginProcessManager() {
        this.accountDAO = new AccountDAO();
    }

    private String convert(char[] charArray) {
        StringBuilder str = new StringBuilder();
        for (char c : charArray) {
            if (c == ' ') {
                break;
            }
            str.append(c);
        }
        return str.toString();
    }

    public void processLogin(View view) {
        String username = view.getUserTextField().getText();
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        if (!pattern.matcher(username).find()) {
            View.showMessage(view.getLoginFrame(), "Username can only contain characters or numbers");
            return;
        }
        char[] passwdArray = view.getUserPasswordField().getPassword();
        String passwd = convert(passwdArray);

        if (!accountDAO.exist(username)) {
            View.showMessage(view.getLoginFrame(), "Account does not exist!");
            view.getUserPasswordField().setText("");
            view.getUserTextField().setText("");
        } else {
            String encodePasswd = encode(passwd);
            if (accountDAO.exist(username, encodePasswd)) {
                View.showMessage(view.getLoginFrame(), "Login successfully!");
                view.getLoginFrame().dispose();
                view.createFrame();
            } else {
                View.showMessage(view.getLoginFrame(), "Incorrect password!");
                view.getUserPasswordField().setText("");

            }
        }
    }
}
