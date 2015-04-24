package Calender;

/**
 * Created by user on 4/22/2015.
 */
public class Party extends Event {
    private String venue;
    private String subject;
    public Party(String Name, String Location, String newvenue, String newsubject){
        super(Name,Location);
        venue = newvenue;
        subject = newsubject;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String newvenue) {
        venue = newvenue;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String newsubject) {
        subject = newsubject;
    }
}
