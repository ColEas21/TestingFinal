package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ESPNLogin {

    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\colli\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("excludeSwitches", List.of("enable-logging"));
        ChromeDriverService service = new ChromeDriverService.Builder().build();
        driver = new ChromeDriver(service, chromeOptions);
        driver.manage().window().maximize();
    }

    //@AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void handleContinueButton(WebDriver driver) {
        WebElement continueButton = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.presenceOfElementLocated(By.id("BtnSubmit"))
        );
        continueButton.click();
    }

    @Test
    public void mainFunction() {
        ESPNLogin.driver.get("https://www.espn.com");

        WebElement profileIcon = new WebDriverWait(ESPNLogin.driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.presenceOfElementLocated(By.id("global-user-trigger"))
        );
        Actions actions = new Actions(ESPNLogin.driver);
        actions.moveToElement(profileIcon).click().perform();

        WebElement accountManagement = new WebDriverWait(ESPNLogin.driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfElementLocated(By.className("account-management"))
        );

        WebElement loginLink = accountManagement.findElement(By.xpath(".//a[text()='Log In']"));
        actions.moveToElement(loginLink).click().perform();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement oneidWrapper = new WebDriverWait(ESPNLogin.driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfElementLocated(By.id("oneid-wrapper"))
        );

        WebElement iframe = oneidWrapper.findElement(By.id("oneid-iframe"));
        ESPNLogin.driver.switchTo().frame(iframe);

        WebElement usernameField = new WebDriverWait(ESPNLogin.driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[data-testid='InputIdentityFlowValue']"))
        );
        usernameField.sendKeys("creasley5167@eagle.fgcu.edu");

        handleContinueButton(ESPNLogin.driver);

        WebElement passwordField = new WebDriverWait(ESPNLogin.driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.presenceOfElementLocated(By.id("InputPassword"))
        );
        passwordField.sendKeys("Topp21?2");

        handleContinueButton(ESPNLogin.driver);


        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //return driver;
    }

    public static WebDriver loginAndGetDriver() {
        if (driver == null) {
            setUp(); // Ensure driver is initialized before returning
        }

        return driver;
    }
}
