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
			
			List<WebElement> st = main.config_all_options();
			
		    // Check if the number of options matches the number of expected strings
		    if (st.size() != expectedString.length) {
		        throw new Exception("The number of options does not match the number of expected strings.");
		    }
			
			int counter = 0;

			for (String expected : expectedString) {
			    if (counter >= st.size()) {
			        System.out.println("Index out of bounds for optionsList.");
			        break;
			    }

			    WebElement option = st.get(counter);
			    if (counter == 3) {
			        expected = expected + username;
			        System.out.println(expected);
			    }
			    System.out.println(counter);
			    System.out.println(expected);
			    String message = "[Expected] :: " + expected + " --> " + option.getText() + " :: [Received]";
			    if (option.getText().equals(expected)) {
			        System.out.println(option.getText() + " -> OK");
			        tc1.log(LogStatus.PASS, message);
			    } else {
			        System.out.println(option.getText() + " -> FAIL");
			        tc1.log(LogStatus.FAIL, message);
			    }
			    counter++;
			}

		} catch(Exception e) {
			String msn = testCaseName + " GOT A CRITICAL ERROR, REVIEW ASAP!";
			System.out.println(msn);
			tc1.log(LogStatus.FATAL, msn );
			e.printStackTrace();
		}
		
		screenshotCapture.takeScreenshotMob(driver_android, pathPicture);
		
		rep.endTest(tc1);
		rep.flush();
	}
	
	@Test(groups = {"mobile"})
	public void configGeneral() throws IOException {
		
		// MainMenu: This test case verifies "CONFIGURACIÓN General" strings options.
		
		String testCaseName = "ConfigGeneralMenu__Validate_Strings";
		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\MOB_demo\\"+testCaseName+".jpg";
		
		ExtentReports rep = reportCapture.handleReportMob();
		ExtentTest tc1 = rep.startTest(testCaseName);
		
		try {
			
			String list = mobileproperties.getProperty("list_string_config_gnrl");
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
			ElementUtils.waitForElement(driver_android, timeout_2, driver -> main.config_config_general_button()).click();
			
			List<WebElement> st = main.config_gnrl_resource_id();
			
//			for (WebElement e : st) {
//				System.out.println(e.getText());
//			}
			
			
		    // Check if the number of options matches the number of expected strings
		    if (st.size() != expectedString.length) {
		        throw new Exception("The number of options does not match the number of expected strings.");
		    }
			
			int counter = 0;

			for (String expected : expectedString) {
			    if (counter >= st.size()) {
			        System.out.println("Index out of bounds for optionsList.");
			        break;
			    }

			    WebElement option = st.get(counter);

			    System.out.println(counter);
			    System.out.println(expected);
			    String message = "[Expected] :: " + expected + " --> " + option.getText() + " :: [Received]";
			    if (option.getText().equals(expected)) {
			        System.out.println(option.getText() + " -> OK");
			        tc1.log(LogStatus.PASS, message);
			    } else {
			        System.out.println(option.getText() + " -> FAIL");
			        tc1.log(LogStatus.FAIL, message);
			    }
			    counter++;
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
