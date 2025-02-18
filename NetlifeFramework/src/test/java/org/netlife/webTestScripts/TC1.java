package org.netlife.webTestScripts;

import java.io.IOException;

import org.netlife.Base.Base;
import org.netlife.assertions.Assertion;
import org.netlife.utilities.reportCapture;
import org.netlife.utilities.screenshotCapture;
import org.netlife.webPages.loginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TC1 extends Base{

	@Test(groups = {"web"}, dataProvider = "dp1")
	public void TC1_definition(String s1, String s2) throws InterruptedException, IOException {
		
		// loginPage: This test case login with two accounts(runs twice).	
		
		String expectedUrl = "https://web-stag.hispasatprod.opentv.com/discover";
		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\WEB_demo\\TC1.jpg";
		String className = "TC1";
		
		ExtentReports rep = reportCapture.handleReport();
		ExtentTest tc1 = rep.startTest(className);
		
		loginPage login = new loginPage(driver_chrome, webproperties);
		login.signIn(s1, s2);
		Thread.sleep(2000);
		
		Boolean result = Assertion.assertion_1(driver_chrome.getCurrentUrl(), expectedUrl, className);
		if (result) {
			tc1.log(LogStatus.PASS, "TC1 - loginPage: This test case login with two accounts(runs twice). - is getting passed");
		}
		else {
			tc1.log(LogStatus.FAIL, "TC1 - loginPage: This test case login with two accounts(runs twice). - is getting failed");
		}
		screenshotCapture.takeScreenshot(driver_chrome, pathPicture);
		rep.endTest(tc1);
		rep.flush();
	}
	
	@DataProvider
	public Object[][] dp1(){
		Object[][] ob = new Object[2][2];
		ob[0][0] = "NC1";
		ob[0][1] = "123";
		ob[1][0] = "NS1";
		ob[1][1] = "123";
		return ob;
	}
	
}


//String txt = driver_chrome.findElement(By.xpath(webproperties.getProperty("cookiepopup"))).getText();
//String expectedpopup = "Es molestoso, lo sabemos, ¡pero tienes que leer esto!";