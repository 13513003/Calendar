package Calender;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Show implements CalendarView {
    String[] years = { "2015", "2016", "2017" };

    JComboBox comboBox = new JComboBox(years);

    String[] months = { "January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December" };
        
    JList list = new JList(months);

    JScrollPane scrollPane = new JScrollPane(list);

    CalendarModel model = new CalendarModel();

    JTable table = new JTable(model);

    ClockPanel clockPanel = new ClockPanel();

    JFrame frame = new JFrame();
    
    public void showCalendar() {        
        frame.getContentPane().setLayout(null);
        comboBox.setBounds(10, 10, 100, 30);
        comboBox.setSelectedIndex(0);
        comboBox.addItemListener(new Show.ComboHandler());
        scrollPane.setBounds(200, 10, 150, 100);
        list.setSelectedIndex(3);
        list.addListSelectionListener(new Show.ListHandler());
        table.setBounds(10, 150, 550, 200);
        model.setMonth(Integer.parseInt(comboBox.getSelectedItem().toString()), list.getSelectedIndex());
        frame.getContentPane().add(comboBox);
        frame.getContentPane().add(scrollPane);
        table.setGridColor(Color.black);
        table.setShowGrid(true);
        frame.getContentPane().add(table);
        clockPanel.setBounds(10, 350, 200, 200);
        frame.getContentPane().add(clockPanel);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(500, 500);
        frame.setVisible(true);
    }
    
    public class ComboHandler implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            model.setMonth(Integer.parseInt(comboBox.getSelectedItem().toString()), list.getSelectedIndex());
            table.repaint();
        }
    }

    public class ListHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            model.setMonth(Integer.parseInt(comboBox.getSelectedItem().toString()), list.getSelectedIndex());
            table.repaint();
        }
    }
}
