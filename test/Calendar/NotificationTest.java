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
public class NotificationTest {
    
    public NotificationTest() {
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
     * Test of setNotificationEvent method, of class Notification.
     */
    @Test
    public void testSetNotificationEvent() {
        System.out.println("setNotificationEvent");
        Event e = new Event();
        Notification instance = new Notification();
        instance.setNotificationEvent(e);
    }

    /**
     * Test of getNotificationEvent method, of class Notification.
     */
    @Test
    public void testGetNotificationEvent() {
        System.out.println("getNotificationEvent");
        Event test = new Event("TestName", "TestLocation", "TestTime", "TestReminder");
        Notification instance = new Notification(test);
        Event expResult = test;
        Event result = instance.getNotificationEvent();
        assertEquals(expResult, result);
    }

    /**
     * Test of showNotification method, of class Notification.
     */
    @Test
    public void testShowNotification() {
        System.out.println("showNotification");
        Party test = new Party("TestName", "TestLocation", "TestTime", "TestReminder", "TestSubject", "TestCode");
        Notification instance = new Notification(test);
        instance.showNotification();
    }
    
}
