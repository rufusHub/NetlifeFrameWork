package org.netlife.mobTestScripts;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.netlife.Base.Base;
import org.netlife.mobPages.loginPage;
import org.netlife.mobPages.mainMenuPage;
import org.netlife.utilities.ElementUtils;
import org.netlife.utilities.reportCapture;
import org.netlife.utilities.screenshotCapture;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ConfigPageTestCases extends Base{

	@Test(groups = {"mobile"})
	public void configMenu() throws IOException {
		
		// MainMenu: This test case verifies "CONFIGURACIÓN" strings options.
		
		String testCaseName = "ConfigMenu__Validate_Strings";
		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\MOB_demo\\"+testCaseName+".jpg";
		
		ExtentReports rep = reportCapture.handleReportMob();
		ExtentTest tc1 = rep.startTest(testCaseName);
		
		try {
		
			String list = mobileproperties.getProperty("list_string_config");
			String[] expectedString = list.split(";");
			
			loginPage login = new loginPage(driver_android, mobileproperties);
			login.usuario().sendKeys(username);
			login.password().sendKeys(passwd);
			login.inicio_sesion().click();
			
			Duration timeout = Duration.ofSeconds(30);
			ElementUtils.waitForElement(driver_android, timeout, driver -> login.idioma_siguiente()).click();
			
			Duration timeout_2 = Duration.ofSeconds(3);
			ElementUtils.waitForElement(driver_android, timeout_2, driver -> login.control_omitir()).click();
			
			mainMenuPage main = new mainMenuPage(driver_android, mobileproperties);
			ElementUtils.waitForElement(driver_android, timeout_2, driver -> main.inicio_button()).click();
			ElementUtils.waitForElement(driver_android, timeout_2, driver -> main.main_config()).click();
			
			String t1 = ElementUtils.waitForElement(driver_android, timeout_2, driver -> main.config_config()).getText(); 
			System.out.println(t1);
			
			List<WebElement> ls = main.config_all_options();
			Integer c = 0;
			for (WebElement e : ls) {
				String exp =  expectedString[c];
				if (c == 3) {
					exp = exp + username;
					System.out.println(exp);
				}
				System.out.println(c);
				System.out.println(exp);
				String mesg = "[Expected] :: " + exp + " --> " + e.getText() + " :: [Received]" ;
				if (e.getText().equals(exp)) {
					System.out.println(e.getText() + " -> OK");
					tc1.log(LogStatus.PASS, mesg );
				}
				else {
					System.out.println(e.getText() + " -> FAIL");
					tc1.log(LogStatus.FAIL, mesg );
				}
				c++;
				
			}
		
		} catch(Exception e) {
			String msn = "THE TEST \"" + testCaseName + "\" DID NOT EXCECUTE!";
			System.out.println(msn);
			tc1.log(LogStatus.FATAL, msn );
		}
		
		screenshotCapture.takeScreenshotMob(driver_android, pathPicture);
		
		rep.endTest(tc1);
		rep.flush();
	}
	
	@Test(groups = {"mobile"})
	public void configGeneral() {
		
		// MainMenu: This test case verifies "CONFIGURACIÓN General" strings options.
	}
}
