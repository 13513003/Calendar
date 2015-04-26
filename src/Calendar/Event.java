package Calendar;

/**
 * Created by user on 4/22/2015.
 */
public class Event {
    protected String eventName;
    protected String eventLocation;
    protected String eventTime;
    protected String eventReminder;
    public Event(){
        eventName = "";
        eventLocation = "";
        eventTime = "";
        eventReminder = "";
    }
    public Event(String Name, String Location, String Time,String Reminder){
        eventName = Name;
        eventLocation = Location;
        eventTime = Time;
        eventReminder = Reminder;
    }

    public void setEventName(String Name){
        eventName = Name;
    }

    public void setLocationName(String Location){
        eventLocation = Location;
    }

    public String getEventName(){
        return eventName;
    }

    public String getEventLocation(){
        return eventLocation;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String Time) {
        eventTime = Time;
    }

    public String getEventReminder() {
        return eventReminder;
    }

    public void setEventReminder(String Reminder) {
        eventReminder = Reminder;
    }
    public void printEvent(){
        System.out.print(eventName+" "+eventLocation+" "+eventTime+" "+eventReminder);
        System.out.println("");
    }

    public String toStringEvent(){
        String temp = new String();
        temp = eventTime+','+eventName+','+eventLocation+','+eventReminder;
        return temp;
    }
}
