package edu.tacoma.uw.stephd27.testinglab;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

public class AccountTest {

    @Test
    public void testAccountConstructor() {
        assertNotNull(new Account("stephd27@uw.edu", "test1@3"));

    }

    @Test
    public void testAccountConstructorBadEmail() {
        try {
            new Account("stephd27uw.edu", "test1@3");
            fail("Account created with invalid email.");
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void testAccountConstructorNullEmail() {
        try {
            new Account(null, "test1@3");
            fail("Account created with null email.");
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void testAccountConstructorShortPwd() {
        try {
            new Account("stephd27@uw.edu", "beep");
            fail("Account created with short password");
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void testAccountConstructorNullPwd() {
        try {
            new Account("stephd27@uw.edu", null);
            fail("Account created with no Symbol password");
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void testAccountConstructorNoDigitPwd() {
        try {
            new Account("stephd27@uw.edu", "beep@boop");
            fail("Account created with no digit password");
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void testAccountConstructorNoSymbolPwd() {
        try {
            new Account("stephd27@uw.edu", "beep1boop");
            fail("Account created with no Symbol password");
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void testGetEmail() {
        Account test = new Account("Stephd27@uw.edu", "test1@3");
        assertNotNull(test.getmEmail());
        assertEquals("Stephd27@uw.edu", test.getmEmail());
    }

    @Test
    public void testSetEmail() {
        Account test = new Account("Stephd27@uw.edu", "test1@3");
        test.setmEmail("cassieRenz@uw.edu");
        assertEquals("cassieRenz@uw.edu", test.getmEmail());
    }




    @Test
    public void testGetPwd() {
        Account test = new Account("Stephd27@uw.edu", "test1@3");
        assertNotNull(test.getmPwd());
        assertEquals("test1@3", test.getmPwd());
    }

    @Test
    public void testSetPwd() {
        Account test = new Account("Stephd27@uw.edu", "test1@3");
        test.setmPwd("beep@boop1");
        assertEquals("beep@boop1", test.getmPwd());
    }


}
