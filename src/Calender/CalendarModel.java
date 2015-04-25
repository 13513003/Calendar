package Calender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.table.AbstractTableModel;

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
