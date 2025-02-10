import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
 
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
 
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
 
public class CalculatorTest {
 
    private AppiumDriver driver;
 
    @Before
    public void setUp() throws MalformedURLException {
    	 DesiredCapabilities caps = new DesiredCapabilities();
         caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
         caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Xiaomi Redmi Note 8");
         caps.setCapability("appPackage", "com.example.calculatorapp");
         caps.setCapability("appActivity", "com.example.calculatorapp.MainActivity");
         caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
 
        driver = new AndroidDriver(new URL("http://localhost:4723"), caps);
    }
 
    @Test
    public void testAddition() {
        // Locate number buttons and operations
    	// Perform addition operation
   	 WebElement num1Field = driver.findElement(By.id("com.example.calculatorapp:id/etFirstNumber"));
   	 System.out.println("NumField 1 : "+num1Field);
         num1Field.sendKeys("5");
 
         WebElement num2Field = driver.findElement(By.id("com.example.calculatorapp:id/etSecondNumber"));
         num2Field.sendKeys("3");
 
         WebElement addButton = driver.findElement(By.id("com.example.calculatorapp:id/btnAdd"));
       addButton.click();
 
       WebElement resultView = driver.findElement(By.id("com.example.calculatorapp:id/tvResult"));
        // Assert the result
        String resultText = resultView.getText();
      assertEquals("Result: 8.0", resultText);
    }
 
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
