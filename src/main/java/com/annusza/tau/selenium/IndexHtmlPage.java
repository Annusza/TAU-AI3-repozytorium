package com.annusza.tau.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IndexHtmlPage extends WebDriverForTesting {

	public IndexHtmlPage(WebDriver driver) {

		super(driver);
	}

	@FindBy(className = "login")
	private WebElement login;

	@FindBy(className = "logout")
	private WebElement logout;

	@FindBy(className = "account")
	private WebElement account;

	
	public WebElement getLogin() {
	
		return login;
	}

	
	public void setLogin(WebElement login) {
	
		this.login = login;
	}

	
	public WebElement getLogout() {
	
		return logout;
	}

	
	public void setLogout(WebElement logout) {
	
		this.logout = logout;
	}

	
	public WebElement getAccount() {
	
		return account;
	}

	
	public void setAccount(WebElement account) {
	
		this.account = account;
	}

	

}
