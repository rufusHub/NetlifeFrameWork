package org.netlife.mobTestScripts;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.netlife.Base.Base;
import org.netlife.mobPages.loginPage;
import org.netlife.mobPages.mainMenuPage;
import org.netlife.utilities.ElementUtils;
import org.netlife.utilities.reportCapture;
import org.netlife.utilities.screenshotCapture;
import org.netlife.utilities.scrollToElement;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumBy;

public class MainPageTestCases extends Base{

	
	@Test(groups = {"mobile"})
	public void mainMenu() throws IOException {
			
			// MainMenu: This test case verifies "INICIO" screen and "Main" menu strings.
			
			String testCaseName = "MainMenu__Validate_Strings";
			String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\MOB_demo\\"+testCaseName+".jpg";
			
			ExtentReports rep = reportCapture.handleReportMob();
			ExtentTest tc1 = rep.startTest(testCaseName);
			
			String list = mobileproperties.getProperty("list_string_main");
			String[] expectedString = list.split(";");
			
			try {
			
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
				
				String t1 = ElementUtils.waitForElement(driver_android, timeout_2, driver -> main.main_inicio()).getText();
				String t2 = ElementUtils.waitForElement(driver_android, timeout_2, driver -> main.main_vivo()).getText();
				String t3 = ElementUtils.waitForElement(driver_android, timeout_2, driver -> main.main_guia()).getText();
				String t4 = ElementUtils.waitForElement(driver_android, timeout_2, driver -> main.main_contenidos()).getText();
				String t5 = ElementUtils.waitForElement(driver_android, timeout_2, driver -> main.main_buscar()).getText();
				String t6 = ElementUtils.waitForElement(driver_android, timeout_2, driver -> main.main_config()).getText();
	
				List<String> st = Arrays.asList(t1,t2,t3,t4,t5,t6);
				
				Integer c = 0;
				
			    // Check if the number of options matches the number of expected strings
			    if (st.size() != expectedString.length) {
			        throw new Exception("The number of options does not match the number of expected strings.");
			    }
				
				for (String i : st) {
					String mesg = "[Expected] :: " + expectedString[c] + " --> " + i + " :: [Received]" ;
					if (i.equals(expectedString[c])) {
						System.out.println(i + " -> OK");
						tc1.log(LogStatus.PASS, mesg );
					}
					else {
						System.out.println(i + " -> FAIL");
						tc1.log(LogStatus.FAIL, mesg );
					}
					c++;
				}
			
			} catch(Exception e) {
				String msn = "THE TEST \"" + testCaseName + "\" DID NOT EXCECUTE!";
				System.out.println(msn);
				tc1.log(LogStatus.FATAL, msn );
				e.printStackTrace();
			}
				
			screenshotCapture.takeScreenshotMob(driver_android, pathPicture);
			
			rep.endTest(tc1);
			rep.flush();

	}
		
		
	
}
