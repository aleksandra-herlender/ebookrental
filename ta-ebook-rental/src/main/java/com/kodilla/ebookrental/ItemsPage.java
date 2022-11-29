package com.kodilla.ebookrental;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ItemsPage extends AbstractPage {

    @FindBy(name = "add-item-button")
    WebElement addItemBtn;

    @FindBy(className = "items-list__item")
    List<WebElement> items;

    public ItemsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void addCopy(){
        addItemBtn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("submit-button")));
        driver.findElement(By.name("submit-button")).click();
    }

    public void removeCopy(WebElement element) {
        element.findElement(By.className("remove-btn")).click();
    }

    public void openHistory(WebElement element) {
        element.findElement(By.className("show-rents-btn")).click();
    }
}
