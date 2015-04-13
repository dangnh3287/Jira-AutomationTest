package com.jira.page;

import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.jira.util.Config;
import com.jira.util.Log;
import com.jira.util.UIComponent;

public class EditIssueForm {
	private final WebDriver driver;

	@FindBy(how = How.ID, using = "environment")
	private WebElement environmentField;

	@FindBy(how = How.ID, using = "description")
	private WebElement descriptionField;

	@FindBy(how = How.ID, using = "edit-issue-submit")
	private WebElement updateButton;

	public EditIssueForm(WebDriver driver) {
		this.driver = driver;
		printLog("Load form: '" + this.driver.getTitle() + "' - Done");
	}

	public void fillEnvironment(String environment) {
		environmentField.clear();
		environmentField.sendKeys(environment);
		printLog("Update environment to : " + environment);
	}

	public void fillDescription(String description) {
		descriptionField.clear();
		descriptionField.sendKeys(description);
		printLog("Update description to : " + description);
	}

	public void fillIssueData() {
		Date date = new Date();
		String editIssueKeyword = "-Edited in " + date.getTime();
		Config.setEditIssueKeyword(editIssueKeyword);
		fillEnvironment(Config.issueEnvironment + Config.getEditIssueKeyword());
		fillDescription(Config.issueDescription + Config.getEditIssueKeyword());
	}

	public SearchResultPage editIssue() {
		updateButton.submit();
		UIComponent.waitForPageLoadWithContainsTitle(driver, Config
				.getNewIssueTitle().toLowerCase(), 60);
		UIComponent.waitForPageLoadWithComponentId(driver,
				"aui-flag-container", 30);
		SearchResultPage searchResultPage = PageFactory.initElements(driver,
				SearchResultPage.class);
		printLog("Issue edit successfull. Begin check edit data.");
		UIComponent.waitForPageLoadWithComponentIdDisplay(driver,
				"issue-content", 20);
		printLog("Load issue data form successfull.");
		if (searchResultPage.checkEditData()) {
			printLog("Check edit data : Pass");
		} else {
			printLog("Check edit data : Fail");
		}
		return searchResultPage;
	}

	private static void printLog(String content) {
		Log.printLog(content);
	}
}
