package org.netlife.webTestScripts;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.netlife.Base.Base;
import org.netlife.assertions.Assertion;
import org.netlife.utilities.reportCapture;
import org.netlife.utilities.screenshotCapture;
import org.netlife.webPages.loginPage;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TC4 extends Base{

	Boolean result = true;
	
	@Test(groups = {"web"})
	public void TC4_definition() throws InterruptedException, IOException {
		
		// loginPage: This test case verifies some strings on Sign in menu.		
		
		List<String> expectedString = Arrays.asList("¿Olvidó usuario o contraseNa?", 
				                                    "Términos de servicio y Política de privacidad.", 
				                                    "Para conocer más de nuestro contenido ingresa a ");
		
		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\WEB_demo\\TC4.jpg";
		String className = "TC4";
		
		ExtentReports rep = reportCapture.handleReport();
		ExtentTest tc4 = rep.startTest(className);
		
		loginPage login = new loginPage(driver_chrome, webproperties);
		List<String> st = login.getStrings();
		
		Integer c = 0;
 
		for (String i : st) {
			String mesg = expectedString.get(c) + " -> is Expected; It is Receiving -> " + i ;
			if (i.equals(expectedString.get(c))) {
				System.out.println(i + " -> OK");
				tc4.log(LogStatus.PASS, mesg );
			}
			else {
				System.out.println(i + " -> FAIL");
				tc4.log(LogStatus.FAIL, mesg );
				this.result = false;
			}
			c++;
		}
		
		Assertion.assertion_2(result, className);
		if (result) {
			tc4.log(LogStatus.PASS, "TC4 - loginPage: This test case verifies some strings on Sign in menu. - is getting passed");
		}
		else {
			tc4.log(LogStatus.FAIL, "TC4 - loginPage: This test case verifies some strings on Sign in menu. - is getting failed");
		}
		screenshotCapture.takeScreenshot(driver_chrome, pathPicture);
		rep.endTest(tc4);
		rep.flush();
	}
}
