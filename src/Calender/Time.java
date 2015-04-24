package Calender;

import java.util.ArrayList;

/**
 * Created by user on 4/22/2015.
 */
public class Time {
    private ArrayList<Event> event;
    public Time(){
        event = new ArrayList<Event>();
    }
    public Time(ArrayList<Event> newevent){
        event = new ArrayList<Event>(newevent);
    }

    public void addTime(Event E){
        event.add(E);
    }

    public Event getEvent(int i){
        return event.get(i);
    }
}
