package controller;

import DAO.imple.AccountDAO;
import DAO.itf.AccountDAOInterface;
import model.Account;
import view.View;

import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginProcessManager {
    private AccountDAOInterface daoTaiKhoan;

    private String encode(String input)  {
        StringBuilder hexString = new StringBuilder();
        try{
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
        this.daoTaiKhoan = new AccountDAO();
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
        String userName = view.getUserTextField().getText();
        if (userName.contains(" ")) {
            JOptionPane.showMessageDialog(view.getLoginFrame(), "Username must not have whitespace!");
            return;
        }
        char[] passwdArray = view.getUserPasswordField().getPassword();
        String passwd = convert(passwdArray);

        Account account = daoTaiKhoan.get(userName);
        if(account == null){
            view.showMessage(view.getLoginFrame(), "Account does not exist!");
            view.getUserPasswordField().setText("");
            view.getUserTextField().setText("");
        }else{
            String encodePasswd = encode(passwd);
            if(encodePasswd.equals(String.valueOf(account.getPasswd()))){
                view.showMessage(view.getLoginFrame(), "Login successfully!");
                view.getLoginFrame().dispose();
                view.createHomeFrame();
            }else{
                view.showMessage(view.getLoginFrame(), "Incorrect password!");
                view.getUserPasswordField().setText("");

            }
        }
    }
}
