package com.appium.android.tests;

import com.appium.android.pages.FormPage;
import com.appium.android.pages.HomePage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.Point;

import java.time.Duration;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Form Test using Page Object Model pattern
 * Cleaner, more maintainable test code
 */
public class FormPageTest extends BaseTest {

    private HomePage homePage;
    private FormPage formPage;

    @Test
    public void testFormComponents() {
        // Initialize page objects
        homePage = new HomePage();
        formPage = new FormPage();
        
        try {
            // Navigate to Forms page
            homePage.clickMenuIcon();
            homePage.openMenu("Forms");
            
            // Fill form using Page Object methods
            formPage.fillForm("type something", "Appium is awesome");
            
            // Toggle switch twice (as in original test)
            formPage.toggleSwitch();
            
            // Perform swipe gesture (if needed)
            performSwipe();
            
            // Test Active button
            formPage.clickActiveButton();
            String alertTitle = formPage.getActiveMessageTitle();
            String alertMessage = formPage.getActiveMessage();
            assertNotNull(alertTitle, "Alert title should not be null");
            assertNotNull(alertMessage, "Alert message should not be null");
            formPage.closeAlert();
            
            // Test Inactive button
            formPage.clickInactiveButton();
            
            // Verify input text result
            String inputResult = formPage.getInputText();
            assertNotNull(inputResult, "Input text result should not be null");
            
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    @Test
    public void testFormWithDropdownIndex() {
        homePage = new HomePage();
        formPage = new FormPage();
        
        try {
            homePage.clickMenuIcon();
            homePage.openMenu("Forms");
            
            // Fill form using dropdown index instead of text
            formPage.fillForm("test input", 0); // Select first option
            
            formPage.submitForm();
            formPage.closeAlert();
            
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    @Test
    public void testFormValidation() {
        homePage = new HomePage();
        formPage = new FormPage();
        
        try {
            homePage.clickMenuIcon();
            homePage.openMenu("Forms");
            
            // Test switch functionality
            formPage.toggleSwitch();
            String switchText = formPage.getSwitchText();
            assertNotNull(switchText, "Switch text should not be null");
            
            // Test dropdown selection
            formPage.openDropdown();
            formPage.selectDropdownOptionByText("Appium is awesome");
            String selectedValue = formPage.getSelectedDropdownValue();
            assertNotNull(selectedValue, "Selected dropdown value should not be null");
            
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    /**
     * Helper method to perform swipe gesture
     */
    private void performSwipe() {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Point start = new Point(458, 1417);
        Point end = new Point(468, 1101);
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
            PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),
            PointerInput.Origin.viewport(), end.getX(), end.getY()));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
    }
}
