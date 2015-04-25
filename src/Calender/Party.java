package Calender;

/**
 * Created by user on 4/22/2015.
 */
public class Party extends Event {
    private String dresscode;
    private String subject;
    public Party(){
        dresscode = "";
        subject = "";
    }
    public Party(String Name, String Location, String Time, String newsubject,String newdresscode){
        super(Name,Location,Time);
        dresscode = newdresscode;
        subject = newsubject;
    }

    public String getDressCode() {
        return dresscode;
    }

    public void setDressCode(String newdresscode) {
        dresscode = newdresscode;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String newsubject) {
        subject = newsubject;
    }
    @Override
    public void printEvent(){
        System.out.print(eventName+" "+eventLocation+" "+eventTime+" "+subject+" "+dresscode);
        System.out.println("");
    }
    @Override
    public String toStringEvent(){
        String temp = new String();
        temp = eventTime+'\n'+eventName+'\n'+eventLocation+'\n'+subject+'\n'+dresscode;
        return temp;
    }
}
