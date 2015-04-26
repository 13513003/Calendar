package Calendar;

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
    public Meeting(String Name, String Location, String Time, String Reminder,String newsubject,ArrayList<String> newparticipant){
        super(Name,Location,Time,Reminder);
        participant = new ArrayList<String>(newparticipant);
        subject = newsubject;
    }
    public ArrayList<String> getParticipant(){
        return participant;
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
        System.out.print(eventName+" "+eventLocation+" "+eventTime+" "+subject+" "+participants+" "+eventReminder);
        System.out.println("");
    }
    @Override
    public String toStringEvent(){
        String temp = new String();
        String participants = new String();
        int firstword = 1;
        for(String word : participant){
            if (firstword == 1){
                participants += word;
                firstword = 0;
            }
            else
                participants += "-"+word;
        }
        temp = eventTime+','+"Meeting :"+eventName+','+"Lokasi :"+eventLocation+','+"Topik :"+subject+','+"Partisipan :"+participants+','+"Pesan :"+eventReminder;
        return temp;
    }
}
