package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class TeamInteraction {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\colli\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        //driver = new ChromeDriver();
        //driver.manage().window().maximize();
        //driver.get("https://www.espn.com");
        driver = ESPNLogin.loginAndGetDriver();
    }


    @Test
    public void clickOnDropdown() {
        driver.get("https://www.espn.com");
        WebElement element = driver.findElement(By.xpath("//a[@tabindex='0' and @itemprop='url' and @href='#']"));

        // Click on the element
        element.click();
    }



    @Test(dependsOnMethods = "clickOnDropdown")
    public void clickOnNcaaM() throws InterruptedException {
        Thread.sleep(1000);
        WebElement ncaamButton = driver.findElement(By.xpath("//span[@class='link-text' and text()='NCAAM']"));
        ncaamButton.click();


    }

    @Test(dependsOnMethods = "clickOnNcaaM")
    public void clickOnTeams() throws InterruptedException {
        // Wait for the element to be clickable
        // Wait for the element to be clickable
        Thread.sleep(1000);
        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
        boolean elementFound = false;

        for (WebElement frame : frames) {
            driver.switchTo().frame(frame);
            // Check if the element is present inside the frame
            if (driver.findElements(By.xpath("//a[@name='&lpos=subnav+subnav_ncb_teams']")).size() > 0) {
                elementFound = true;
                break;
            }
            // Switch back to the default content
            driver.switchTo().defaultContent();
        }

        if (elementFound) {
            // Perform actions on the element
            WebElement yourElement = driver.findElement(By.xpath("//a[@name='&lpos=subnav+subnav_ncb_teams']"));
            yourElement.click(); // Example action: Clicking on the element
        } else {
            // Element not found
            System.out.println("Element not found.");
        }

// Switch back to the default content
        driver.switchTo().defaultContent();
    }

    @Test (dependsOnMethods = "clickOnTeams")
    public void clickOnFGCU(){
        //driver.get("https://www.espn.com/mens-college-basketball/teams");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate the Florida Gulf Coast Eagles title and click on it
        String jsPath = "#fittPageContainer > div:nth-child(3) > div > div > div.Wrapper.TeamsWrapper.br-5.mb3.pa5 > div.layout.is-split > div:nth-child(1) > div:nth-child(2) > div.ContentList.mt4.ContentList--NoBorder > div:nth-child(5) > div > section > div > a";
        ((JavascriptExecutor) driver).executeScript("document.querySelector('" + jsPath + "').click();");



    }

    @Test(dependsOnMethods = "clickOnFGCU")
    public void followteam() throws InterruptedException {
        Thread.sleep(1000);
        String jsPath = "#fittPageContainer > div.StickyContainer > div.ResponsiveWrapper > div > div > div > div > div > button";

        // Execute JavaScript to click on the element
        ((JavascriptExecutor) driver).executeScript("document.querySelector('" + jsPath + "').click();");

    }



}
