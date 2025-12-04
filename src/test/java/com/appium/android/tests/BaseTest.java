package com.appium.android.tests;

import com.appium.android.utils.AppiumDriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    protected static AndroidDriver driver;

    @BeforeAll
    public static void globalSetUp() {
        System.out.println("Global test setup...");
        // Initialize driver once for all tests
        driver = AppiumDriverManager.getDriver();
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @AfterAll
    public static void globalTearDown() {
        System.out.println("Global test cleanup...");
        AppiumDriverManager.quitDriver();
    }
}