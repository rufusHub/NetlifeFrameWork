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

public class TC6 extends Base{

	@Test(groups = {"web"})
	public void TC6_definition() throws InterruptedException, IOException {
		
		// loginPage: This test case try to login with WRONG account.		
		
		String expectedMsg = "Nombre de usuario o contraseña incorrectos. Por favor, inténtelo de nuevo.";
		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\WEB_demo\\TC6.jpg";
		String className = "TC6";
		
		ExtentReports rep = reportCapture.handleReport();
		ExtentTest tc6 = rep.startTest(className);
		
		loginPage loginPagerror = new loginPage(driver_chrome, webproperties);
		String txt = loginPagerror.signonerror(wronguser, wrongpass);
		Thread.sleep(2000);
		
		Boolean result = Assertion.assertion_1(txt, expectedMsg, className);
		if (result) {
			tc6.log(LogStatus.PASS, "TC6 - loginPage: This test case try to login with WRONG account. - is getting passed");
		}
		else {
			tc6.log(LogStatus.FAIL, "TC6 - loginPage: This test case try to login with WRONG account. - is getting failed");
		}
		screenshotCapture.takeScreenshot(driver_chrome, pathPicture);
		rep.endTest(tc6);
		rep.flush();
	}	
}
