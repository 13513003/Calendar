package Calender;

import java.util.ArrayList;

/**
 * Created by user on 4/22/2015.
 */
public class Meeting extends Event {
    private ArrayList<String> participant;
    private String subject;
    public Meeting(){
        subject = "";
        participant = new ArrayList<String>();
    }
    public Meeting(String Name, String Location,String Time, String newsubject,ArrayList<String> newparticipant){
        super(Name,Location,Time);
        participant = new ArrayList<String>(newparticipant);
        subject = newsubject;
    }
    public String getParticipant(int i) {
        return participant.get(i);
    }

    public void addParticipant(String newparticipant) {
        participant.add(newparticipant);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String newsubject) {
        subject = newsubject;
    }
    @Override
    public void printEvent(){
        String participants = new String();
        for(String word : participant)
            participants += word;
        System.out.print(eventName+" "+eventLocation+" "+eventTime+" "+subject+" "+participants);
        System.out.println("");
    }
    @Override
    public String toStringEvent(){
        String temp = new String();
        String participants = new String();
        for(String word : participant)
            participants += word;
        temp = eventTime+','+eventName+','+eventLocation+','+subject+','+participants;
        return temp;
    }
}
