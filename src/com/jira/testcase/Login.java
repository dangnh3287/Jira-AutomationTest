package com.jira.testcase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.jira.page.DashBoardPage;
import com.jira.page.LoginPage;
import com.jira.util.Config;

public class Login {
	public static DashBoardPage login(WebDriver driver){
		driver.get(Config.loginPageUrl);
		
		//Login and init DashboardPage Object
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		DashBoardPage dashboardPage = loginPage.login(Config.testUsername, Config.testPassword);
		return dashboardPage;
	}
}
