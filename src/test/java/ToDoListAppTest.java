

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertNotNull;

public class ToDoListAppTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
       
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Xiaomi Redmi Note 8");
        caps.setCapability("appPackage", "com.example.todolistapp");
        caps.setCapability("appActivity", "com.example.todolistapp.MainActivity");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
    }

    @Test
    public void testAddTask() throws InterruptedException {
        
        WebElement fabAdd = driver.findElement(By.id("com.example.todolistapp:id/fab_add"));
        fabAdd.click();
        Thread.sleep(2000);

        
        WebElement taskTitle = driver.findElement(By.id("com.example.todolistapp:id/edit_text_task"));
        taskTitle.sendKeys("Buy Groceries");

        
        WebElement taskDesc = driver.findElement(By.id("com.example.todolistapp:id/edit_text_task_desc"));
        taskDesc.sendKeys("Milk, Eggs, Bread, Chocolate");

       
        WebElement addButton = driver.findElement(By.id("com.example.todolistapp:id/btn_add_task"));
        addButton.click();
        Thread.sleep(3000);

        
        WebElement addedTask = driver.findElement(By.xpath("//android.widget.TextView[@text='Buy Groceries']"));
        assertNotNull("Task was not added.", addedTask);
        
        System.out.println(" Test Passed: Task 'Buy Groceries' added successfully!");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
