package com.kodilla.ebookrental;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class ItemsPageTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void testSetup(){
        System.setProperty("webdriver.chrome.driver", "c:\\selenium-drivers\\chrome\\chromedriver.exe");
        driver = new ChromeDriver();

        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.navigate().to("https://ta-bookrental-fe.onrender.com/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAction("aleksandra-herlender","123");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-title-button")));
        TitlesPage titlesPage = new TitlesPage(driver);
        titlesPage.openItems(titlesPage.titles.get(titlesPage.titles.size()-1));
    }

    @AfterEach
    public void cleanUpTest(){
        driver.close();
    }

    @Test
    public void shouldAddCopy(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("title-copies")));
        ItemsPage itemsPage = new ItemsPage(driver);
        Integer items = itemsPage.items.size();
        itemsPage.addCopy();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("items-list__item"),items));
        itemsPage = new ItemsPage(driver);
        assertEquals(items+1,itemsPage.items.size());

    }

    @Test
    public void shouldRemoveCopy(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("title-copies")));
        ItemsPage itemsPage = new ItemsPage(driver);
        Integer items = itemsPage.items.size();
        itemsPage.removeCopy(itemsPage.items.get(items-1));
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.className("items-list__item"),items));
        itemsPage = new ItemsPage(driver);
        assertEquals(items-1,itemsPage.items.size());
    }
}