package com.appium.android.tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class TestSuiteRunner extends BaseTest {
    
    private FormPageTest formPageTest;
    private SwipePageTest swipePageTest;
    private WebViewPageTest webViewPageTest;
    private DragPageTest dragPageTest;
    private SignUpPageTest signUpPageTest;
    
    @BeforeAll
    public void initializeTests() {
        System.out.println("Initializing test suite...");
        formPageTest = new FormPageTest();
        swipePageTest = new SwipePageTest();
        webViewPageTest = new WebViewPageTest();
        dragPageTest = new DragPageTest();
        signUpPageTest = new SignUpPageTest();
        
        // Setup driver for WebViewPageTest (since it doesn't extend BaseTest)
        try {
            webViewPageTest.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    @Order(1)
    public void testFormPage() {
        System.out.println("=== Running FormPage Tests ===");
        try {
            formPageTest.testFormComponents();
            System.out.println("✓ Form components test passed");
            
            formPageTest.testFormWithDropdownIndex();
            System.out.println("✓ Form dropdown index test passed");
            
            formPageTest.testFormValidation();
            System.out.println("✓ Form validation test passed");
            
        } catch (Exception e) {
            System.err.println("FormPage tests failed: " + e.getMessage());
            throw e;
        }
    }
    
    @Test
    @Order(2)
    public void testSwipePage() {
        System.out.println("=== Running SwipePage Tests ===");
        try {
            swipePageTest.sampleTest();
            System.out.println("✓ Swipe page test passed");
        } catch (Exception e) {
            System.err.println("SwipePage test failed: " + e.getMessage());
            throw e;
        }
    }
    
    @Test
    @Order(3)
    public void testWebViewPage() {
        System.out.println("=== Running WebViewPage Tests ===");
        try {
            webViewPageTest.webViewNavigationTest();
            System.out.println("✓ WebView navigation test passed");
            
            webViewPageTest.alternativeSwipeTest();
            System.out.println("✓ WebView alternative swipe test passed");
            
            webViewPageTest.simpleWebViewTest();
            System.out.println("✓ WebView simple test passed");
            
        } catch (Exception e) {
            System.err.println("WebViewPage tests failed: " + e.getMessage());
            throw e;
        }
    }
    
    @Test
    @Order(4)
    public void testDragPage() {
        System.out.println("=== Running DragPage Tests ===");
        try {
            dragPageTest.sampleTest();
            System.out.println("✓ Drag page test passed");
        } catch (Exception e) {
            System.err.println("DragPage test failed: " + e.getMessage());
            throw e;
        }
    }
    
    @Test
    @Order(5)
    public void testSignUpPage() {
        System.out.println("=== Running SignUpPage Tests ===");
        try {
            signUpPageTest.testSignUpAndLogin();
            System.out.println("✓ SignUp and Login test passed");
            
            signUpPageTest.testSignUpOnly();
            System.out.println("✓ SignUp only test passed");
            
        } catch (Exception e) {
            System.err.println("SignUpPage tests failed: " + e.getMessage());
            throw e;
        }
    }
    
    @AfterAll
    public void cleanup() {
        System.out.println("=== Cleaning up test suite ===");
        
        // Cleanup WebViewPageTest
        try {
            webViewPageTest.tearDown();
        } catch (Exception e) {
            System.err.println("Error during WebViewPageTest cleanup: " + e.getMessage());
        }
        
        // You may also want to quit the driver from BaseTest
        // AppiumDriverManager.quitDriver();
        
        System.out.println("Test suite completed!");
    }
}