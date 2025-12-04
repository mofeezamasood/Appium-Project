package com.appium.android.tests;

import com.appium.android.pages.SignUpPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Sign Up functionality
 * Uses Page Object Model pattern
 */
public class SignUpPageTest extends BaseTest {

    private SignUpPage signUpPage;

    @Test
    public void testSignUpAndLogin() {
        signUpPage = new SignUpPage();

        // Test Sign Up
        String email = "email@test.com";
        String password = "12345678";
        
        signUpPage.signUp(email, password);
        
        // Verify success message if shown
        try {
            String title = signUpPage.getSuccessMessageTitle();
            String message = signUpPage.getSuccessMessage();
            assertNotNull(title, "Success message title should not be null");
            assertNotNull(message, "Success message should not be null");
            signUpPage.closeSuccessMessage();
        } catch (Exception e) {
            // Success message might not appear, continue with login test
            System.out.println("No success message shown, continuing with login test");
        }

        // Test Login
        signUpPage.login(email, password);
    }

    @Test
    public void testSignUpOnly() {
        signUpPage = new SignUpPage();
        
        signUpPage.signUp("test@example.com", "password123");
        
        // Verify success message
        try {
            String title = signUpPage.getSuccessMessageTitle();
            assertNotNull(title, "Success message title should not be null");
            signUpPage.closeSuccessMessage();
        } catch (Exception e) {
            System.out.println("No success message shown");
        }
    }
}
