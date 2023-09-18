package org.example.Calendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class LoginPage {
    public static int numberPage = 0;
    public static int userId;
    public static int[][] idCard = new int[25][8];
    public static SimpleDateFormat sourceDateFormat = new SimpleDateFormat("EEEE-dd-MM-yyyy", Locale.ENGLISH);
    public static HashMap<String, String> userCredentials = new HashMap<>();
    public static HashMap<String, Integer> numberCard = new HashMap<>();
    public static HashMap<Integer, String> idLogin = new HashMap<>();
    public static HashMap<Integer, Calendar> date = new HashMap<>();
    public static HashMap<Integer, HashMap<Integer, String>> realId = new HashMap<>();
    JFrame frame;
    Container container;
    JTextField loginField = new JTextField(20);
    JTextField passwordField = new JTextField(20);
    JLabel loginLabel = new JLabel("enter your username");
    JLabel passwordLabel = new JLabel("enter the password");
    JButton loginButton = new JButton("Enter");
    ActionListener loginAction = new TestActionListener();

    LoginPage() {
        frame = new JFrame("Authorization");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginButton.addActionListener(loginAction);

        container = frame.getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        container.add(loginLabel);
        container.add(loginField);
        container.add(passwordLabel);
        container.add(passwordField);
        container.add(loginButton);

        frame.setVisible(true);
    }

    public class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (validateCredentials(loginField.getText(), passwordField.getText())) {
                int i = 1;
                while (idLogin.containsKey(i)) {
                    if (idLogin.get(i).equals(loginField.getText())) {
                        userId = i;
                    }
                    i++;
                }
                frame.dispose();
                CalendarPage Cl = new CalendarPage();
            } else {
                showWarning();
            }
        }
    }

    private void showWarning() {
        JOptionPane.showMessageDialog(frame, "Please check your username and password", "Wrong Data!", JOptionPane.ERROR_MESSAGE);
    }

    private boolean validateCredentials(String username, String password) {
        return userCredentials.containsKey(username) && password.equals(userCredentials.get(username));
    }

    public static void main(String[] args) {
        LoginPage lp = new LoginPage();

        idLogin.put(1, "artur");
        userCredentials.put(idLogin.get(1), "2");
        idLogin.put(2, "tutu");
        userCredentials.put(idLogin.get(2), "2");
        HashMap<Integer, String> id = new HashMap<>();
        int n = 1;
        for (int i = 0; i < 25; i++) {
            for (int y = 1; y < 8; y++) {
                LoginPage.idCard[i][y] = n;
                n++;
                id.put(LoginPage.idCard[i][y],"");
                LoginPage.realId.put(0,id);
            }
        }

        realId.get(0).put(25, idLogin.get(1));
        realId.get(0).put(58, idLogin.get(1));
        realId.get(0).put(12, idLogin.get(1));
        realId.get(0).put(1, idLogin.get(1));

        realId.get(0).put(56, idLogin.get(2));
        realId.get(0).put(78, idLogin.get(2));
        realId.get(0).put(67, idLogin.get(2));
        realId.get(0).put(87, idLogin.get(2));

        numberCard.put(idLogin.get(1), 4);
        numberCard.put(idLogin.get(2), 4);
    }
}