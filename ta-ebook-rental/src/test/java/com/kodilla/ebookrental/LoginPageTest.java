package com.kodilla.ebookrental;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginPageTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void testSetup(){
        System.setProperty("webdriver.chrome.driver", "c:\\selenium-drivers\\chrome\\chromedriver.exe");
        driver = new ChromeDriver();

        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.navigate().to("https://ta-bookrental-fe.onrender.com/login");

    }

    @AfterEach
    public void cleanUpTest(){
        driver.close();
    }

    @Test
    public void shouldFailedOnWrongCredentials(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAction("aleksandra-herlender","1234");
        assertLoginFailed("Login failed");
        driver.navigate().refresh();
        loginPage.loginAction("aleksandra-herlender","");
        assertLoginFailed("You can't leave fields empty");
        driver.navigate().refresh();
        loginPage.loginAction("","123");
        assertLoginFailed("You can't leave fields empty");
    }

    public void assertLoginFailed(String expected){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert--error")));
        WebElement error = driver.findElement(By.cssSelector(".alert--error"));
        assertEquals(expected, error.getText());
    }


    @Test
    public void shouldLoginSuccessfullyOnProperCredentials(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAction("aleksandra-herlender","123");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-title-button")));
        TitlesPage titlesPage = new TitlesPage(driver);
        assertNotNull(titlesPage.titlesDiv);
    }
}
