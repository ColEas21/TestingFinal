package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.example.ESPNLogin;

import java.time.Duration;
import java.util.List;

public class PlayoffTester {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = ESPNLogin.loginAndGetDriver();
        driver.get("https://www.espn.com");

    }

    @Test
    public void GetFantasyDropdown(){
        WebElement fantasyElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Fantasy']")));


        fantasyElement.click();
    }
    @Test(dependsOnMethods = "GetFantasyDropdown")
    public void GetNBAChallenge(){
        WebElement nbaPlayoffChallengeElement = driver.findElement(By.xpath("//a[@tabindex='0' and @itemprop='url' and @href='https://fantasy.espn.com/games/nba-playoff-challenge-2024/?addata=nbaplayoffchallenge2024_nba_web_fantasynav']"));


        nbaPlayoffChallengeElement.click();
    }
    @Test(dependsOnMethods = "GetNBAChallenge")
    public void clickBracket(){
        //String jsPath = "#fittPageContainer > div > div.CHUIPageContent_Children > div:nth-child(2) > div > div > div > section.Card.ChuiCard.ChuiCard--shadowMobile.BracketChallengeHome-section.css-qjh506 > section:nth-child(2) > a > div > div.BracketCardHeader-headerDataContainer > div.BracketCardHeader-titleContainer > div.css-150kxx3 > span > span";
        //((JavascriptExecutor) driver).executeScript("document.querySelector('" + jsPath + "').click();");
        WebElement element = driver.findElement(By.cssSelector("a[href='/games/nba-playoff-challenge-2024/bracket?id=238ff790-f84a-11ee-bf2f-bf5b7d72ba37']"));

        element.click();

    }

    @Test(dependsOnMethods = "clickBracket")
    public void clickGroups() throws InterruptedException {
        Thread.sleep(1000);
        //String jsPath = "#fittPageContainer > div > div.ChallengeNav.css-1kuag8l > div > div > div > div > nav > ul > li:nth-child(3) > a";
        //((JavascriptExecutor) driver).executeScript("document.querySelector('" + jsPath + "').click();");

    }


}
