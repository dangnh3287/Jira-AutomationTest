import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.jira.page.DashBoardPage;
import com.jira.page.SearchResultPage;
import com.jira.testcase.CreateIssue;
import com.jira.testcase.Login;
import com.jira.testcase.SearchIssue;
import com.jira.testcase.UpdateIssue;
import com.jira.util.Config;


public class TestManager {

	public static WebDriver driver = new FirefoxDriver();
	
	public static void main(String[] args) {
		testFullFlow();
	}
	
	private static void testFullFlow(){
		DashBoardPage dashBoardPage = Login.login(driver);
		dashBoardPage = CreateIssue.runTest(dashBoardPage);
		SearchResultPage searchResultPage = SearchIssue.runTest(dashBoardPage, Config.getNewIssueTitle());
		UpdateIssue.runTest(driver, searchResultPage);
	}
	
	private static void testCreateIssue(){
		DashBoardPage dashBoardPage = Login.login(driver);
		dashBoardPage = CreateIssue.runTest(dashBoardPage);
	}
	
	private static void testSearchIssue(){
		DashBoardPage dashBoardPage = Login.login(driver);
		SearchIssue.runTest(dashBoardPage, Config.issueSuccessToSearch);
	}
	
	private static void testEditIssue(){
		Config.setNewIssueTitle(Config.issueSuccessToSearch);
		DashBoardPage dashBoardPage = Login.login(driver);
		SearchResultPage searchResultPage = SearchIssue.runTest(dashBoardPage, Config.getNewIssueTitle());
		UpdateIssue.runTest(driver, searchResultPage);
	}
}
