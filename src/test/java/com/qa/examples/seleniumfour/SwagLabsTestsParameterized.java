package com.qa.examples.seleniumfour;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class SwagLabsTestsParameterized {
	
	String username;
	String password;
	
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{"standard_user", "secret_sauce"},
			{"problem_user", "secret_sauce"}
		});
	}

	WebDriver driver;
	String url = "https://www.saucedemo.com/";
	By usernameSelector = By.name("user-name");
	By passwordSelector = By.name("password");
	By errorSelector = By.cssSelector("h3[data-test='error']");
	By loginBtnSelector = By.id("login-button");
	
	public SwagLabsTestsParameterized(String username, String password) {
		this.username = username;
		this.password = password;
	}

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
	
	@Test
	public void successfulLogin() {
		WebElement usernameInput = driver.findElement(usernameSelector);
		WebElement passwordInput = driver.findElement(passwordSelector);
		WebElement loginBtn = driver.findElement(loginBtnSelector);

		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);

		loginBtn.click();
		
		List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
		
		
		assertTrue(products.size() > 0);
	}
	
}
