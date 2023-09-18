package org.example.Calendar;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

public class CardPage {
    JFrame frame;
    JPanel card;
    JButton cancel;
    public static JButton save;
    public static JButton delete;

    CardPage(int c, int r, String date) {
        frame = new JFrame("Event card data");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        card = new JPanel();
        card.setSize(400, 400);

        frame.add(card);
        frame.setVisible(true);

        if (LoginPage.realId.get(LoginPage.numberPage).get(LoginPage.idCard[r][c]) != null && !LoginPage.realId.get(LoginPage.numberPage).get(LoginPage.idCard[r][c]).isEmpty()) {
            JLabel label = new JLabel("User "+LoginPage.realId.get(LoginPage.numberPage).get(LoginPage.idCard[r][c]) + " is scheduled for 00:0" + r + " on " + date);
            card.add(label);

            delete = new JButton("Delete an entry");
            delete.setBounds(40, 40, 90, 20);

            CardPage.delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    int number = LoginPage.numberCard.get(LoginPage.idLogin.get(LoginPage.userId)) - 1;
                    LoginPage.numberCard.put(LoginPage.idLogin.get(LoginPage.userId), number);
                    LoginPage.realId.get(LoginPage.numberPage).put(LoginPage.idCard[r][c], "");
                    frame.dispose();
                    CalendarPage Cl = new CalendarPage();
                }
            });
            card.add(delete);
        } else {
            JLabel label = new JLabel("Record user " + LoginPage.idLogin.get(LoginPage.userId) + " at 00:0" + r + " on  " + date + "?");
            card.add(label);

            save = new JButton("Record");
            save.setBounds(40, 40, 90, 20);

            save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    int number = LoginPage.numberCard.get(LoginPage.idLogin.get(LoginPage.userId)) + 1;
                    LoginPage.numberCard.put(LoginPage.idLogin.get(LoginPage.userId), number);
                    LoginPage.realId.get(LoginPage.numberPage).put(LoginPage.idCard[r][c], LoginPage.idLogin.get(LoginPage.userId));
                    frame.dispose();
                    CalendarPage Cl = new CalendarPage();
                }
            });
            card.add(save);
        }

        cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                CalendarPage Cl = new CalendarPage();
            }
        });
        card.add(cancel);
    }
}

