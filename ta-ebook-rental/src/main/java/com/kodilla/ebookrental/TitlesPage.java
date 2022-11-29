package com.kodilla.ebookrental;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TitlesPage extends AbstractPage{

    @FindBy(id ="titles")
    WebElement titlesDiv;

    @FindBy(id ="add-title-button")
    WebElement addTitleBtn;

    @FindBy(css = ".list__item")
    List<WebElement> titles;

    public TitlesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void removeTitle(WebElement element){
        element.findElement(By.className("remove-btn")).click();
    }

    public void openItems(WebElement element){
        element.findElement(By.className("show-copies-btn")).click();
    }
}
