/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lenovo
 */
public class MeetingTest {
    
    public MeetingTest() {
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
     * Test of getParticipant method, of class Meeting.
     */
    @Test
    public void testGetParticipant_0args() {
        System.out.println("getParticipant");
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("Test");
        Meeting instance = new Meeting("TestName", "TestLocation", "TestTime", "TestReminder", "TestSubject", expResult);
        ArrayList<String> result = instance.getParticipant();
        assertEquals(expResult, result);
    }

    /**
     * Test of getParticipant method, of class Meeting.
     */
    @Test
    public void testGetParticipant_int() {
        System.out.println("getParticipant");
        int i = 1;
        ArrayList<String> test = new ArrayList<String>();
        test.add("Test");
        test.add("Test2");
        Meeting instance = new Meeting("TestName", "TestLocation", "TestTime", "TestReminder", "TestSubject", test);
        String expResult = "Test2";
        String result = instance.getParticipant(i);
        assertEquals(expResult, result);
    }

    /**
     * Test of addParticipant method, of class Meeting.
     */
    @Test
    public void testAddParticipant() {
        System.out.println("addParticipant");
        String newparticipant = "Test";
        Meeting instance = new Meeting();
        instance.addParticipant(newparticipant);
        String result = instance.getParticipant().get(0);
        assertEquals(newparticipant, result);
    }

    /**
     * Test of getSubject method, of class Meeting.
     */
    @Test
    public void testGetSubject() {
        System.out.println("getSubject");
        ArrayList<String> test = new ArrayList<String>();
        Meeting instance = new Meeting("TestName", "TestLocation", "TestTime", "TestReminder", "TestSubject", test);
        String expResult = "TestSubject";
        String result = instance.getSubject();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSubject method, of class Meeting.
     */
    @Test
    public void testSetSubject() {
        System.out.println("setSubject");
        String newsubject = "TestSubject";
        Meeting instance = new Meeting();
        instance.setSubject(newsubject);
    }

    /**
     * Test of printEvent method, of class Meeting.
     */
    @Test
    public void testPrintEvent() {
        System.out.println("printEvent");
        Meeting instance = new Meeting();
        instance.printEvent();
    }

    /**
     * Test of toStringEvent method, of class Meeting.
     */
    @Test
    public void testToStringEvent() {
        System.out.println("toStringEvent");
        ArrayList<String> test = new ArrayList<String>();
        test.add("Test");
        Meeting instance = new Meeting("TestName", "TestLocation", "TestTime", "TestReminder", "TestSubject", test);
        String expResult = "TestTime"+','+"Meeting :"+"TestName"+','+"Lokasi :"+"TestLocation"+','+"Topik :"+"TestSubject"+','+"Partisipan :"+"Test"+','+"Pesan :"+"TestReminder";
        String result = instance.toStringEvent();
        assertEquals(expResult, result);
    }
    
}
