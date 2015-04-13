package com.jira.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jira.util.Log;

public class LoginPage {

	@FindBy(how = How.ID, using = "username")
	private WebElement usernameField;

	@FindBy(how = How.ID, using = "password")
	private WebElement passwordField;

	@FindBy(how = How.ID, using = "login-submit")
	private WebElement loginButton;

	private final WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		printLog("Load page: '" + this.driver.getTitle() + "' - Done");
	}

	public void fillUsername(String username) {
		usernameField.sendKeys(username);
	}

	public void fillPassword(String password) {
		passwordField.sendKeys(password);
	}

	public DashBoardPage login(String username, String password) {
		fillUsername(username);
		fillPassword(password);
		loginButton.submit();
		// Wait 10 seconds until Dashboar page has been loaded complete
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().startsWith("system dashboar");
			}
		});
		printLog("Login successfull !");
		DashBoardPage dashboardPage = PageFactory.initElements(driver,
				DashBoardPage.class);
		return dashboardPage;
	}

	private void printLog(String content) {
		Log.printLog(content);
	}
}
