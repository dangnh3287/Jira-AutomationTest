package com.jira.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.jira.util.Config;
import com.jira.util.Log;
import com.jira.util.UIComponent;

public class SearchResultPage {
	@FindBy(how = How.XPATH, using = "//a[@class='toolbar-trigger issueaction-edit-issue']")
	public WebElement editIssueButton;

	@FindBy(how = How.XPATH, using = "//div[@id='environment-val']/p")
	public WebElement environmentData;

	@FindBy(how = How.XPATH, using = "//div[@id='description-val']/div[@class='user-content-block']/p")
	public WebElement descriptionData;

	public EditIssueForm loadEditForm(WebDriver driver) {
		printLog("Load form: '" + driver.getTitle() + "' - Done");
		editIssueButton.click();
		UIComponent.waitForPageLoadWithStartWithTitle(driver, "edit issue", 30);
		EditIssueForm editIssueForm = PageFactory.initElements(driver,
				EditIssueForm.class);
		return editIssueForm;
	}

	public boolean checkEditData() {

		boolean result = true;

		String currentEnvironmentData = environmentData.getText();
		String requiredEnvironmentData = Config.issueEnvironment
				+ Config.getEditIssueKeyword();
		printLog("Current environment data : " + currentEnvironmentData);
		printLog("Required environment data : " + requiredEnvironmentData);
		if (!currentEnvironmentData.equals(requiredEnvironmentData)) {
			printLog("Check environment data after edit fail.");
			result = false;
		} else {
			printLog("Check environment data after edit pass.");
		}

		String currentDescriptionData = descriptionData.getText();
		String requiredDescriptionData = Config.issueDescription
				+ Config.getEditIssueKeyword();
		printLog("Current description data : " + currentDescriptionData);
		printLog("Required description data : " + requiredDescriptionData);
		if (!currentDescriptionData.equals(requiredDescriptionData)) {
			printLog("Check description data after edit fail.");
			result = false;
		} else {
			printLog("Check description data after edit pass.");
		}
		return result;
	}

	private static void printLog(String content) {
		Log.printLog(content);
	}
}
