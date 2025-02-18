package org.netlife.webTestScripts;

import java.io.IOException;

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

public class TC8 extends Base{

	String txt;

	@Test(groups = {"web"})
	public void TC8_definition() throws InterruptedException, IOException {
		// inicialPopupPage: This test case selects ACEPTAR to the initial Popup.
		
		String expectedTxt = "No popup found";
		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\WEB_demo\\TC8.jpg";
		String className = "TC8";
		
		ExtentReports rep = reportCapture.handleReport();
		ExtentTest tc8 = rep.startTest(className);
		
		loginPage login = new loginPage(driver_chrome, webproperties);
		login.signIn(username, passwd);
		Thread.sleep(2000);
		
		inicialPopupPage clickaccept = new inicialPopupPage(driver_chrome, webproperties);
		clickaccept.clickAceptarTodo();
		Thread.sleep(2000);
		try {
			this.txt = clickaccept.getPopupHeader();
		}
		catch(Exception e){
			this.txt = "No popup found";
		}
		
		Boolean result = Assertion.assertion_1(this.txt, expectedTxt, className);
		if (result) {
			tc8.log(LogStatus.PASS, "TC8 - inicialPopupPage: This test case selects ACEPTAR to the initial Popup. - is getting passed");
		}
		else {
			tc8.log(LogStatus.FAIL, "TC8 - inicialPopupPage: This test case selects ACEPTAR to the initial Popup. - is getting failed");
		}
		screenshotCapture.takeScreenshot(driver_chrome, pathPicture);
		rep.endTest(tc8);
		rep.flush();
	
	}
}
