package controller;

import DAO.DAOFactory;
import view.View;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class LoginProcessManager {
    private final DAOFactory daoFactory;

    LoginProcessManager(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
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

    void processLogin(View view) {
        String username = view.getUserTextField().getText();
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        if (!pattern.matcher(username).find()) {
            View.showMessage(view.getLoginFrame(), Message.USERNAME_CAN_ONLY_CONTAIN_CHARACTERS_OR_NUMBERS.getMessage());
            return;
        }

        char[] passwdArray = view.getUserPasswordField().getPassword();
        String passwd = new String(passwdArray);
        Arrays.fill(passwdArray, ' ');

        if (!daoFactory.getAccountDAO().exist(username)) {
            View.showMessage(view.getLoginFrame(), Message.ACCOUNT_DOES_NOT_EXIST.getMessage());
            view.getUserPasswordField().setText("");
        } else {
            String encodePasswd = encode(passwd);
            if (daoFactory.getAccountDAO().exist(username, encodePasswd)) {
                View.showMessage(view.getLoginFrame(), Message.LOGIN_SUCCESSFUL.getMessage());
                view.setCurrentAccount(view.getUserTextField().getText());
                view.getLoginFrame().dispose();
                view.createFrame();
            } else {
                View.showMessage(view.getLoginFrame(), Message.PASSWORD_INCORRECT.getMessage());
                view.getUserPasswordField().setText("");
            }
        }
    }

}
