/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class EventTest {
    Event e;
    
    public EventTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setEventName method, of class Event.
     */
    @Test
    public void testSetEventName() {
        System.out.println("setEventName");
        String Name = "";
        Event instance = new Event();
        instance.setEventName(Name);
    }

    /**
     * Test of setLocationName method, of class Event.
     */
    @Test
    public void testSetLocationName() {
        System.out.println("setLocationName");
        String Location = "";
        Event instance = new Event();
        instance.setLocationName(Location);
    }

    /**
     * Test of getEventName method, of class Event.
     */
    @Test
    public void testGetEventName() {
        System.out.println("getEventName");
        Event instance = new Event();
        String expResult = "Event";
        instance.setEventName(expResult);
        String result = instance.getEventName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEventLocation method, of class Event.
     */
    @Test
    public void testGetEventLocation() {
        System.out.println("getEventLocation");
        Event instance = new Event();
        String expResult = "Jakarta";
        instance.setLocationName(expResult);
        String result = instance.getEventLocation();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEventTime method, of class Event.
     */
    @Test
    public void testGetEventTime() {
        System.out.println("getEventTime");
        Event instance = new Event();
        String expResult = "09.00-10.00";
        instance.setEventTime(expResult);
        String result = instance.getEventTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEventTime method, of class Event.
     */
    @Test
    public void testSetEventTime() {
        System.out.println("setEventTime");
        String Time = "";
        Event instance = new Event();
        instance.setEventTime(Time);
    }

    /**
     * Test of getEventReminder method, of class Event.
     */
    @Test
    public void testGetEventReminder() {
        System.out.println("getEventReminder");
        Event instance = new Event();
        String expResult = "Alert";
        instance.setEventReminder(expResult);
        String result = instance.getEventReminder();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEventReminder method, of class Event.
     */
    @Test
    public void testSetEventReminder() {
        System.out.println("setEventReminder");
        String Reminder = "";
        Event instance = new Event();
        instance.setEventReminder(Reminder);
    }

    /**
     * Test of printEvent method, of class Event.
     */
    @Test
    public void testPrintEvent() {
        System.out.println("printEvent");
        Event instance = new Event();
        instance.printEvent();
    }

    /**
     * Test of toStringEvent method, of class Event.
     */
    @Test
    public void testToStringEvent() {
        System.out.println("toStringEvent");
        Event instance = new Event("Event","Jakarta","09.00","Reminder");
        String expResult = "09.00,Event,Jakarta,Reminder";
        String result = instance.toStringEvent();
        assertEquals(expResult, result);
    }
    
}
