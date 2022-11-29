package com.kodilla.ebookrental;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class RentsPage extends AbstractPage {

    @FindBy(name = "add-rent-button")
    WebElement addRentButton;

    @FindBy(className = "rents-list__rent")
    List<WebElement> rents;

    public RentsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void addRent(String customerName){
        addRentButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fog__content")));
        driver.findElement(By.name("customer-name")).sendKeys(customerName);
        driver.findElement(By.name("submit-button")).click();
    }

    public void removeRent(WebElement element){
        element.findElement(By.className("remove-btn")).click();
    }


}
