package com.annusza.tau.test.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.*;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import com.annusza.tau.selenium.*;

public class AutomationPracticeTest {

	private WebDriver driver;
	long time = System.currentTimeMillis();
	private String email = "kwakwa" + time + "@hauhau.com";
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", "E:/Selenium Java/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		// baseUrl = "http://automationpractice.com";
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://automationpractice.com/index.php");
	}

	@Test
	public void fieldValidationTest() {

		loginClick();

		AuthorizationPage authorizationPage = new AuthorizationPage(driver);
		authorizationPage.submit("miauuuu@hauwau.com");

		NewAccount newAccount = new NewAccount(driver);
		newAccount.getHeButton().click();
		newAccount.setFirstName("Dana3");
		newAccount.setLastName("Kralova5");
		newAccount.setPassword("000");
		newAccount.setAddress("");
		newAccount.setCity("@@@");
		newAccount.setIdState("-");
		newAccount.setPostCode("000");
		newAccount.setPhone("errrr");
		newAccount.submitAccount();

		Assert.assertTrue(newAccount.alertDangerTxt("firstname is invalid."));
		Assert.assertTrue(newAccount.alertDangerTxt("lastname is invalid."));
		Assert.assertTrue(newAccount.alertDangerTxt("passwd is invalid."));
		Assert.assertTrue(newAccount.alertDangerTxt("address1 is required."));
		Assert.assertTrue(newAccount.alertDangerTxt("city is invalid."));
		Assert.assertTrue(newAccount.alertDangerTxt("This country requires you to choose a State."));
		Assert.assertTrue(newAccount.alertDangerTxt("The Zip/Postal code you've entered is invalid. It must follow this format: 00000"));
		Assert.assertTrue(newAccount.alertDangerTxt("phone_mobile is invalid."));
	}

	@Test
	public void successfulRegistrationTest() throws InterruptedException {

		loginClick();
		authorized();

		UserPage userPage = new UserPage(driver);
		Assert.assertTrue(userPage.getUserMessage().isDisplayed());

		// cookies
		// driver.manage().deleteAllCookies();

	}

	@Test
	public void failedRegisterTest() throws InterruptedException {

		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		WebElement emailInput = driver.findElement(By.id("email_create"));
		emailInput.clear();
		emailInput.sendKeys("123@com.pl");
		driver.findElement(By.id("SubmitCreate")).click();
		driver.findElement(By.id("submitAccount")).click();

		boolean errorFound;

		try {
			driver.findElement(By.className("alert-danger"));
			errorFound = true;
		} catch (NoSuchElementException e) {
			errorFound = false;

		}

		assertEquals("Registration failed", true, errorFound);

	}

	@Test
	public void succesfulRegistrationWithDimension800Test() throws InterruptedException {

		driver.manage().window().setSize(new Dimension(480, 800));
		loginClick();
		authorized();

		UserPage userPage = new UserPage(driver);
		Thread.sleep(5000);
		Assert.assertTrue(userPage.getUserMessage().isDisplayed());

	}

	@Test
	public void succesfulRegistrationWithDimension1024Test() throws InterruptedException {

		driver.manage().window().setSize(new Dimension(768, 1024));
		loginClick();
		authorized();

		UserPage userPage = new UserPage(driver);
		Thread.sleep(5000);
		Assert.assertTrue(userPage.getUserMessage().isDisplayed());

	}

	private void loginClick() {

		IndexHtmlPage indexHtmlPage = new IndexHtmlPage(driver);
		indexHtmlPage.getLogin().click();
	}

	private void authorized() {

		AuthorizationPage authorizationPage = new AuthorizationPage(driver);
		authorizationPage.submit(email);

		NewAccount newAccount = new NewAccount(driver);
		newAccount.getHeButton().click();
		newAccount.setFirstName("Dana");
		newAccount.setLastName("Kralova");
		newAccount.setPassword("Qwerty");
		newAccount.setAddress("ul. Zapiecek 15");
		newAccount.setCity("Ghibli");
		newAccount.setIdState("Florida");
		newAccount.setPostCode("77777");
		newAccount.setIdCountry("United States");
		newAccount.setPhone("1234567890");
		newAccount.submitAccount();
	}

	private boolean isElementPresent(By by) {

		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {

		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {

		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}

	@After
	public void deleteCookies() {

		driver.manage().deleteAllCookies();
		driver.quit();
	}

	public void tearDown() throws Exception {

		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
