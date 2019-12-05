package com.annusza.tau.selenium;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class WebDriverForTesting {
	
	protected WebDriver driver;

    public WebDriverForTesting(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
