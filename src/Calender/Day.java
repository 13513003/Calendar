package Calender;

import java.util.ArrayList;

/**
 * Created by user on 4/22/2015.
 */
public class Day {
    private ArrayList<Time> time;
    public Day(){
        time = new ArrayList<Time>();
    }
    public Day(ArrayList<Time> newtime){
        time = new ArrayList<Time>(newtime);
    }

    public void addTime(Time T){
        time.add(T);
    }

    public Time getTime(int i){
        return time.get(i);
    }
}
