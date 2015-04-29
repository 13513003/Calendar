package Calendar;

/**
 * Created by user on 4/22/2015.
 */

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

/**
 *
 * @author Lenovo
 */

public class Notification {
    private Event notificationEvent;
    public Notification(){
        notificationEvent = new Event();
    }
    public Notification(Event e){
        notificationEvent = e;
    }

    public void setNotificationEvent(Event e){
        notificationEvent = e;
    }

    public Event getNotificationEvent(){
        return notificationEvent;
    }

    public void showNotification(){
        try {
            String bip = "UI/alarm_beep.wav";
            File wavFile = new File(bip);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(wavFile);
            AudioFormat af = audioInputStream.getFormat();
            int nSize = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
            byte[] audio = new byte[nSize];
            DataLine.Info info = new DataLine.Info(Clip.class, af, nSize);
            audioInputStream.read(audio, 0, nSize);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(af, audio, 0, nSize);
            clip.start();
            String output = new String();
            if(notificationEvent instanceof Party) {
                Party notifParty = (Party) notificationEvent;
                output = notifParty.getEventName() + "\n" +
                        notifParty.getEventTime() + "\n" +
                        notifParty.getEventLocation() + "\n" +
                        notifParty.getSubject() + "\n" +
                        notifParty.getDressCode() + "\n"+
                        notifParty.getEventReminder() + "\n" ;
            }
            else if (notificationEvent instanceof Meeting){
                Meeting notifMeeting = (Meeting) notificationEvent;
                String participants = new String();
                int firstword = 1;
                for(String word : notifMeeting.getParticipant()){
                    if (firstword == 1){
                        participants += word;
                        firstword = 0;
                    }
                    else
                        participants += "-"+word;
                }
                output = notifMeeting.getEventName() + "\n" +
                        notifMeeting.getEventTime() + "\n" +
                        notifMeeting.getEventLocation() + "\n" +
                        notifMeeting.getSubject() + "\n" +
                        participants + "\n"+
                        notifMeeting.getEventReminder() + "\n" ;
            }
            JOptionPane message = new JOptionPane(output, INFORMATION_MESSAGE);
            JDialog dialog = message.createDialog(null, "Notification");
            dialog.show();
            clip.stop();
        } catch (Exception e) {

        }
    }
}
