package org.netlife.webTestScripts;

import java.io.IOException;

import org.netlife.Base.Base;
import org.netlife.assertions.Assertion;
import org.netlife.utilities.reportCapture;
import org.netlife.utilities.screenshotCapture;
import org.netlife.webPages.loginPage;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TC1 extends Base{

	@Test(groups = {"web"})
	public void TC1_definition() throws InterruptedException, IOException {
		
		// loginPage: This test case login with a specific account.		
		
		String expectedUrl = "https://web-stag.hispasatprod.opentv.com/discover";
		String pathPicture = "/home/rufo/logs/netlifeWebAutomated/TC1.jpg";
		String className = "TC1";
		
		ExtentReports rep = reportCapture.handleReport();
		ExtentTest tc1 = rep.startTest(className);
		
		loginPage login = new loginPage(driver_chrome, webproperties);
		login.signIn(username, passwd);
		Thread.sleep(5000);
		
		Boolean result = Assertion.assertion_1(driver_chrome.getCurrentUrl(), expectedUrl, className);
		if (result) {
			tc1.log(LogStatus.PASS, "TC1 is getting passed");
		}
		else {
			tc1.log(LogStatus.FAIL, "TC1 is getting failed");
		}
		screenshotCapture.takeScreenshot(driver_chrome, pathPicture);
		rep.endTest(tc1);
		rep.flush();
		
		
		
	}
	
}


//String txt = driver_chrome.findElement(By.xpath(webproperties.getProperty("cookiepopup"))).getText();
//String expectedpopup = "Es molestoso, lo sabemos, ¡pero tienes que leer esto!";