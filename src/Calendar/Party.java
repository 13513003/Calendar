package Calendar;

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
    public Party(String Name, String Location, String Time, String Reminder,String newsubject,String newdresscode){
        super(Name,Location,Time,Reminder);
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
    public String printEvent(){
        return eventName+"`"+eventLocation+"`"+eventTime+"`"+subject+"`"+dresscode+"`"+eventReminder;
        //System.out.println("");
    }
    @Override
    public String toStringEvent(){
        String temp = new String();
        temp = eventTime+';'+"Party: "+eventName+';'+"Lokasi: "+eventLocation+';'+"Tema: "+subject+';'+"DC: "+dresscode+';'+"Pesan: "+eventReminder;
        return temp;
    }
}
