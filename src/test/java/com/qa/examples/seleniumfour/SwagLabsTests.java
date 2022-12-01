package com.qa.examples.seleniumfour;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SwagLabsTests {

	WebDriver driver;
	String url = "https://www.saucedemo.com/";
	By usernameSelector = By.name("user-name");
	By passwordSelector = By.name("password");
	By errorSelector = By.cssSelector("h3[data-test='error']");
	By loginBtnSelector = By.id("login-button");

	@Before
	public void setup() {
		driver = new ChromeDriver();
		driver.get(url);
		
		Options options = driver.manage();
		Timeouts timeouts = options.timeouts();
		timeouts.implicitlyWait(Duration.ofSeconds(15));
		timeouts.pageLoadTimeout(Duration.ofSeconds(15));
	}

	@After
	public void teardown() {
		// When we use a WebDriver, the resource must be closed when we are done
		driver.quit();
	}
	
	private void login(String username, String password) {
		WebElement usernameInput = driver.findElement(usernameSelector);
		WebElement passwordInput = driver.findElement(passwordSelector);
		WebElement loginBtn = driver.findElement(loginBtnSelector);

		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);

		loginBtn.click();
	}
	
	@Test
	public void successfulLogin() {
		String username = "standard_user";
		String password = "secret_sauce";
		
		login(username, password);
		
		List<WebElement> products = driver.findElements(By.className("inventory_item"));
		
		assertTrue(products.size() > 0);
	}
	
	@Test
	public void lockedOutUserLogin() {
		String username = "locked_out_user";
		String password = "secret_sauce";
		String expected = "Epic sadface: Sorry, this user has been locked out.";
		
		login(username, password);
		
		WebElement error = driver.findElement(errorSelector);
		assertEquals(expected, error.getText());
	}
	
	@Test
	public void wrongPasswordLogin() {
		String username = "problem_user";
		String password = "wrong";
		String expected = "Epic sadface: Username and password do not match any user in this service";
		
		login(username, password);
		
		WebElement error = driver.findElement(errorSelector);
		assertEquals(expected, error.getText());
	}
}
