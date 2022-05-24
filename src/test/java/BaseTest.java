import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


import java.io.File;
import java.io.IOException;
import java.util.Date;

import static constants.links.URL.BASE_URL;

public class BaseTest {
    WebDriver driver;

    @BeforeClass
    public void setUpChromeDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void openSUT() {
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void screenshotOnFailure(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            TakesScreenshot camera = (TakesScreenshot) driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            String fileInfo = String.valueOf(new Date().getTime());
            Files.move(screenshot, new File("src/test/screenshots/"
                    + result.getName()
                    + "-"
                    + fileInfo
                    + ".png"));
        }
    }

    @AfterSuite
    public void quitDriver() {
        driver.quit();
    }

}
