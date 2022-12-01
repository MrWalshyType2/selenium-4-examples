package com.qa.examples.seleniumfour.browsermanipulation01;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;

import com.qa.examples.seleniumfour.utilities.WebDriverFactory;

public class Navigation02 {

		private WebDriver driver;
		
		@Before
		public void setup() throws Exception {
			driver = WebDriverFactory.getDriver();
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
			WebElement agreeBtn = driver.findElement(By.xpath("//button[div[text()='Accept all']]"));
			agreeBtn.click();
		}
		
		@Test
		public void navigationExample() throws InterruptedException {
			Navigation nav = driver.navigate();
			
			// navigating to a page
			nav.to("https://www.google.com");
			agreeToTerms();
			
			// going back a page
			Thread.sleep(2000);
			nav.back();
			
			// going forward a page
			Thread.sleep(2000);
			nav.forward();
			
			// refresh the page
			Thread.sleep(2000);
			nav.refresh();
			
			assertEquals("Google", driver.getTitle());
		}
		
		@After
		public void teardown() {
			// prevents driver resources from being left open when the test finishes
			driver.quit();
		}
}
