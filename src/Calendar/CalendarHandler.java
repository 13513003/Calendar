/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CalendarHandler implements CalendarView {

    private String[] years = {"2015", "2016", "2017"};
    private JComboBox comboBox = new JComboBox(years);
    private String[] months = {"January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December"};
    private JList list = new JList(months);
    private JScrollPane scrollPane = new JScrollPane(list);
    private static CalendarModel model;
    private JTable table;
    private Clock clock = new Clock();
    private JLabel date;
    private JFrame frame = new JFrame();

    public void showCalendar() throws IOException {
        model = new CalendarModel();
        table = new JTable(model);
        frame.setTitle("Calendar");
        frame.getContentPane().setLayout(null);
        java.util.Calendar now = java.util.Calendar.getInstance();
        int year = now.get(java.util.Calendar.YEAR);
        int month = now.get(java.util.Calendar.MONTH);
        int day = now.get(java.util.Calendar.DAY_OF_MONTH);
        Day[][][] notifDay = model.getDay();
        if ((notifDay[year - Integer.parseInt(years[0])][month][day - 1].isDayEmpty()) != 1) {
            for (Event e : notifDay[year - Integer.parseInt(years[0])][month][day - 1].getEvent()) {
                Notification notif = new Notification(e);
                notif.showNotification();
            }
        }
        comboBox.setBounds(10, 10, 100, 30);
        comboBox.setSelectedItem(year);
        comboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxItemStateChanged(evt);
            }
        });
        scrollPane.setBounds(330, 10, 150, 100);
        list.setSelectedIndex(month);
        list.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listValueChanged(evt);
            }
        });
        JLabel currdate = new JLabel(day + " " + list.getSelectedValue() + " " + year);
        date = new JLabel(list.getSelectedValue() + " " + comboBox.getSelectedItem().toString());
        date.setBounds(10, 115, 100, 50);
        table.setBounds(10, 150, 470, 115);
        BufferedImage nextIcon = ImageIO.read(new File("UI/next.png"));
        BufferedImage beforeIcon = ImageIO.read(new File("UI/before.png"));
        JButton nextButton = new JButton(new ImageIcon(nextIcon));
        JButton beforeButton = new JButton(new ImageIcon(beforeIcon));
        nextButton.setBorderPainted(false);
        nextButton.setContentAreaFilled(false);
        beforeButton.setBorderPainted(false);
        beforeButton.setContentAreaFilled(false);
        frame.getContentPane().add(nextButton);
        frame.getContentPane().add(beforeButton);
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });
        beforeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beforeButtonActionPerformed(evt);
            }
        });
        model.setMonth(Integer.parseInt(comboBox.getSelectedItem().toString()), list.getSelectedIndex());
        frame.getContentPane().add(comboBox);
        frame.getContentPane().add(scrollPane);
        frame.getContentPane().add(date);
        table.setGridColor(Color.black);
        table.setShowGrid(true);
        table.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        frame.getContentPane().add(table);
        currdate.setBounds(325, 250, 100, 50);
        frame.getContentPane().add(currdate);
        beforeButton.setBounds(10, 275, 50, 50);
        nextButton.setBounds(120, 275, 50, 50);
        clock.setBounds(300, 285, 200, 100);
        frame.getContentPane().add(clock);
        frame.setLocation(400, 200);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.rowAtPoint(e.getPoint());
                    int col = table.columnAtPoint(e.getPoint());
                    if (table.rowAtPoint(e.getPoint()) != 0 && !table.getValueAt(row, col).toString().equals(" ")) {
                        eventLister el = new eventLister(row, col, model, table.getValueAt(row, col).toString(), list.getSelectedValue().toString(), list.getSelectedIndex(), comboBox.getSelectedItem().toString(), years);
                    }
                }
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 380);
        frame.setVisible(true);
    }

    private void listValueChanged(javax.swing.event.ListSelectionEvent evt) {
        model.setMonth(Integer.parseInt(comboBox.getSelectedItem().toString()), list.getSelectedIndex());
        date.setText(list.getSelectedValue() + " " + comboBox.getSelectedItem().toString());
        table.repaint();
    }

    private void comboBoxItemStateChanged(java.awt.event.ItemEvent e) {
        model.setMonth(Integer.parseInt(comboBox.getSelectedItem().toString()), list.getSelectedIndex());
        date.setText(list.getSelectedValue() + " " + comboBox.getSelectedItem().toString());
        table.repaint();
    }

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (list.getSelectedIndex() == 11 && comboBox.getSelectedIndex() != 2) {
            comboBox.setSelectedIndex(comboBox.getSelectedIndex() + 1);
            list.setSelectedIndex(0);
        } else
            list.setSelectedIndex(list.getSelectedIndex() + 1);
        comboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxItemStateChanged(evt);
            }
        });
        list.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listValueChanged(evt);
            }
        });
    }

    private void beforeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (list.getSelectedIndex() == 0 && comboBox.getSelectedIndex() != 0) {
            comboBox.setSelectedIndex(comboBox.getSelectedIndex() - 1);
            list.setSelectedIndex(11);
        } else
            list.setSelectedIndex(list.getSelectedIndex() - 1);
        comboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxItemStateChanged(evt);
            }
        });
        list.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listValueChanged(evt);
            }
        });
    }
}


