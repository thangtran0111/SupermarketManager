package controller;

import DAO.DAOFactory;
import DAO.itf.AccountDAOInterface;
import view.View;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class LoginProcessManager {
    private final AccountDAOInterface accountDAO;

    public LoginProcessManager(DAOFactory daoFactory) {
        this.accountDAO = daoFactory.getAccountDAO();
    }

    private String encode(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Password encoding failed.", e);
        }
    }

    public void processLogin(View view) {
        String username = view.getUserTextField().getText();
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        if (!pattern.matcher(username).find()) {
            View.showMessage(view.getLoginFrame(), "Username can only contain characters or numbers");
            return;
        }

        char[] passwdArray = view.getUserPasswordField().getPassword();
        String passwd = new String(passwdArray);
        Arrays.fill(passwdArray, ' ');

        if (!accountDAO.exist(username)) {
            View.showMessage(view.getLoginFrame(), "Account does not exist!");
            view.getUserPasswordField().setText("");
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
