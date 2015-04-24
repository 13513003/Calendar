package Calender;

/**
 * Created by user on 4/22/2015.
 */
public class Notification {
    private Event notificationEvent;
    public Notification(Event e){
        notificationEvent = e;
    }

    public void setNotificationEvent(Event e){
        notificationEvent = e;
    }

    public Event getNotificationEvent(){
        return notificationEvent;
    }

    public void printNotification(){
        System.out.println();
    }
}
