package com.qa.examples.seleniumfour.pom03.pages.nofactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RedditLoginPage {
	
	private WebDriver driver;
	private By usernameInputSelector = By.name("username");

	public RedditLoginPage(WebDriver driver) {
		this.driver = driver;
		
		WebElement frame = driver.findElement(By.xpath("//iframe[contains(@src, 'https://www.reddit.com/login')]"));
		driver.switchTo()
			  .frame(frame);
	}
	
	public void login(String username, String password) {
		driver.findElement(usernameInputSelector)
			  .sendKeys(username);
		driver.switchTo().parentFrame();
	}
	
	
}