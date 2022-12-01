package com.qa.examples.seleniumfour.pom03.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AcmeLoginPage {

	private WebDriver driver;

	@FindBy(id = "username")
	private WebElement usernameInput;
	
	@FindBy(how = How.ID, using = "password")
	private WebElement passwordInput;
	
	@FindBy(id = "log-in")
	private WebElement signinBtn;
	
	WebDriverWait wait;

	
	public AcmeLoginPage(WebDriver driver) {
		this.driver = driver;
		driver.get("https://demo.applitools.com/");
		
		// page load verification
		if (!driver.getTitle().equals("ACME demo app")) {
			throw new IllegalStateException("Page did not load");
		}
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
	
	public AcmeHomePage login(String username, String password) {
		Actions loginActions = new Actions(driver);

		loginActions.sendKeys(wait.until(ExpectedConditions.visibilityOf(usernameInput)), username)
				    .sendKeys(passwordInput, password)
				    .click(signinBtn);
		Action loginAction = loginActions.build();
		loginAction.perform();

		return PageFactory.initElements(driver, AcmeHomePage.class);
	}

}
