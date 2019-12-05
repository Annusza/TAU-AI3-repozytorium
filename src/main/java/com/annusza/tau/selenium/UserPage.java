package com.annusza.tau.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class UserPage extends WebDriverForTesting {

	public UserPage(WebDriver driver) {

		super(driver);
	}

	@FindBy(css = "#center_column h1")
    private WebElement userMessage;

	
	public WebElement getUserMessage() {
	
		return userMessage;
	}
}
