package org.example.Calendar;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.HashMap;
//
public class CalendarPage {
    public JFrame frame;
    Container container;
    public JTable table;
    public JScrollPane scrollPane;
    JButton right;
    JButton left;

    public CalendarPage() {
        CalendarMake();
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK);

        if (day_of_week == 1) {
            c.roll(Calendar.DAY_OF_YEAR, + 7*LoginPage.numberPage);
            LoginPage.date.put(7, c);
            c = Calendar.getInstance();
            for (int i = 2; i < 8; i++) {
                c.roll(Calendar.DAY_OF_YEAR, -day_of_week +i + 7*LoginPage.numberPage);
                LoginPage.date.put(i - 1, c);
                c = Calendar.getInstance();
            }
        } else {
            for (int i = 2; i < 8; i++) {
                if (i == day_of_week) {
                    c = Calendar.getInstance();
                    c.roll(Calendar.DAY_OF_YEAR, + 7*LoginPage.numberPage);
                    LoginPage.date.put(i - 1, c);
                } else {
                    c.roll(Calendar.DAY_OF_YEAR, -day_of_week +i + 7*LoginPage.numberPage);
                    LoginPage.date.put(i - 1, c);
                }
                c = Calendar.getInstance();
            }
            c.roll(Calendar.DAY_OF_YEAR, -day_of_week + 8 +7*LoginPage.numberPage);
            LoginPage.date.put(7, c);
        }

        frame = new JFrame("Calendar of events");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = frame.getContentPane();

        table = new JTable(new MyTable());
        scrollPane = new JScrollPane(table);

        int i = 1;
        DefaultListModel<String> listModel = new DefaultListModel<>();

        while (LoginPage.idLogin.containsKey(i)) {
            listModel.addElement("The user " + LoginPage.idLogin.get(i) + " has " + LoginPage.numberCard.get(LoginPage.idLogin.get(i))+" cards");
            i++;
        }

        JList<String> list = new JList<>(listModel);
        container.add(list, BorderLayout.NORTH);

        right = new JButton("->");
        right.setSize(50, 20);
        left = new JButton("<-");

        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                LoginPage.numberPage++;
                frame.dispose();
                CalendarPage Cl = new CalendarPage();
            }
        });

        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                LoginPage.numberPage--;
                frame.dispose();
                CalendarPage Cl = new CalendarPage();
            }
        });

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                String sll = (String) table.getValueAt(table.rowAtPoint(e.getPoint()),table.columnAtPoint(e.getPoint()));
                if (sll.equals(LoginPage.idLogin.get(LoginPage.userId)) || sll.isEmpty()) {
                    CardPage card = new CardPage(table.columnAtPoint(e.getPoint()), table.rowAtPoint(e.getPoint()), table.getColumnName(table.columnAtPoint(e.getPoint())));
                    frame.dispose();
                }
            }
        });

        JButton cancel = new JButton("Exit");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                LoginPage Cl = new LoginPage();
            }
        });

        container.add(scrollPane, BorderLayout.CENTER);
        container.add(right, BorderLayout.EAST);
        container.add(left, BorderLayout.WEST);
        container.add(cancel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void CalendarMake(){
        HashMap<Integer, String> id = new HashMap<>();
        if(!LoginPage.realId.containsKey(LoginPage.numberPage)){
        for (int i = 0; i < 25; i++) {
                for (int y = 1; y < 8; y++) {
                    id.put(LoginPage.idCard[i][y],"");
                    LoginPage.realId.put(LoginPage.numberPage,id);
                }
            }
        }
    }
    public class MyTable extends AbstractTableModel {

        String date;
        Calendar calendar;

        @Override
        public int getRowCount() {
            return 25;
        }

        @Override
        public int getColumnCount() {
            return 8;
        }

        @Override
        public Object getValueAt(int r, int c) {
            if (c == 0) {
                if (r < 10) {
                    return ("00:0" + r);
                } else {
                    return ("00:" + r);
                }
            }
            return LoginPage.realId.get(LoginPage.numberPage).get(LoginPage.idCard[r][c]);

        }

        @Override
        public String getColumnName(int c) {
            if (c == 0) {
                return "Time";
            }
            calendar = LoginPage.date.get(c);
            date = LoginPage.sourceDateFormat.format(calendar.getTime());
            return date;
        }
    }
}
