# Appium Automation Project

This is a Java-based Appium automation project for Android app testing.

## Prerequisites

1. **Java JDK 11 or higher** - Install and set JAVA_HOME
2. **Maven** - Install Maven (https://maven.apache.org/install.html)
3. **Appium Server** - Install Appium Server
   ```bash
   npm install -g appium
   ```
4. **Android SDK** - Install Android SDK and set ANDROID_HOME
5. **Android Device/Emulator** - Connect a physical device or start an emulator

## Setup Instructions

### 1. Install Dependencies

```bash
mvn clean install
```

### 2. Configure Your APK

Update the APK path in `src/test/java/com/appium/utils/AppiumDriverManager.java`:

```java
options.setApp("/absolute/path/to/your/app.apk");
```

Or if the app is already installed, use:

```java
options.setAppPackage("com.example.package");
options.setAppActivity("com.example.package.MainActivity");
```

### 3. Configure Device Settings

In `AppiumDriverManager.java`, update:
- `setDeviceName()` - Your device/emulator name
- `setUdid()` - Optional: Device UDID if you have multiple devices

### 4. Start Appium Server

```bash
appium
```

The server will start on `http://localhost:4723` by default.

### 5. Connect Android Device

- **Physical Device**: Enable USB debugging and connect via USB
- **Emulator**: Start your Android emulator

Verify device connection:
```bash
adb devices
```

## Adding Your Recorded Code

### Step 1: Copy Code from Appium Inspector

1. Open Appium Inspector
2. Record your test steps
3. Copy the generated Java code

### Step 2: Integrate into Test Class

1. Open `src/test/java/com/appium/tests/AppTest.java`
2. Replace the `sampleTest()` method with your recorded code
3. Update element locators if needed

### Example Conversion:

**From Appium Inspector (might look like):**
```java
driver.findElement(By.id("com.example:id/button")).click();
```

**Update to use AppiumBy:**
```java
driver.findElement(AppiumBy.id("com.example:id/button")).click();
```

### Common Element Locators:

- **ID**: `AppiumBy.id("com.example:id/button")`
- **Accessibility ID**: `AppiumBy.accessibilityId("Login")`
- **XPath**: `AppiumBy.xpath("//android.widget.Button[@text='Submit']")`
- **Class Name**: `AppiumBy.className("android.widget.Button")`
- **Android UIAutomator**: `AppiumBy.androidUIAutomator("new UiSelector().text(\"Click\")")`

## Running Tests

### Run all tests:
```bash
mvn test
```

### Run specific test class:
```bash
mvn test -Dtest=AppTest
```

### Run with IntelliJ IDEA or Eclipse:
- Right-click on test class → Run

## Project Structure

```
Appium Project/
├── pom.xml                          # Maven dependencies
├── src/
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── appium/
│       │           ├── tests/
│       │           │   ├── BaseTest.java      # Base test class
│       │           │   └── AppTest.java       # Your test class
│       │           └── utils/
│       │               └── AppiumDriverManager.java  # Driver setup
│       └── resources/
│           └── config.properties    # Configuration file
└── README.md
```

## Troubleshooting

### Issue: "Cannot find device"
- Check `adb devices` to see connected devices
- Verify device name in `AppiumDriverManager.java`
- Ensure USB debugging is enabled

### Issue: "Appium server not running"
- Start Appium server: `appium`
- Check if port 4723 is available
- Verify Appium server URL in code

### Issue: "APK not found"
- Use absolute path for APK file
- Verify file exists and path is correct

### Issue: "Element not found"
- Use Appium Inspector to verify element locators
- Add explicit waits if elements load slowly
- Check if element is in a different activity/screen

## Next Steps

1. Add your APK file to the project (or reference its path)
2. Copy your recorded test code into `AppTest.java`
3. Update element locators
4. Run your first test!

## Additional Resources

- [Appium Documentation](http://appium.io/docs/en/about-appium/intro/)
- [Appium Java Client](https://github.com/appium/java-client)
- [Selenium WebDriver](https://www.selenium.dev/documentation/)

