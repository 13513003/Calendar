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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CalendarHandler implements CalendarView {
    String[] years = { "2015", "2016", "2017" };

    JComboBox comboBox = new JComboBox(years);

    String[] months = { "January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December" };

    JList list = new JList(months);

    JScrollPane scrollPane = new JScrollPane(list);

    CalendarModel model = new CalendarModel();

    JTable table = new JTable(model);

    Clock clock = new Clock();

    JLabel date;

    JFrame frame = new JFrame();

    Day[][][] notifDay;

    public void showCalendar() throws IOException {
        frame.setTitle("Calendar");
        frame.getContentPane().setLayout(null);
        java.util.Calendar now = java.util.Calendar.getInstance();
        int year = now.get(java.util.Calendar.YEAR);
        int month = now.get(java.util.Calendar.MONTH);
        int day = now.get(java.util.Calendar.DAY_OF_MONTH);
        notifDay = model.getDay();
        if ((notifDay[year-Integer.parseInt(years[0])][month][day-1].isDayEmpty()) != 1) {
            for (Event e : notifDay[year-Integer.parseInt(years[0])][month][day-1].getEvent()) {
                Notification notif = new Notification(e);
                notif.showNotification();
            }
        }
        //System.out.println(year + " " + month + " " + day);
        comboBox.setBounds(10, 10, 100, 30);
        comboBox.setSelectedItem(year);
        comboBox.addItemListener(new ComboHandler());
        scrollPane.setBounds(330, 10, 150, 100);
        list.setSelectedIndex(month);
        list.addListSelectionListener(new ListHandler());
        JLabel currdate = new JLabel(day+" "+list.getSelectedValue() + " " +year);
        date = new JLabel(list.getSelectedValue() + " " +comboBox.getSelectedItem().toString());
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
        nextButton.addActionListener(new nextHandler());
        beforeButton.addActionListener(new beforeHandler());
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
        beforeButton.setBounds(100, 275, 50, 50);
        nextButton.setBounds(200, 275, 50, 50);
        clock.setBounds(300, 285, 200, 100);
        frame.getContentPane().add(clock);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.rowAtPoint(e.getPoint());
                    int col = table.columnAtPoint(e.getPoint());
                    if (table.rowAtPoint(e.getPoint()) != 0 && Integer.parseInt(table.getValueAt(row, col).toString()) > 0 && Integer.parseInt(table.getValueAt(row, col).toString()) < 32) {
                        //System.out.println("Value in the cell clicked :" + " " + table.getValueAt(row, col).toString());
                        JFrame dayFrame = new JFrame();
                        dayFrame.setTitle("Today's Events");
                        dayFrame.setVisible(true);
                        dayFrame.setContentPane(new eventLister(row, col));
                        dayFrame.setResizable(false);
                        dayFrame.pack();
                    }
                }
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 380);
        frame.setVisible(true);
    }

    public class ComboHandler implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            model.setMonth(Integer.parseInt(comboBox.getSelectedItem().toString()), list.getSelectedIndex());
            date.setText(list.getSelectedValue() + " " + comboBox.getSelectedItem().toString());
            table.repaint();
        }
    }

    public class ListHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            model.setMonth(Integer.parseInt(comboBox.getSelectedItem().toString()), list.getSelectedIndex());
            date.setText(list.getSelectedValue() + " " + comboBox.getSelectedItem().toString());
            table.repaint();
        }
    }
    public class nextHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if (comboBox.getSelectedIndex() != years.length) {
                if(list.getSelectedIndex() == 11) {
                    comboBox.setSelectedIndex(comboBox.getSelectedIndex() + 1);
                    list.setSelectedIndex(0);
                }
                else
                    list.setSelectedIndex(list.getSelectedIndex()+1);
            }
            comboBox.addItemListener(new ComboHandler());
            list.addListSelectionListener(new ListHandler());
        }
    }

    public class beforeHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if (comboBox.getSelectedIndex() != 0) {
                if(list.getSelectedIndex() == 0) {
                    comboBox.setSelectedIndex(comboBox.getSelectedIndex() - 1);
                    list.setSelectedIndex(11);
                }
                else
                    list.setSelectedIndex(list.getSelectedIndex()-1);
            }
            comboBox.addItemListener(new ComboHandler());
            list.addListSelectionListener(new ListHandler());
        }
    }

    public class eventLister extends JPanel implements ActionListener{

        DefaultListModel listModel;
        int row = 0;
        int col = 0;

        public eventLister(int selectedrow,int selectedcol){
            row = selectedrow;
            col = selectedcol;
            listModel = new DefaultListModel();
            setLayout(new BorderLayout());
            JList listevent = new JList(listModel);
            listevent.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            listevent.setLayoutOrientation(JList.VERTICAL);
            setPreferredSize(new Dimension(205, 180));
            JScrollPane listScroller = new JScrollPane(listevent);
            add(listScroller, BorderLayout.NORTH);
            JButton addButton = new JButton("+");
            add(addButton, BorderLayout.CENTER);
            addButton.addActionListener(this);
            listModel.addElement(table.getValueAt(row, col).toString() + " " + list.getSelectedValue() + " " + comboBox.getSelectedItem().toString());
            listModel.addElement("----------------------------------------------------------------");
            for(int i=0; i<model.getDay()[Integer.parseInt(comboBox.getSelectedItem().toString()) - Integer.parseInt(years[0])][list.getSelectedIndex()][Integer.parseInt(table.getValueAt(row, col).toString()) - 1].getEvent().size();i++){
                String event = model.getDay()[Integer.parseInt(comboBox.getSelectedItem().toString()) - Integer.parseInt(years[0])][list.getSelectedIndex()][Integer.parseInt(table.getValueAt(row, col).toString()) - 1].toStringDay(i);
                String[] tokenevent = event.split(",");
                for(String partevent : tokenevent )
                    listModel.addElement(partevent);
                listModel.addElement("----------------------------------------------------------------");
            }
        }
        public void actionPerformed(ActionEvent evt){
            eventAdder a = new eventAdder(row,col);
        }
    }

    public class eventAdder extends JFrame implements ActionListener{
        JPanel p;
        String[] labels = {"Tanggal: ", "Pukul: ", "Nama Kegiatan: ", "Jenis Kegiatan: ", "Tempat: ", "Pesan:"};
        int numPairs = labels.length;
        String[] eventType = {"Party","Meeting"};
        JComboBox combo = new JComboBox(eventType);
        JTextField tanggalField;
        JTextField pukulField;
        JTextField namaField;
        JTextField tempatField;
        JTextField reminderField;
        JTextField subjectField;
        JTextField dresscodeField;
        JTextField participantField;
        JLabel label1;
        JLabel label2;
        JLabel label3;
        JLabel labelpesan;
        int row = 0;
        int col = 0;
        public eventAdder(int selectedrow,int selectedcol){
            row = selectedrow;
            col = selectedcol;
            p = new JPanel(new GridLayout(0, 1));
            combo.setSelectedIndex(0);
            setPreferredSize(new Dimension(215, 500));
            setResizable(false);
            tanggalField = new JTextField(table.getValueAt(row, col).toString() + "-" + list.getSelectedValue() + "-" + comboBox.getSelectedItem().toString());
            pukulField = new JTextField(10);
            namaField = new JTextField(10);
            tempatField = new JTextField(10);
            reminderField = new JTextField(10);
            for (int i = 0; i < numPairs; i++) {
                JLabel label = new JLabel(labels[i]);
                if(i==5)
                    break;
                p.add(label);
                switch(i){
                    case 0: label.setLabelFor(tanggalField);
                        p.add(tanggalField);
                        break;
                    case 1: label.setLabelFor(pukulField);
                        p.add(pukulField);
                        break;
                    case 2: label.setLabelFor(namaField);
                        p.add(namaField);
                        break;
                    case 3: label.setLabelFor(combo);
                        p.add(combo);
                        break;
                    case 4: label.setLabelFor(tempatField);
                        p.add(tempatField);
                        break;
                    /*case 5: label.setLabelFor(reminderField);
                        p.add(reminderField);
                        break;*/
                }
            }
            label1 = new JLabel("Tema");
            label2 = new JLabel("Dress Code");
            subjectField = new JTextField(10);
            dresscodeField = new JTextField(10);
            p.add(label1);
            label1.setLabelFor(subjectField);
            p.add(subjectField);
            p.add(label2);
            label2.setLabelFor(dresscodeField);
            p.add(dresscodeField);
            labelpesan = new JLabel(labels[5]);
            labelpesan.setLabelFor(reminderField);
            p.add(labelpesan);
            p.add(reminderField);
            combo.addActionListener(this);
            p.setOpaque(true);
            setTitle("Add an Event");
            setContentPane(p);
            JButton okButton = new JButton("OK");
            add(okButton, BorderLayout.LINE_END);
            setVisible(true);
            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println(comboBox.getSelectedItem()
                            + "`" + (list.getSelectedIndex() + 1)
                            + "`" + table.getValueAt(row, col)
                            + "`" + combo.getSelectedItem()
                            + "`" + namaField.getText()
                            + "`" + tempatField.getText()
                            + "`" + pukulField.getText()
                            + "`" + reminderField.getText());
                    if (namaField.getText().equals("") || tempatField.getText().equals("") || pukulField.getText().equals("") || reminderField.getText().equals(""))
                        JOptionPane.showMessageDialog(null, "Please fill all necessary forms to add an Event", "Input Error Message", JOptionPane.ERROR_MESSAGE);
                    else {
                        File log = new File("src/input.txt");
                        try {
                            if (log.exists() == false) {
                                System.out.println("We had to make a new file.");
                                log.createNewFile();
                            }
                            PrintWriter out = new PrintWriter(new FileWriter(log, true));
                            out.append(System.lineSeparator());
                            out.append(comboBox.getSelectedItem()
                                    + "`" + (list.getSelectedIndex() + 1)
                                    + "`" + table.getValueAt(row, col)
                                    + "`" + combo.getSelectedItem()
                                    + "`" + namaField.getText()
                                    + "`" + tempatField.getText()
                                    + "`" + pukulField.getText()
                                    + "`" + subjectField.getText()
                                    + "`" + dresscodeField.getText()
                                    + "`" + reminderField.getText());
                            out.close();
                        } catch (IOException err) {
                            System.out.println("COULD NOT LOG!!");
                        }
                        dispose();
                        model.init();
                    }
                }
            });
            pack();

        }
        public void actionPerformed(ActionEvent e) {
            p = new JPanel(new GridLayout(0, 1));
            String selected = (String) combo.getSelectedItem();

            for (int i = 0; i < numPairs; i++) {
                JLabel label = new JLabel(labels[i]);
                if(i==5)
                    break;
                p.add(label);
                switch(i){
                    case 0: label.setLabelFor(tanggalField);
                        p.add(tanggalField);
                        break;
                    case 1: label.setLabelFor(pukulField);
                        p.add(pukulField);
                        break;
                    case 2: label.setLabelFor(namaField);
                        p.add(namaField);
                        break;
                    case 3: label.setLabelFor(combo);
                        p.add(combo);
                        break;
                    case 4: label.setLabelFor(tempatField);
                        p.add(tempatField);
                        break;
                    /*case 5: label.setLabelFor(reminderField);
                        p.add(reminderField);
                        break;*/
                }
            }
            switch(selected){
                case "Party":
                    label1 = new JLabel("Tema");
                    label2 = new JLabel("Dress Code");
                    subjectField = new JTextField(10);
                    dresscodeField = new JTextField(10);
                    p.add(label1);
                    label1.setLabelFor(subjectField);
                    p.add(subjectField);

                    p.add(label2);
                    label2.setLabelFor(dresscodeField);
                    p.add(dresscodeField);
                    System.out.println(combo.getSelectedItem().toString());
                    break;
                case "Meeting":
                    label1 = new JLabel("Topik");
                    label3 = new JLabel("Partisipan");
                    subjectField = new JTextField(10);
                    participantField = new JTextField(10);
                    p.add(label1);
                    label1.setLabelFor(subjectField);
                    p.add(subjectField);

                    p.add(label3);
                    label3.setLabelFor(participantField);
                    p.add(participantField);
                    System.out.println(combo.getSelectedItem().toString());
                    break;
            }
            labelpesan = new JLabel(labels[5]);
            labelpesan.setLabelFor(reminderField);
            p.add(labelpesan);
            p.add(reminderField);
            combo.addActionListener(this);
            p.setOpaque(true);
            setTitle("Add an Event");
            setContentPane(p);
            JButton okButton = new JButton("OK");
            add(okButton,BorderLayout.LINE_END);
            setVisible(true);
            //pack();
            //p.revalidate();
            //p.validate();
            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println(comboBox.getSelectedItem()
                            + "`" + (list.getSelectedIndex() + 1)
                            + "`" + table.getValueAt(row, col)
                            + "`" + combo.getSelectedItem()
                            + "`" + namaField.getText()
                            + "`" + tempatField.getText()
                            + "`" + pukulField.getText()
                            + "`" + reminderField.getText());
                    if (namaField.getText().equals("") || tempatField.getText().equals("") || pukulField.getText().equals("") || reminderField.getText().equals(""))
                        JOptionPane.showMessageDialog(null, "Please fill all necessary forms to add an Event", "Input Error Message", JOptionPane.ERROR_MESSAGE);
                    else {
                        File log = new File("src/input.txt");
                        try {
                            if (log.exists() == false) {
                                System.out.println("We had to make a new file.");
                                log.createNewFile();
                            }
                            PrintWriter out = new PrintWriter(new FileWriter(log, true));
                            out.append(System.lineSeparator());
                            if (selected.equals("Party"))
                                out.append(comboBox.getSelectedItem()
                                        + "`" + (list.getSelectedIndex() + 1)
                                        + "`" + table.getValueAt(row, col)
                                        + "`" + combo.getSelectedItem()
                                        + "`" + namaField.getText()
                                        + "`" + tempatField.getText()
                                        + "`" + pukulField.getText()
                                        + "`" + subjectField.getText()
                                        + "`" + dresscodeField.getText()
                                        + "`" + reminderField.getText());
                            else if (selected.equals("Meeting"))
                                out.append(comboBox.getSelectedItem()
                                        + "`" + (list.getSelectedIndex() + 1)
                                        + "`" + table.getValueAt(row, col)
                                        + "`" + combo.getSelectedItem()
                                        + "`" + namaField.getText()
                                        + "`" + tempatField.getText()
                                        + "`" + pukulField.getText()
                                        + "`" + subjectField.getText()
                                        + "`" + participantField.getText()
                                        + "`" + reminderField.getText());
                            out.close();
                        } catch (IOException err) {
                            System.out.println("COULD NOT LOG!!");
                        }
                        dispose();
                        model.init();
                    }
                }
            });
            repaint();
        }
    }
}

