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
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterPageTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void testSetup(){
        System.setProperty("webdriver.chrome.driver", "c:\\selenium-drivers\\chrome\\chromedriver.exe");
        driver = new ChromeDriver();

        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.navigate().to("https://ta-bookrental-fe.onrender.com/register");

    }

    @AfterEach
    public void cleanUpTest(){
        driver.close();
    }

    public void assertRegisterFailed(String expected){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert--error")));
        WebElement error = driver.findElement(By.cssSelector(".alert--error"));
        assertEquals(expected, error.getText());
    }
    public void assertRegisterSuccess(String expected){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert--success")));
        WebElement success = driver.findElement(By.cssSelector(".alert--success"));
        assertEquals(expected, success.getText());
    }


    @Test
    public void shouldFailedOnWrongForm(){
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.registerAction("demo", "1", "2");
        assertRegisterFailed("The passwords don't match");
        driver.navigate().refresh();
        registerPage.registerAction("aleksandra-herlender","", "1");
        assertRegisterFailed("You can't leave fields empty");
        driver.navigate().refresh();
        registerPage.registerAction("","123","123");
        assertRegisterFailed("You can't leave fields empty");
        driver.navigate().refresh();
        registerPage.registerAction("demo","123","");
        assertRegisterFailed("You can't leave fields empty");
    }

    @Test
    public void shouldCreateNewUserOnProperCredentials(){
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.registerAction("test-aleksandry"+ OffsetDateTime.now().toInstant(), "12", "12");
        assertRegisterSuccess("You have been successfully registered!");
    }
}