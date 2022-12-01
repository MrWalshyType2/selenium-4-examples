package com.qa.examples.seleniumfour.pom03.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AcmeLoginPage {

	private WebDriver driver;

	@FindBy(id = "username")
	private WebElement usernameInput;
	
	@FindBy(how = How.ID, using = "password")
	private WebElement passwordInput;
	
	@FindBy(id = "log-in")
	private WebElement signinBtn;
	
	public AcmeLoginPage(WebDriver driver) {
		this.driver = driver;
		
		driver.get("https://demo.applitools.com/");
		
		// page load verification
		if (!driver.getTitle().equals("ACME demo app")) {
			throw new IllegalStateException("Page did not load");
		}
	}
	
	public AcmeHomePage login(String username, String password) {
		Actions loginAction = new Actions(driver);
		loginAction.sendKeys(usernameInput, username)
				   .sendKeys(passwordInput, password)
				   .click(signinBtn)
				   .perform();
		
		return PageFactory.initElements(driver, AcmeHomePage.class);
	}
}
