/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package com.mycompany.prog5121poe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    @Test
    public void testCheckUserName_Correct() {
        Login login = new Login();
        boolean result = login.checkUserName("kyl_1");
        assertTrue(result);
    }

    @Test
    public void testCheckUserName_Incorrect() {
        Login login = new Login();
        boolean result = login.checkUserName("kyle!!!!!!!");
        assertFalse(result);
    }

    @Test
    public void testCheckPasswordComplexity_Correct() {
        Login login = new Login();
        boolean result = login.checkPasswordComplexity("Ch&&sec@ke99!");
        assertTrue(result);
    }

    @Test
    public void testCheckPasswordComplexity_Incorrect() {
        Login login = new Login();
        boolean result = login.checkPasswordComplexity("password");
        assertFalse(result);
    }

    @Test
    public void testCheckCellPhoneNumber_Correct() {
        Login login = new Login();
        boolean result = login.checkCellPhoneNumber("+27838968976");
        assertTrue(result);
    }

    @Test
    public void testCheckCellPhoneNumber_Incorrect() {
        Login login = new Login();
        boolean result = login.checkCellPhoneNumber("08966553");
        assertFalse(result);
    }

    @Test
    public void testLoginUser_Successful() {
        Login login = new Login();
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        boolean result = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertTrue(result);
    }

    @Test
    public void testLoginUser_Failed() {
        Login login = new Login();
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        boolean result = login.loginUser("kyl_1", "WrongPassword1!");
        assertFalse(result);
    }

    @Test
    public void testReturnLoginStatus_Successful() {
        Login login = new Login();
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        String result = login.returnLoginStatus("kyl_1", "Ch&&sec@ke99!");
        assertEquals("Welcome Kyle, Smith it is great to see you again.", result);
    }

    @Test
    public void testReturnLoginStatus_Failed() {
        Login login = new Login();
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        String result = login.returnLoginStatus("kyl_1", "WrongPassword1!");
        assertEquals("Username or password incorrect, please try again.", result);
    }
}
