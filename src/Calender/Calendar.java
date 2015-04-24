package Calender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

public class Calendar extends JFrame {
    String[] years = { "2015", "2016", "2017" };

    JComboBox comboBox = new JComboBox(years);

    String[] months = { "January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December" };

    JList list = new JList(months);

    JScrollPane scrollPane = new JScrollPane(list);

    CalendarModel model = new CalendarModel();

    JTable table = new JTable(model);

    public Calendar() {
        super();

        getContentPane().setLayout(null);
        comboBox.setBounds(10, 10, 100, 30);
        comboBox.setSelectedIndex(0);
        comboBox.addItemListener(new ComboHandler());
        scrollPane.setBounds(200, 10, 150, 100);
        list.setSelectedIndex(3);
        list.addListSelectionListener(new ListHandler());
        table.setBounds(10, 150, 550, 200);
        model.setMonth(Integer.parseInt(comboBox.getSelectedItem().toString()), list.getSelectedIndex());
        getContentPane().add(comboBox);
        getContentPane().add(scrollPane);
        table.setGridColor(Color.black);
        table.setShowGrid(true);
        getContentPane().add(table);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(500, 500);
        setVisible(true);
    }

    public static void main(String[] args) {
        Calendar app = new Calendar();
    }
    public class ComboHandler implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            model.setMonth(Integer.parseInt(comboBox.getSelectedItem().toString()), list.getSelectedIndex());
            //System.out.println(comboBox.getSelectedItem());
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
class CalendarModel extends AbstractTableModel {
    private Day[][][] day;
    private String[] days = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
    private int[] numDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    private String[] months = { "January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December" };
    private String[] years = { "2015", "2016", "2017" };
    private String[][] calendar;

    public CalendarModel() {
        day = new Day[years.length][months.length][32];
        calendar = new String[7][7];
        for (int i = 0; i < days.length; i++)
            calendar[0][i] = days[i];
        for (int i = 0; i < years.length; i++) {
            for (int j = 0; j < months.length; j++) {
                for (int k = 0; k < numDays[j]; ++k)
                    day[i][j][k] = new Day();
            }
        }
        init();
    }

    public void init(){
        String line;
        String delims = "[-]+";
        String[] tokens;
        Day tempday = new Day();
        try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\user\\IdeaProjects\\Calendar\\input.txt"))) {
            line = br.readLine();
            if(line != null){
                //tokens = line.split(delims);
                do {
                    tokens = line.split(delims);
                    int tokenyear = 0;
                    int tokenmonth = 0 ;
                    int tokenday = 0;
                    int tokendaybefore = 0;
                    for (int j = 0; j < tokens.length; j++) {
                        switch(j){
                            case 0 :tokenyear = Integer.parseInt(tokens[j])-Integer.parseInt(years[0]);
                                    break;
                            case 1 :tokenmonth = Integer.parseInt(tokens[j])-1;
                                    break;
                            case 2 :tokenday = Integer.parseInt(tokens[j])-1;
                                    break;
                        }
                        if(j == 3){
                            if(tokens[j].equals("Party")) {
                                Event tokenevent = new Party(tokens[j + 1], tokens[j + 2], tokens[j + 3], tokens[j + 4], tokens[j + 5]);
                                if(tokendaybefore != tokenday)
                                    tempday = new Day();
                                tempday.addEvent(tokenevent);
                                day[tokenyear][tokenmonth][tokenday] =  tempday;
                            }
                            else if(tokens[j].equals("Meeting")){
                                //donothing
                            }
                            tokendaybefore = tokenday;
                            break;
                        }
                    }
                }while ((line = br.readLine()) != null);
            }
        }
        catch (IOException e){
            //System.out.println("File I/O error!");
        }
        for (int i = 0; i < years.length; i++) {
            for (int j = 0; j < months.length; j++) {
                for (int k = 0; k < numDays[j]; ++k) {
                    day[i][j][k].printDay();
                }
            }
        }
    }

    public int getRowCount() {
        return 7;
    }

    public int getColumnCount() {
        return 7;
    }

    public Object getValueAt(int row, int column) {
        return calendar[row][column];
    }

    public void setValueAt(Object value, int row, int column) {
        calendar[row][column] = (String) value;
    }

    public void setMonth(int year, int month) {
        for (int i = 1; i < 7; ++i)
            for (int j = 0; j < 7; ++j)
                calendar[i][j] = " ";
        java.util.GregorianCalendar cal = new java.util.GregorianCalendar();
        cal.set(year, month, 1);
        int offset = cal.get(java.util.GregorianCalendar.DAY_OF_WEEK) - 1;
        offset += 7;
        int num = daysInMonth(year, month);
        //day[month] = new ArrayList<Day>();
        for (int i = 0; i < num; ++i) {
            calendar[offset / 7][offset % 7] = Integer.toString(i + 1);
            ++offset;
        }
    }

    public boolean isLeapYear(int year) {
        if (year % 4 == 0)
            return true;
        return false;
    }

