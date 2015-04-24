package Calender;

/**
 * Created by user on 4/22/2015.
 */
public class Event {
    protected String eventName;
    protected String eventLocation;
    protected String eventTime;
    public Event(){
        eventName = "";
        eventLocation = "";
        eventTime = "";
        System.out.println("b");
    }
    public Event(String Name, String Location, String Time){
        eventName = Name;
        eventLocation = Location;
        eventTime = Time;
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

    public String getLocationName(){
        return eventLocation;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public void printEvent(){
        System.out.print(eventName+" "+eventLocation+" "+eventTime);
        System.out.println("");
    }
}
