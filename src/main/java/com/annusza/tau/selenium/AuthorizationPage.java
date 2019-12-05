package com.annusza.tau.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthorizationPage extends WebDriverForTesting {

	public AuthorizationPage(WebDriver driver) {

		super(driver);
	}

	@FindBy(id = "email_create")
	private WebElement emailCreate;

	@FindBy(id = "SubmitCreate")
	private WebElement submitCreate;

	@FindBy(xpath = "//*[@id=\"create_account_error\"]")
	private WebElement createAccountError;

	@FindBy(css = ".alert-danger")
	private WebElement createAccountErrTxt;

	@FindBy(id = "email")
	private WebElement email;

	@FindBy(id = "passwd")
	private WebElement password;

	@FindBy(id = "SubmitLogin")
	private WebElement submitLogin;

	public void submit(String email) {

		emailCreate.clear();
		emailCreate.sendKeys(email);
		submitCreate.click();
	}
	
	

	public WebElement getEmail() {
		return email;
	}
	public WebElement getCreateAccountError() {

		return createAccountError;
	}

	public boolean isErrorMessage(String errorMessage) {

		return createAccountError.getText().contains(errorMessage);
	}
}
