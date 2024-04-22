package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class TeamInteraction {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = ESPNLogin.loginAndGetDriver();
    }

    @Test
    public void clickOnDropdown() throws InterruptedException {
        driver.get("https://www.espn.com");
        Thread.sleep(1000);
        WebElement element = driver.findElement(By.xpath("//a[@tabindex='0' and @itemprop='url' and @href='#']"));
        element.click();
    }

    @Test(dependsOnMethods = "clickOnDropdown")
    public void clickOnNcaaM() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement ncaamButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='link-text' and text()='NCAAM']")));
        ncaamButton.click();
    }

    @Test(dependsOnMethods = "clickOnNcaaM")
    public void clickOnTeams() throws InterruptedException {
        //driver.get("https://www.espn.com/mens-college-basketball/");
        Thread.sleep(1000);
        WebElement navElement = driver.findElement(By.id("global-nav-secondary"));

        // Step 2: Find the <ul> element with class "first-group" within the <nav>
        WebElement ulElement = navElement.findElement(By.className("first-group"));

        // Step 3: Find the <li> that contains an <a> with the specific href
        WebElement liElement = ulElement.findElement(By.xpath(".//li[a[@href='/mens-college-basketball/teams']]"));

        // Step 4: Click on the <li> element
        liElement.click();



    }

    @Test(dependsOnMethods = "clickOnTeams")
    public void clickOnFGCU() throws InterruptedException {
        Thread.sleep(1000);
        String jsPath = "#fittPageContainer > div:nth-child(3) > div > div > div.Wrapper.TeamsWrapper.br-5.mb3.pa5 > div.layout.is-split > div:nth-child(1) > div:nth-child(2) > div.ContentList.mt4.ContentList--NoBorder > div:nth-child(5) > div > section > div > a";
        ((JavascriptExecutor) driver).executeScript("document.querySelector('" + jsPath + "').click()");
    }

    @Test(dependsOnMethods = "clickOnFGCU")
    public void followTeam() throws InterruptedException {
        Thread.sleep(1000);
        String jsPath = "#fittPageContainer > div.StickyContainer > div.ResponsiveWrapper > div > div > div > div > div > button";
        ((JavascriptExecutor) driver).executeScript("document.querySelector('" + jsPath + "').click()");
    }
}
