package com.appium.android.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

public class AppiumDriverManager {
    private static AndroidDriver driver;
    private static WebDriverWait wait;

    public static AndroidDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    public static WebDriverWait getWait() {
        if (wait == null && driver != null) {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
        return wait;
    }

    private static void initializeDriver() {
        UiAutomator2Options options = new UiAutomator2Options();
        
        // Set your APK path here - Update the filename to match your APK
        String apkFileName = "Android App.apk"; // APK filename
        String apkPath = getApkPath(apkFileName);
        options.setApp(apkPath);
        
        // Alternative: If app is already installed, use package and activity
        // options.setAppPackage("com.example.package");
        // options.setAppActivity("com.example.package.MainActivity");
        
        // Device configuration
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setDeviceName("emulator-5554"); // Updated to match your emulator
        options.setUdid("emulator-5554"); // Set UDID to match your device
        
        // Additional options
        options.setNoReset(false); // Set to true if you don't want to reset app state
        options.setFullReset(true); // Set to true for full reset (uninstalls app first)
        options.setAutoGrantPermissions(true); // Automatically grant permissions
        options.setNewCommandTimeout(Duration.ofSeconds(300)); // Increase timeout
        options.setAppWaitActivity("*"); // Wait for any activity to start
        options.setAppWaitDuration(Duration.ofSeconds(30)); // Wait up to 30 seconds for app to start
        
        try {
            // Appium server URL - default is localhost:4723
            URI appiumServerUri = URI.create("http://localhost:4723");
            System.out.println("Connecting to Appium server at: " + appiumServerUri);
            System.out.println("APK path: " + apkPath);
            System.out.println("Device UDID: emulator-5554");
            
            driver = new AndroidDriver(appiumServerUri.toURL(), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            System.out.println("Driver initialized successfully!");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium server URL", e);
        } catch (Exception e) {
            System.err.println("Failed to initialize driver: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize Appium driver. Make sure:\n" +
                    "1. Appium server is running (http://localhost:4723)\n" +
                    "2. Device/emulator is connected (check with 'adb devices')\n" +
                    "3. APK file exists and is valid", e);
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            wait = null;
        }
    }

    /**
     * Gets the absolute path to the APK file in the apps folder
     * @param apkFileName Name of the APK file (e.g., "myapp.apk")
     * @return Absolute path to the APK file
     */
    private static String getApkPath(String apkFileName) {
        // Get the project root directory (assuming we're running from project root)
        String projectRoot = System.getProperty("user.dir");
        File apkFile = new File(projectRoot, "apps" + File.separator + apkFileName);
        
        if (!apkFile.exists()) {
            throw new RuntimeException("APK file not found at: " + apkFile.getAbsolutePath() + 
                    "\nPlease place your APK file in the 'apps' folder.");
        }
        
        return apkFile.getAbsolutePath();
    }
}

