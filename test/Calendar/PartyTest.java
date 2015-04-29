/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar;

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
public class PartyTest {
    
    public PartyTest() {
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
     * Test of getDressCode method, of class Party.
     */
    @Test
    public void testGetDressCode() {
        System.out.println("getDressCode");
        Party instance = new Party("TestName", "TestLocation", "TestTime", "TestReminder", "TestSubject", "TestCode");
        String expResult = "TestCode";
        String result = instance.getDressCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDressCode method, of class Party.
     */
    @Test
    public void testSetDressCode() {
        System.out.println("setDressCode");
        String newdresscode = "";
        Party instance = new Party();
        instance.setDressCode(newdresscode);
    }

    /**
     * Test of getSubject method, of class Party.
     */
    @Test
    public void testGetSubject() {
        System.out.println("getSubject");
        Party instance = new Party("TestName", "TestLocation", "TestTime", "TestReminder", "TestSubject", "TestCode");
        String expResult = "TestSubject";
        String result = instance.getSubject();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSubject method, of class Party.
     */
    @Test
    public void testSetSubject() {
        System.out.println("setSubject");
        String newsubject = "";
        Party instance = new Party();
        instance.setSubject(newsubject);
    }

    /**
     * Test of printEvent method, of class Party.
     */
    @Test
    public void testPrintEvent() {
        System.out.println("printEvent");
        Party instance = new Party();
        instance.printEvent();
    }

    /**
     * Test of toStringEvent method, of class Party.
     */
    @Test
    public void testToStringEvent() {
        System.out.println("toStringEvent");
        Party instance = new Party("TestName", "TestLocation", "TestTime", "TestReminder", "TestSubject", "TestCode");
        String expResult = "TestTime"+','+"Party :"+"TestName"+','+"Lokasi :"+"TestLocation"+','+"Tema :"+"TestSubject"+','+"DC :"+"TestCode"+','+"Pesan :"+"TestReminder";
        String result = instance.toStringEvent();
        assertEquals(expResult, result);
    }
    
}
