/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calendar;

import java.util.ArrayList;
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
public class DayTest {
    
    public DayTest() {
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
     * Test of addEvent method, of class Day.
     */
    @Test
    public void testAddEvent() {
        System.out.println("addEvent");
        Event E = null;
        Day instance = new Day();
        instance.addEvent(E);
    }

    /**
     * Test of getEvent method, of class Day.
     */
    @Test
    public void testGetEvent_int() {
        System.out.println("getEvent");
        int i = 0;
        Day instance = new Day();
        Event expResult = null;
        instance.addEvent(expResult);
        Event result = instance.getEvent(i);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEvent method, of class Day.
     */
    @Test
    public void testGetEvent_0args() {
        System.out.println("getEvent");
        Event e = new Event();
        ArrayList<Event> expResult = new ArrayList<>();
        expResult.add(e);
        Day instance = new Day(expResult);
        ArrayList<Event> result = instance.getEvent();
        assertEquals(expResult, result);
    }

    /**
     * Test of printDay method, of class Day.
     */
    @Test
    public void testPrintDay() {
        System.out.println("printDay");
        Day instance = new Day();
        instance.printDay();
    }

    /**
     * Test of isDayEmpty method, of class Day.
     */
    @Test
    public void testIsDayEmpty() {
        System.out.println("isDayEmpty");
        Day instance = new Day();
        int expResult = 1;
        int result = instance.isDayEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of toStringDay method, of class Day.
     */
    @Test
    public void testToStringDay() {
        System.out.println("toStringDay");
        int i = 0;
        Event e = new Event("Event","Jakarta","09.00","Reminder");
        Day instance = new Day();
        instance.addEvent(e);
        String expResult = "09.00,Event,Jakarta,Reminder";
        String result = instance.toStringDay(i);
        assertEquals(expResult, result);
    }
    
}
