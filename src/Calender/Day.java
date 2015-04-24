package Calender;

import java.util.ArrayList;

/**
 * Created by user on 4/22/2015.
 */
public class Day {
    private ArrayList<Event> event;

    public Day(){
        event = new ArrayList<Event>();
    }
    public Day(ArrayList<Event> newevent){
        event = new ArrayList<Event>(newevent);
    }

    public void addEvent(Event E){
        event.add(E);
    }

    public Event getEvent(int i){
        return event.get(i);
    }
}
