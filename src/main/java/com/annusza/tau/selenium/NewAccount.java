package com.annusza.tau.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class NewAccount extends WebDriverForTesting {

	public NewAccount(WebDriver driver) {

		super(driver);
	}

	@FindBy(id = "id_gender1")
	private WebElement heButton;

	@FindBy(id = "id_gender2")
	private WebElement sheButton;

	@FindBy(id = "customer_firstname")
	private WebElement firstName;

	@FindBy(id = "customer_lastname")
	private WebElement lastName;

	@FindBy(id = "email")
	private WebElement email;

	@FindBy(id = "passwd")
	private WebElement password;

	@FindBy(id = "address1")
	private WebElement address;

	@FindBy(id = "city")
	private WebElement city;

	@FindBy(id = "id_state")
	private WebElement idState;

	@FindBy(id = "postcode")
	private WebElement postCode;

	@FindBy(id = "id_country")
	private WebElement idCountry;

	@FindBy(id = "phone_mobile")
	private WebElement phone;

	@FindBy(id = "submitAccount")
	private WebElement submitAccount;

	@FindBy(css = ".alert-danger")
	private WebElement alertDanger;

	public WebElement getHeButton() {

		return heButton;
	}

	public void setFirstName(String firstName) {

		this.firstName.sendKeys(firstName);
	}

	public void setLastName(String lastName) {

		this.lastName.sendKeys(lastName);
	}

	public void setEmail(String email) {

		this.email.sendKeys(email);
	}

	public void setPassword(String password) {

		this.password.sendKeys(password);
	}

	public void setAddress(String address) {

		this.address.sendKeys(address);
	}

	public void setCity(String city) {

		this.city.sendKeys(city);
	}

	public void setIdState(String idState) {

		new Select(this.idState).selectByVisibleText(idState);
	}

	public void setPostCode(String postCode) {

		this.postCode.sendKeys(postCode);
	}

	public void setIdCountry(String idCountry) {

		new Select(this.idCountry).selectByVisibleText(idCountry);
	}

	public void setPhone(String phone) {

		this.phone.sendKeys(phone);
	}

	public void submitAccount() {

		submitAccount.click();
	}

	public boolean alertDangerTxt(String alertDangerTxt) {

		return alertDanger.getText().contains(alertDangerTxt);
	}

}
