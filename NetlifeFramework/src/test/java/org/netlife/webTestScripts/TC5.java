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

public class TC5 extends Base{

	
	@Test(groups = {"web"})
	public void TC5_definition() throws InterruptedException, IOException {
		// inicialPopupPage: This test case takes the header of the initial Popup.
		
		String expectedTxt = "Configuración de privacidad";
		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\WEB_demo\\TC5.jpg";
		String className = "TC5";
		
		ExtentReports rep = reportCapture.handleReport();
		ExtentTest tc5 = rep.startTest(className);
		
		loginPage login = new loginPage(driver_chrome, webproperties);
		login.signIn(username, passwd);
		Thread.sleep(2000);
		
		inicialPopupPage pop = new inicialPopupPage(driver_chrome, webproperties);
		String txt = pop.getPopupHeader();
		System.out.println(txt);
		
		Boolean result = Assertion.assertion_1(txt, expectedTxt, className);
		if (result) {
			tc5.log(LogStatus.PASS, "TC5 - inicialPopupPage: This test case takes the header of the initial Popup. - is getting passed");
		}
		else {
			tc5.log(LogStatus.FAIL, "TC5 - inicialPopupPage: This test case takes the header of the initial Popup. - is getting failed");
		}
		screenshotCapture.takeScreenshot(driver_chrome, pathPicture);
		rep.endTest(tc5);
		rep.flush();
	
	}
}
