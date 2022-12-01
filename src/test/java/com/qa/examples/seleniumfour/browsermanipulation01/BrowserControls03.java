package com.qa.examples.seleniumfour.browsermanipulation01;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;

import com.qa.examples.seleniumfour.utilities.ScreenshotManager;
import com.qa.examples.seleniumfour.utilities.WebDriverFactory;

public class BrowserControls03 {

	private WebDriver driver;
	private ScreenshotManager screenshotManager;
	
	@Before
	public void setup() throws Exception {
		driver = WebDriverFactory.getDriver();
		screenshotManager = new ScreenshotManager();
		Window window = driver.manage()
							  .window();
		Timeouts timeouts = driver.manage()
								  .timeouts();
		window.maximize();
		timeouts.pageLoadTimeout(Duration.ofSeconds(30));
	}
	
	/**
	 * Use to agree to the terms and conditions form when first navigating to the Google search page.
	 */
	public void agreeToTerms() {
		WebElement agreeBtn = driver.findElement(By.xpath("//button[div[text()='I agree']]"));
		agreeBtn.click();
	}
	
	@Ignore
	// newWindow API
	@Test
	public void newTabExample() throws InterruptedException {
		String originalTab = driver.getWindowHandle(); // returns the identifier for the curent window
		driver.get("https://www.google.com");
		Thread.sleep(3000);
		
		// switch to a new "window"
		driver.switchTo()
			  .newWindow(WindowType.TAB);
		driver.get("https://www.bing.com");
		
		// close the current "window"
		Thread.sleep(3000);
		driver.close();
		
		// return control to the original tab
		driver.switchTo()
			  .window(originalTab);
		
		assertEquals("Google", driver.getTitle());
	}
	
	@Ignore
	@Test
	public void newBrowserWindowExample() throws InterruptedException {
		String originalWindow = driver.getWindowHandle();
		driver.get("https://www.google.com");
		Thread.sleep(2000);

		// switch to new browser window
		driver.switchTo()
			  .newWindow(WindowType.WINDOW);
		driver.get("https://www.bing.com");
		Thread.sleep(2000);
		
		// close new window
		driver.close();
		
		// give control back to original window
		driver.switchTo()
			  .window(originalWindow);
		Thread.sleep(2000);

		assertEquals("Google", driver.getTitle());
	}
	
	// Window API
	// - used for managing browser windows
//	@Ignore
	@Test
	public void resizeExample() throws IOException {
		// retrieve the window object from the options object on a web driver
		Window window = driver.manage()
							  .window();
		driver.get("https://github.com/");
		// get current size of window, resize to new specified dimensions and take screenshots
		Dimension defaultWindowSize = window.getSize();
		Dimension mobileWindowSize = new Dimension(479, 850); // 479x850
		Dimension tabletWindowSize = new Dimension(767, 1024); // 767x1024
		Dimension desktopWindowSize = new Dimension(1024, 720); // 1024x720
		
		// mobile
		window.setSize(mobileWindowSize);
		screenshotManager.takeAndSaveScreenshot(driver, "./target/screenshots/mobile-view.png");
		
		// tablet
		window.setSize(tabletWindowSize);
		screenshotManager.takeAndSaveScreenshot(driver, "./target/screenshots/tablet-view.png");
		
		// desktop
		window.setSize(desktopWindowSize);
		screenshotManager.takeAndSaveScreenshot(driver, "./target/screenshots/desktop-view.png");
	}
	
	@After
	public void teardown() {
		// prevents driver resources from being left open when the test finishes
		driver.quit();
	}
}
