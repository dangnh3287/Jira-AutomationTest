package com.jira.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.jira.util.Log;
import com.jira.util.UIComponent;

public class DashBoardPage {

	@FindBy(how = How.ID, using = "quickSearchInput")
	private WebElement quickSearchInput;

	@FindBy(how = How.ID, using = "create_link")
	private WebElement createIssueLink;

	private final WebDriver driver;

	public DashBoardPage(WebDriver driver) {
		this.driver = driver;
		printLog("Load page: '" + this.driver.getTitle() + "' - Done");
	}

	public CreateIssueForm loadCreateIssueForm() {
		createIssueLink.click();
		// Wait 10 seconds until Browse Projects page has been loaded complete
		UIComponent.waitForPageLoadWithStartWithTitle(driver, "create issue",
				30);
		CreateIssueForm createIssueForm = PageFactory.initElements(driver,
				CreateIssueForm.class);
		return createIssueForm;
	}

	public SearchResultPage searchIssue(String issueTitle) {
		quickSearchInput.sendKeys(issueTitle);
		quickSearchInput.submit();
		try {
			UIComponent
					.waitForPageLoadWithContainsTitle(driver, issueTitle, 10);
			printLog("Search issue with title : '" + issueTitle
					+ "' successful");
		} catch (Exception ex) {
			printLog("No issues were found to match search with : '"
					+ issueTitle + "'");
		}

		UIComponent.waitForPageLoadWithComponentIdDisplay(driver,
				"issue-content", 10);
		printLog("Load edit form successfull.");
		SearchResultPage searchResultPage = PageFactory.initElements(driver,
				SearchResultPage.class);

		return searchResultPage;
	}

	private void printLog(String content) {
		Log.printLog(content);
	}
}
