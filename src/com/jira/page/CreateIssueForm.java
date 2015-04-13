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

public class CreateIssueForm {
	private final WebDriver driver;

	@FindBy(how = How.ID, using = "summary")
	private WebElement summaryField;

	@FindBy(how = How.ID, using = "issuetype-field")
	private WebElement typeField;

	@FindBy(how = How.ID, using = "security")
	private WebElement securityField;

	@FindBy(how = How.ID, using = "priority-field")
	private WebElement priorityTextField;

	@FindBy(how = How.ID, using = "duedate")
	private WebElement dueDateField;

	@FindBy(how = How.ID, using = "components-textarea")
	private WebElement commponentsField;

	@FindBy(how = How.ID, using = "versions-textarea")
	private WebElement affectsVersionField;

	@FindBy(how = How.ID, using = "fixVersions-textarea")
	private WebElement fixVersionsField;

	@FindBy(how = How.ID, using = "assignee-field")
	private WebElement assigneeField;

	@FindBy(how = How.ID, using = "environment")
	private WebElement environmentField;

	@FindBy(how = How.ID, using = "description")
	private WebElement descriptionField;

	@FindBy(how = How.ID, using = "timetracking")
	private WebElement timetrackingField;

	@FindBy(how = How.ID, using = "labels-textarea")
	private WebElement labelField;

	@FindBy(how = How.ID, using = "labels-suggestions")
	private WebElement labelSuggestions;

	@FindBy(how = How.ID, using = "create-issue-submit")
	private WebElement createButton;

	@FindBy(how = How.ID, using = "cancel")
	private WebElement cancelLink;

	public CreateIssueForm(WebDriver driver) {
		this.driver = driver;
		printLog("Load form: '" + this.driver.getTitle() + "' - Done");
	}

	public void fillSummary(String summary) {
		Date date = new Date();
		summary = summary + date.getTime();
		Config.setNewIssueTitle(summary);
		UIComponent.waitForClikable(driver, summaryField);
		summaryField.sendKeys(summary);
	}

	public void selectType() {
		typeField.click();
		printLog("Begin choose type");
		UIComponent.selectSuggestionItem(driver, "issuetype-suggestions");
	}

	public void selectSercurity() {
		UIComponent.selectDropDownBoxItemByIndex(securityField, 1);
	}

	public void selectPriority() {
		priorityTextField.click();
		printLog("Begin choose priority");
		UIComponent.selectSuggestionItem(driver, "priority-suggestions");
	}

	public void fillDueDate(String dueDate) {
		dueDateField.sendKeys(dueDate);
	}

	public void selectComponent(String component) {
		commponentsField.sendKeys(component);
		printLog("Begin choose priority");
		UIComponent.selectSuggestionItem(driver, "components-suggestions");
	}

	public void selectAffectsVersion(String affectsVersion) {
		affectsVersionField.sendKeys(affectsVersion);
		printLog("Begin choose affects version");
		UIComponent.selectSuggestionItem(driver, "versions-suggestions");
	}

	public void selectFixVersion(String fixVersion) {
		fixVersionsField.sendKeys(fixVersion);
		printLog("Begin choose fix version");
		UIComponent.selectSuggestionItem(driver, "fixVersions-suggestions");
	}

	public void selectAssignee(String assign) {
		assigneeField.clear();
		assigneeField.sendKeys(assign);
		UIComponent.waitForClikable(driver, assigneeField);
		printLog("Begin choose assignee");
		UIComponent.selectSuggestionItem(driver, "assignee-suggestions");
	}

	public void fillEnvironment(String environment) {
		environmentField.sendKeys(environment);
	}

	public void fillDescription(String description) {
		descriptionField.sendKeys(description);
	}

	public void fillTimeTracking(String timeTracking) {
		timetrackingField.sendKeys(timeTracking);
	}

	public void selectLabel(String label) {
		labelField.clear();
		labelField.sendKeys(label);
		UIComponent.waitForVisible(driver, labelSuggestions);
		printLog("Begin choose label");
		UIComponent.selectSuggestionItem(driver, "labels-suggestions");
	}

	public void fillIssueData() {
		selectType();
		fillSummary(Config.issueSummary);
		selectSercurity();
		selectPriority();
		fillDueDate(Config.issueDueDate);
		selectComponent(Config.issueComponentText);
		selectAffectsVersion(Config.issueAffectsVersionText);
		selectFixVersion(Config.issueFixVersionText);
		selectAssignee(Config.issueAssignToText);
		fillEnvironment(Config.issueEnvironment);
		fillDescription(Config.issueDescription);
		fillTimeTracking(Config.issueTimeTracking);
		selectLabel(Config.issueLabelText);
	}

	public DashBoardPage createIssue() {
		createButton.submit();
		UIComponent.waitForPageLoadWithStartWithTitle(driver,
				"system dashboard", 60);
		UIComponent.waitForPageLoadWithComponentId(driver,
				"aui-flag-container", 60);
		DashBoardPage dashBoardPage = PageFactory.initElements(driver,
				DashBoardPage.class);
		printLog("New issue created successfull with title : '"
				+ Config.getNewIssueTitle() + "'");
		return dashBoardPage;
	}

	private static void printLog(String content) {
		Log.printLog(content);
	}
}
