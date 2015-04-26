package Calender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.Collections;
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

    Clock clock = new Clock();

    JLabel date;

    public Calendar() {
        super();
        setTitle("Calendar");
        getContentPane().setLayout(null);
        java.util.Calendar now = java.util.Calendar.getInstance();
        int year = now.get(java.util.Calendar.YEAR);
        int month = now.get(java.util.Calendar.MONTH);
        int day = now.get(java.util.Calendar.DAY_OF_MONTH);
        System.out.println(year + " " + month + " " + day);
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
        model.setMonth(Integer.parseInt(comboBox.getSelectedItem().toString()), list.getSelectedIndex());
        getContentPane().add(comboBox);
        getContentPane().add(scrollPane);
        getContentPane().add(date);
        table.setGridColor(Color.black);
        table.setShowGrid(true);
        table.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        getContentPane().add(table);
        currdate.setBounds(325,250,100,50);
        getContentPane().add(currdate);
        clock.setBounds(300, 285, 200, 100);
        getContentPane().add(clock);
        table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int row=table.rowAtPoint(e.getPoint());
                int col= table.columnAtPoint(e.getPoint());
                JFrame dayFrame = new JFrame();
                dayFrame.setTitle("Today's Events");
                dayFrame.setVisible(true);
                dayFrame.setContentPane(new eventLister(row, col));
                dayFrame.pack();
                System.out.println("Value in the cell clicked :" + " " + table.getValueAt(row, col).toString());

            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(500, 380);
        setVisible(true);
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

            JScrollPane listScroller = new JScrollPane(listevent);
            add(listScroller, BorderLayout.NORTH);
            JButton addButton = new JButton("+");
            add(addButton, BorderLayout.CENTER);
            addButton.addActionListener(this);
            listModel.addElement(table.getValueAt(row, col).toString() + " " + list.getSelectedValue() + " " + comboBox.getSelectedItem().toString());
            listModel.addElement("----------------------------------");
            for(int i=0; i<model.getDay()[Integer.parseInt(comboBox.getSelectedItem().toString()) - Integer.parseInt(years[0])][list.getSelectedIndex()][Integer.parseInt(table.getValueAt(row, col).toString()) - 1].getEvent().size();i++){
                String event = model.getDay()[Integer.parseInt(comboBox.getSelectedItem().toString()) - Integer.parseInt(years[0])][list.getSelectedIndex()][Integer.parseInt(table.getValueAt(row, col).toString()) - 1].toStringDay(i);
                String[] tokenevent = event.split(",");
                for(String partevent : tokenevent )
                    listModel.addElement(partevent);
                listModel.addElement("----------------------------------");
            }
        }
        public void actionPerformed(ActionEvent evt){
            eventAdder a = new eventAdder(row,col);
        }
    }

    public class eventAdder extends JFrame implements ActionListener{
        JPanel p;
        String[] labels = {"Tanggal: ", "Pukul: ", "Nama Kegiatan: ", "Jenis Kegiatan: ", "Tempat: ", "Reminder:"};
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
        int row = 0;
        int col = 0;
        public eventAdder(int selectedrow,int selectedcol){
            row = selectedrow;
            col = selectedcol;
            p = new JPanel(new GridLayout(0, 1));
            combo.setSelectedIndex(0);

            tanggalField = new JTextField(table.getValueAt(row, col).toString() + "-" + list.getSelectedValue() + "-" + comboBox.getSelectedItem().toString());
            pukulField = new JTextField(10);
            namaField = new JTextField(10);
            tempatField = new JTextField(10);
            reminderField = new JTextField(10);

            for (int i = 0; i < numPairs; i++) {
                JLabel label = new JLabel(labels[i]);
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
                    case 5: label.setLabelFor(reminderField);
                        p.add(reminderField);
                        break;
                }
            }
            label1 = new JLabel("Subjek");
            label2 = new JLabel("Dress Code");
            subjectField = new JTextField(10);
            dresscodeField = new JTextField(10);
            p.add(label1);
            label1.setLabelFor(subjectField);
            p.add(subjectField);
            p.add(label2);
            label2.setLabelFor(dresscodeField);
            p.add(dresscodeField);
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
                            + "`" + (list.getSelectedIndex()+1)
                            + "`" + table.getValueAt(row, col)
                            + "`" + combo.getSelectedItem()
                            + "`" + namaField.getText()
                            + "`" + tempatField.getText()
                            + "`" + pukulField.getText()
                            + "`" + reminderField.getText());
                    File log = new File("src/input.txt");
                    try {
                        if (log.exists() == false) {
                            System.out.println("We had to make a new file.");
                            log.createNewFile();
                        }
                        PrintWriter out = new PrintWriter(new FileWriter(log, true));
                        out.append(System.lineSeparator());
                        out.append(comboBox.getSelectedItem()
                                    + "`" + (list.getSelectedIndex()+1)
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
                }
            });
            pack();

        }
        public void actionPerformed(ActionEvent e) {
            p = new JPanel(new GridLayout(0, 1));
            String selected = (String) combo.getSelectedItem();

            for (int i = 0; i < numPairs; i++) {
                JLabel label = new JLabel(labels[i]);
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
                    case 5: label.setLabelFor(reminderField);
                        p.add(reminderField);
                        break;
                }
            }
            switch(selected){
                case "Party":
                    label1 = new JLabel("Subjek");
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
                    label1 = new JLabel("Subjek");
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
                            + "`" + (list.getSelectedIndex()+1)
                            + "`" + table.getValueAt(row, col)
                            + "`" + combo.getSelectedItem()
                            + "`" + namaField.getText()
                            + "`" + tempatField.getText()
                            + "`" + pukulField.getText()
                            + "`" + reminderField.getText());
                    File log = new File("src/input.txt");
                    try{
                        if(log.exists()==false){
                            System.out.println("We had to make a new file.");
                            log.createNewFile();
                        }
                        PrintWriter out = new PrintWriter(new FileWriter(log, true));
                        out.append(System.lineSeparator());
                        if(selected.equals("Party"))
                            out.append(comboBox.getSelectedItem()
                                + "`" + (list.getSelectedIndex()+1)
                                + "`" + table.getValueAt(row, col)
                                + "`" + combo.getSelectedItem()
                                + "`" + namaField.getText()
                                + "`" + tempatField.getText()
                                + "`" + pukulField.getText()
                                + "`" + subjectField.getText()
                                + "`" + dresscodeField.getText()
                                + "`" + reminderField.getText());
                        else if(selected.equals("Meeting"))
                            out.append(comboBox.getSelectedItem()
                                    + "`" + (list.getSelectedIndex()+1)
                                    + "`" + table.getValueAt(row, col)
                                    + "`" + combo.getSelectedItem()
                                    + "`" + namaField.getText()
                                    + "`" + tempatField.getText()
                                    + "`" + pukulField.getText()
                                    + "`" + subjectField.getText()
                                    + "`" + participantField.getText()
                                    + "`" + reminderField.getText());
                        out.close();
                    }catch(IOException err){
                        System.out.println("COULD NOT LOG!!");
                    }
                    dispose();
                }
            });
            repaint();
        }
    }

    public static void main(String[] args) {
        Calendar app = new Calendar();
    }
    public class ComboHandler implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            model.setMonth(Integer.parseInt(comboBox.getSelectedItem().toString()), list.getSelectedIndex());
            date.setText(list.getSelectedValue() + " " +comboBox.getSelectedItem().toString());
            table.repaint();
        }
    }

    public class ListHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            model.setMonth(Integer.parseInt(comboBox.getSelectedItem().toString()), list.getSelectedIndex());
            date.setText(list.getSelectedValue() + " " +comboBox.getSelectedItem().toString());
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
        TextFileSorter sorter = new TextFileSorter();
        try{
            sorter.sort();
        }
        catch(Exception e){

        }
        init();
    }

    public Day[][][] getDay (){
        return day;
    }

    public void init(){
        String line;
        String delims = "[`]+";
        String[] tokens;
        Day tempday = new Day();
        int tokendaybefore = 0;
        int tokenmonthbefore = 0;
        int tokenyearbefore = 0;
        try(BufferedReader br = new BufferedReader(new FileReader("src/input.txt"))) {
            line = br.readLine();
            if(line != null){
                do {
                    tokens = line.split(delims);
                    int tokenyear = 0;
                    int tokenmonth = 0 ;
                    int tokenday = 0;

                    for (int j = 0; j < tokens.length; j++) {
                        switch(j){
                            case 0 :tokenyear = Integer.parseInt(tokens[j])-Integer.parseInt(years[0]);
                                    break;
                            case 1 :tokenmonth = Integer.parseInt(tokens[j])-1;
                                    break;
                            case 2 :tokenday = Integer.parseInt(tokens[j])-1;
                                    break;
                            case 3:if(tokens[j].equals("Party")) {
                                        Event tokenevent = new Party(tokens[j + 1], tokens[j + 2], tokens[j + 3], tokens[j + 4], tokens[j + 5]);
                                        if(tokendaybefore != tokenday || tokenmonthbefore != tokenmonth || tokenyearbefore != tokenyear){
                                            tempday = new Day();
                                        }
                                        tempday.addEvent(tokenevent);
                                        day[tokenyear][tokenmonth][tokenday] =  tempday;
                                    }
                                    else if(tokens[j].equals("Meeting")){
                                        String[] tokenparticipant = tokens[j + 5].split(",");
                                        ArrayList<String> participant = new ArrayList<String>();
                                        Collections.addAll(participant, tokenparticipant);
                                        Event tokenevent = new Meeting(tokens[j + 1], tokens[j + 2], tokens[j + 3], tokens[j + 4], participant);
                                        if(tokendaybefore != tokenday || tokenmonthbefore != tokenmonth || tokenyearbefore != tokenyear){
                                            tempday = new Day();
                                        }
                                        tempday.addEvent(tokenevent);
                                        day[tokenyear][tokenmonth][tokenday] =  tempday;
                                    }
                                    tokendaybefore = tokenday;
                                    tokenmonthbefore = tokenmonth;
                                    tokenyearbefore = tokenyear;
                                    break;
                        }
                    }
                }while ((line = br.readLine()) != null);
            }
        }
        catch (IOException e){
            //System.out.println("File I/O error!");
        }
        //for (int i = 0; i < years.length; i++) {
            //for (int j = 0; j < months.length; j++) {
                //for (int k = 0; k < numDays[j]; ++k) {
                    day[0][0][0].printDay();
                //}
            //}
        //}
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
class TextFileSorter {

    public void sort() throws Exception {

        String inputFile = "src/input.txt";
        String outputFile = "src/input.txt";

        FileReader fileReader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String inputLine;
        java.util.List<String> lineList = new ArrayList<String>();
        while ((inputLine = bufferedReader.readLine()) != null) {
            if(!inputLine.equals(""))
                lineList.add(inputLine);
        }
        fileReader.close();

        Collections.sort(lineList);

        FileWriter fileWriter = new FileWriter(outputFile);
        PrintWriter out = new PrintWriter(fileWriter);
        for (String outputLine : lineList) {
            out.println(outputLine);
        }
        out.flush();
        out.close();
        fileWriter.close();

    }
}
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