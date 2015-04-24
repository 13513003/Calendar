package Calender;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JPanel;

public class ClockPanel extends JPanel implements Runnable {
    Thread runner;

    public ClockPanel() {
        if (runner == null) {
            runner = new Thread(this);
            runner.start();
        }
    }

    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { 
                Thread.currentThread().interrupt();
            }
        }
    }

    public void paintComponent(Graphics comp) {
        Graphics2D comp2D = (Graphics2D)comp;
        Font type = new Font("Calibri", Font.BOLD, 40);
        comp2D.setFont(type);
        comp2D.setColor(getBackground());
        comp2D.fillRect(0, 0, getSize().width, getSize().height);
        GregorianCalendar day = new GregorianCalendar();
        String time = day.getTime().toString();
        comp2D.setColor(Color.black);
        comp2D.drawString(currentTime(), 20, 40);
    }
    
    public String currentTime() {
        GregorianCalendar now = new GregorianCalendar();
        int hrs = now.get(Calendar.HOUR_OF_DAY);
        int min = now.get(Calendar.MINUTE);
        int sec = now.get(Calendar.SECOND);
        
        String hours, minutes, seconds;
        
        if (hrs < 10) {
            hours = "0"+hrs;
        } else {
            hours = ""+hrs;
        }
                
        if (min < 10) {
            minutes = "0"+min;
        } else {
            minutes = ""+min;
        }
       
        if (sec < 10) {
            seconds = "0"+sec;
        } else {
            seconds = ""+sec;
        }
               
        String time = hours+":"+minutes+":"+seconds;
        return time;
    }
}