package org.example;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;
public class SearchTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\colli\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        //driver = new ChromeDriver();
        //driver.manage().window().maximize();
        //driver.get("https://www.espn.com");
        driver = ESPNLogin.loginAndGetDriver();
        driver.get("https://www.espn.com");

    }

    @Test
    public void clickOnSearch(){


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement searchTrigger = wait.until(ExpectedConditions.elementToBeClickable(By.id("global-search-trigger")));

        // Click on the element
        searchTrigger.click();
    }

    @Test(dependsOnMethods = "clickOnSearch")
    public void SearchSport(){
        WebElement searchInputElement = driver.findElement(By.cssSelector("#global-search-input"));

        // Enter "football" into the input field
        searchInputElement.sendKeys("football");

        // Press Enter key to submit the search
        searchInputElement.sendKeys(Keys.ENTER);
    }

    @Test(dependsOnMethods = "SearchSport")
    public void naviToTeamsTabs() throws InterruptedException {
        Thread.sleep(15000);

        WebElement element = driver.findElement(By.cssSelector("#fittPageContainer > div:nth-child(2) > div > div > ul > li:nth-child(2) > a"));

        // Click on the element
        element.click();
    }

    @Test(dependsOnMethods = "naviToTeamsTabs")
    public void searchPlayer() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchInputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"fittPageContainer\"]/div[2]/div/div/div[1]/form/input")));

        // Clear the current search input
        searchInputElement.clear();

        // Enter "Tom Brady" into the input field
        searchInputElement.sendKeys("Tom Brady");

        // Press Enter key to submit the search
        searchInputElement.sendKeys(Keys.ENTER);

    }

    @Test(dependsOnMethods = "searchPlayer")
    public void SearchNonSense(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement IncorrectsearchInputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"fittPageContainer\"]/div[2]/div/div/div[1]/form/input")));
        IncorrectsearchInputElement.clear();
        IncorrectsearchInputElement.sendKeys("432423");
        IncorrectsearchInputElement.sendKeys(Keys.ENTER);

    }
}
