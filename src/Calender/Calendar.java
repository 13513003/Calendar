package Calender;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 *
 * @author Lenovo
 */
public class Calendar{
    public Calendar() {
        File file = new File("./theme");
        try {
            URL[] cp = {file.toURI().toURL()};
            URLClassLoader urlcl = new URLClassLoader(cp);
            Class AClass = urlcl.loadClass("calendar.Show");
            Class interfaces[] = AClass.getInterfaces();
            for (Class intf : interfaces) {
                if (intf.getName().equals("calendar.CalendarView")) {
                    Show calendarShow = (Show)AClass.newInstance();
                    calendarShow.showCalendar(); 
                }
            }
        } 
        catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(null, "Invalid URL", "Error", ERROR_MESSAGE);
        }
        catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Class Not Found", "Error", ERROR_MESSAGE);
        }
        catch (InstantiationException ex) {
            JOptionPane.showMessageDialog(null, "Class Cannot Be Instantiated", "Error", ERROR_MESSAGE);
        }
        catch (IllegalAccessException ex) {
            JOptionPane.showMessageDialog(null, "Illegal Access", "Error", ERROR_MESSAGE);
        }
    }
}
