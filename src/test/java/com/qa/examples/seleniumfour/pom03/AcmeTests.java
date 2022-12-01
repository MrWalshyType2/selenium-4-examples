package com.qa.examples.seleniumfour.pom03;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.support.PageFactory;

import com.qa.examples.seleniumfour.pom03.pages.AcmeLoginPage;
import com.qa.examples.seleniumfour.pom03.pages.nofactory.AcmeLoginPageNoPageFactory;
import com.qa.examples.seleniumfour.utilities.ScreenshotManager;
import com.qa.examples.seleniumfour.utilities.WebDriverFactory;

public class AcmeTests {
	private WebDriver driver;
	private ScreenshotManager screenshotManager;
	private AcmeLoginPageNoPageFactory acmeLoginPageNoPageFactory;
	private AcmeLoginPage acmeLoginPage;

	@Before
	public void setup() throws Exception {
		driver = WebDriverFactory.getDriver();
		screenshotManager = new ScreenshotManager();
		Window window = driver.manage().window();
		Timeouts timeouts = driver.manage().timeouts();
		window.maximize();
		timeouts.pageLoadTimeout(Duration.ofSeconds(30));
		timeouts.implicitlyWait(Duration.ofSeconds(3));
		acmeLoginPageNoPageFactory = new AcmeLoginPageNoPageFactory(driver);
		acmeLoginPage = PageFactory.initElements(driver, AcmeLoginPage.class);
	}

//	@Ignore
	@Test
	public void acmeLoginWithoutPageFactoryTest() throws InterruptedException, IOException {
		String expectedUsername = "Jack Gomez";
		String actualUsername = acmeLoginPageNoPageFactory.login("Jack", "password")
													      .getLoggedInUsername();
		screenshotManager.takeAndSaveScreenshot(driver, "./login-result.png");
		assertEquals(expectedUsername, actualUsername);
	}
	
	@Ignore
	@Test
	public void acmeLoginWithPageFactoryTest() throws IOException {
		String expectedUsername = "Jack Gomez";
		String actualUsername = acmeLoginPage.login("Jack", "password")
											 .getLoggedInUsername();
		screenshotManager.takeAndSaveScreenshot(driver, "./login-result.png");
		assertEquals(expectedUsername, actualUsername);
	}

	@After
	public void teardown() {
		// prevents driver resources from being left open when the test finishes
		driver.quit();
	}
}
