package org.netlife.webTestScripts;

import java.io.IOException;

import org.jsoup.nodes.Element;
import org.netlife.Base.Base;
import org.netlife.assertions.Assertion;
import org.netlife.utilities.reportCapture;
import org.netlife.utilities.screenshotCapture;
import org.netlife.webPages.inicialPopupPage;
import org.netlife.webPages.loginPage;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TC7 extends Base{
	
	String txt;

	@Test(groups = {"web"})
	public void TC7_definition() throws InterruptedException, IOException {
		// inicialPopupPage: This test case selects CLOSE to the initial Popup.
		
		String expectedTxt = "No popup found";
		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\WEB_demo\\TC7.jpg";
		String className = "TC7";
		
		ExtentReports rep = reportCapture.handleReport();
		ExtentTest tc7 = rep.startTest(className);
		
		loginPage login = new loginPage(driver_chrome, webproperties);
		login.signIn(username, passwd);
		Thread.sleep(5000);
		
		inicialPopupPage clickclose = new inicialPopupPage(driver_chrome, webproperties);
		clickclose.clickClose();
		Thread.sleep(2000);
		try {
			this.txt = clickclose.getPopupHeader();
		}
		catch(Exception e){
			this.txt = "No popup found";
		}
		
		Boolean result = Assertion.assertion_1(this.txt, expectedTxt, className);
		if (result) {
			tc7.log(LogStatus.PASS, "TC7 is getting passed");
		}
		else {
			tc7.log(LogStatus.FAIL, "TC7 is getting failed");
		}
		screenshotCapture.takeScreenshot(driver_chrome, pathPicture);
		rep.endTest(tc7);
		rep.flush();
	
	}
}