    public int daysInMonth(int year, int month) {
        int days = numDays[month];
        if (month == 1 && isLeapYear(year))
            ++days;
        return days;
    }
}
/*public class Calendar{
    private ArrayList<Day> days;
    public Calendar(){
        days = new ArrayList<Day>();
    }
    public Calendar(ArrayList<Day> newday){
        days = new ArrayList<Day>(newday);
    }

    public void addDay(Day D){
        days.add(D);
    }

    public Day getDay(int i){
        return days.get(i);
    }
    String[] days = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };

    int[] numDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    String[][] calendar = new String[7][7];
    public void setMonth(int year, int month) {
        for (int i = 1; i < 7; ++i)
            for (int j = 0; j < 7; ++j)
                calendar[i][j] = "   ";
        java.util.GregorianCalendar cal = new java.util.GregorianCalendar();
        cal.set(year, month, 1);
        int offset = cal.get(java.util.GregorianCalendar.DAY_OF_WEEK) - 1;
        //offset += 7;
        int num = daysInMonth(year, month);
        for (int i = 0; i < num; ++i) {
            calendar[offset / 7][offset % 7] = Integer.toString(i + 1);
            ++offset;
        }
    }
    public int daysInMonth(int year, int month) {
        int days = numDays[month];
        if (month == 1 && isLeapYear(year))
            ++days;
        return days;
    }
    public boolean isLeapYear(int year) {
        if (year % 4 == 0)
            return true;
        return false;
    }
    public static void main(String[] args){

        Calendar c = new Calendar();

        c.setMonth(2015, 3);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTable table = new JTable(c.calendar, c.days);

        table.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        MouseListener mouseListener = new MouseAdapter() {
            public void mouseisClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    JOptionPane.showMessageDialog(null, "testing");
                }
            }
        };
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String selectedData = null;

                int[] selectedRow = table.getSelectedRows();
                int[] selectedColumns = table.getSelectedColumns();

                for (int i = 0; i < selectedRow.length; i++) {
                    for (int j = 0; j < selectedColumns.length; j++) {
                        selectedData = (String) table.getValueAt(selectedRow[i], selectedColumns[j]);
                    }
                }
                System.out.println("Selected: " + selectedData);
            }

        });
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(300, 150);
        frame.setVisible(true);
    }
}*/


/*
        import java.awt.Color;
        import java.awt.Container;
        import java.awt.Dimension;
        import java.awt.Font;
        import java.awt.Graphics;
        import java.awt.Graphics2D;
        import java.awt.Image;
        import java.awt.Point;
        import java.awt.RenderingHints;
        import java.awt.event.MouseEvent;
        import java.awt.event.MouseListener;
        import java.awt.event.MouseMotionListener;
        import java.text.SimpleDateFormat;
        import java.util.Calendar;
        import java.util.Date;

        import javax.swing.ImageIcon;
        import javax.swing.JComponent;
        import javax.swing.JFrame;
        import javax.swing.JPanel;

class CalendarHack extends JPanel {
    protected Image background = new ImageIcon("calendar.png").getImage();
    protected Image highlight = new ImageIcon("highlight.png").getImage();
    protected Image day_img = new ImageIcon("day.png").getImage();

    protected SimpleDateFormat month = new SimpleDateFormat("MMMM");

    protected SimpleDateFormat year = new SimpleDateFormat("yyyy");

    protected SimpleDateFormat day = new SimpleDateFormat("d");

    protected Date date = new Date();

    public void setDate(Date date) {
        this.date = date;
    }

    public CalendarHack() {
        this.setPreferredSize(new Dimension(300, 280));
    }

    public void paintComponent(Graphics g) {
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(background, 0, 0, null);
        g.setColor(Color.black);
        g.setFont(new Font("SansSerif", Font.PLAIN, 18));
        g.drawString(month.format(date), 34, 36);
        g.setColor(Color.black);
        g.drawString(year.format(date), 235, 36);

        Calendar today = Calendar.getInstance();
        today.setTime(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.DATE, -cal.get(Calendar.DAY_OF_WEEK) + 1);
        for (int week = 0; week < 6; week++) {
            for (int d = 0; d < 7; d++) {
                Image img = day_img;
                Color col = Color.black;
                // only draw if it's actually in this month
                if (cal.get(Calendar.MONTH) == today.get(Calendar.MONTH)) {
                    if (cal.equals(today)) {
                        img = highlight;
                        col = Color.white;
                    }
                    g.drawImage(img, d * 30 + 46, week * 29 + 81, null);
                    g.drawString(day.format(cal.getTime()), d * 30 + 46 + 4, week * 29 + 81 + 20);
                }
                cal.add(Calendar.DATE, +1);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        CalendarHack ch = new CalendarHack();
        ch.setDate(new Date());
        frame.getContentPane().add(ch);
        frame.setUndecorated(true);

        MoveMouseListener mml = new MoveMouseListener(ch);
        ch.addMouseListener(mml);
        ch.addMouseMotionListener(mml);

        frame.pack();
        frame.setVisible(true);
    }
}

class MoveMouseListener implements MouseListener, MouseMotionListener {
    JComponent target;

    Point start_drag;

    Point start_loc;

    public MoveMouseListener(JComponent target) {
        this.target = target;
    }

    public static JFrame getFrame(Container target) {
        if (target instanceof JFrame) {
            return (JFrame) target;
        }
        return getFrame(target.getParent());
    }

    Point getScreenLocation(MouseEvent e) {
        Point cursor = e.getPoint();
        Point target_location = this.target.getLocationOnScreen();
        return new Point((int) (target_location.getX() + cursor.getX()),
                (int) (target_location.getY() + cursor.getY()));
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        this.start_drag = this.getScreenLocation(e);
        this.start_loc = this.getFrame(this.target).getLocation();
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        Point current = this.getScreenLocation(e);
        Point offset = new Point((int) current.getX() - (int) start_drag.getX(), (int) current.getY()
                - (int) start_drag.getY());
        JFrame frame = this.getFrame(target);
        Point new_location = new Point((int) (this.start_loc.getX() + offset.getX()),
                (int) (this.start_loc.getY() + offset.getY()));
        frame.setLocation(new_location);
    }

    public void mouseMoved(MouseEvent e) {
    }
}*/