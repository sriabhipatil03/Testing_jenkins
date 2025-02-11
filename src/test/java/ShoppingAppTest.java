

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

public class ShoppingAppTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Xiaomi Redmi Note 8");
        capabilities.setCapability("appPackage", "com.example.shoppingapp");
        capabilities.setCapability("appActivity", "com.example.shoppingapp.MainActivity");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);
    }

    @Test
    public void testPerformVisibleSwipe() {
        System.out.println("Performing visible swipe...");

        WebElement recyclerView = driver.findElement(AppiumBy.className("androidx.recyclerview.widget.RecyclerView"));
        assertNotNull("RecyclerView not found!", recyclerView);

        int startX = recyclerView.getLocation().getX() + (int) (recyclerView.getSize().getWidth() * 0.8);
        int endX = recyclerView.getLocation().getX() + (int) (recyclerView.getSize().getWidth() * 0.2);
        int y = recyclerView.getLocation().getY() + recyclerView.getSize().getHeight() / 2;

        System.out.println("Swipe Coordinates -> startX: " + startX + ", endX: " + endX + ", y: " + y);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, y))
                .addAction(finger.createPointerDown(0))
                .addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, y))
                .addAction(finger.createPointerUp(0));

        driver.perform(Arrays.asList(swipe));

        System.out.println("Test Passed: Swipe action performed successfully!");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
