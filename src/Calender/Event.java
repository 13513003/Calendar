package Calender;

/**
 * Created by user on 4/22/2015.
 */
public class Event {
    private String eventName;
    private String eventLocation;
    public Event(String Name, String Location){
        eventName = Name;
        eventLocation = Location;
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
}
