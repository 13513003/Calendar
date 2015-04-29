package Calendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by user on 4/29/2015.
 */
public class eventAdder extends JFrame implements ActionListener {

    private JPanel p;
    private String[] labels = {"Tanggal: ", "Pukul: ", "Nama Kegiatan: ", "Jenis Kegiatan: ", "Tempat: ", "Pesan:"};
    private int numPairs = labels.length;
    private String[] eventType = {"Party","Meeting"};
    private JComboBox combo = new JComboBox(eventType);
    private JTextField tanggalField;
    private JTextField pukulField;
    private JTextField namaField;
    private JTextField tempatField;
    private JTextField reminderField;
    private JTextField subjectField;
    private JTextField dresscodeField;
    private JTextField participantField;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel labelpesan;
    private int row = 0;
    private int col = 0;
    private int listindex;
    private String tablevalue = new String();
    private String combovalue = new String();
    private CalendarModel model;

    public eventAdder(int selectedrow,int selectedcol, CalendarModel inputmodel, String inputtablevalue, String listvalue, int inputlistindex, String inputcombovalue ){
        row = selectedrow;
        col = selectedcol;
        tablevalue = inputtablevalue;
        combovalue = inputcombovalue;
        listindex = inputlistindex;
        model = inputmodel;
        p = new JPanel(new GridLayout(0, 1));
        combo.setSelectedIndex(0);
        setLocation(700,200);
        setPreferredSize(new Dimension(215, 500));
        setResizable(false);
        tanggalField = new JTextField(tablevalue + "-" + listvalue + "-" + combovalue);
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
                        out.append(combovalue
                                + "`" + (listindex + 1)
                                + "`" + tablevalue
                                + "`" + combo.getSelectedItem()
                                + "`" + pukulField.getText()
                                + "`" + namaField.getText()
                                + "`" + tempatField.getText()
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
        final String selected = (String) combo.getSelectedItem();

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
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(combovalue
                        + "`" + (listindex + 1)
                        + "`" + tablevalue
                        + "`" + combo.getSelectedItem()
                        + "`" + pukulField.getText()
                        + "`" + namaField.getText()
                        + "`" + tempatField.getText()
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
                            out.append(combovalue
                                    + "`" + (listindex + 1)
                                    + "`" + tablevalue
                                    + "`" + combo.getSelectedItem()
                                    + "`" + pukulField.getText()
                                    + "`" + namaField.getText()
                                    + "`" + tempatField.getText()
                                    + "`" + subjectField.getText()
                                    + "`" + dresscodeField.getText()
                                    + "`" + reminderField.getText());
                        else if (selected.equals("Meeting"))
                            out.append(combovalue
                                    + "`" + (listindex + 1)
                                    + "`" + tablevalue
                                    + "`" + combo.getSelectedItem()
                                    + "`" + pukulField.getText()
                                    + "`" + namaField.getText()
                                    + "`" + tempatField.getText()
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
