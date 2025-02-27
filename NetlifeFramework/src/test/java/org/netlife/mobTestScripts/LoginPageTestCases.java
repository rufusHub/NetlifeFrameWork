package org.netlife.mobTestScripts;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.netlife.Base.Base;
import org.netlife.mobPages.loginPage;
import org.netlife.utilities.ElementUtils;
import org.netlife.utilities.reportCapture;
import org.netlife.utilities.screenshotCapture;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPageTestCases extends Base{
	
	@Test(groups = {"mobile"})
	public void getText() throws IOException {
		
		// LoginPage: This test case verifies some strings in "Sign in" menu.	
		
//		Boolean result = true;
		String testCaseName = "LoginMenu__Login_Screen_strings";
													
		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\MOB_demo\\"+testCaseName+".jpg";
		
		ExtentReports rep = reportCapture.handleReportMob();
		ExtentTest tc1 = rep.startTest(testCaseName);
		
		try {
		
			String list = mobileproperties.getProperty("list_string_login");
			String[] expectedString = list.split(";");
			
			loginPage login = new loginPage(driver_android, mobileproperties);
			String t1 = login.usuario().getText();
			String t2 = login.password().getText();
			String t3 = login.inicio_sesion().getText();
			String t4 = login.olvido_pass().getText();
			String t5 = login.terminos_politicas().getText();
			
			List<String> st = Arrays.asList(t1,t2,t3,t4,t5);
			
			Integer c = 0;
			
			for (String i : st) {
				String mesg = "[Expected] :: " + expectedString[c] + " --> " + i + " :: [Received]" ;
				if (i.equals(expectedString[c])) {
					System.out.println(i + " -> OK");
					tc1.log(LogStatus.PASS, mesg );
				}
				else {
					System.out.println(i + " -> FAIL");
					tc1.log(LogStatus.FAIL, mesg );
	//				result = false;
				}
				c++;
			}
		
		} catch(Exception e) {
			String msn = "THE TEST \"" + testCaseName + "\" DID NOT EXCECUTE!";
			System.out.println(msn);
			tc1.log(LogStatus.FATAL, msn );
		}
		//Assertion.assertion_2(result, testCaseName);
			
		screenshotCapture.takeScreenshotMob(driver_android, pathPicture);
		
		rep.endTest(tc1);
		rep.flush();
		
		
	}
	
	@Test(groups = {"mobile"})
	public void loginSuccess() throws InterruptedException, IOException {
		
		//  LoginPage: This test case login with correct account.
	
		String testCaseName = "LoginMenu__Valid_account";
		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\MOB_demo\\"+testCaseName+".jpg";
		
		ExtentReports rep = reportCapture.handleReportMob();
		ExtentTest tc1 = rep.startTest(testCaseName);
		
		try {
		
			loginPage login = new loginPage(driver_android, mobileproperties);
			login.usuario().sendKeys(username);
			login.password().sendKeys(passwd);
			login.inicio_sesion().click();;
			
			Duration timeout = Duration.ofSeconds(30);
			WebElement con = ElementUtils.waitForElement(driver_android, timeout, driver -> login.idioma_seleccion());
			
			if (con != null) {
			    System.out.println("Element found and visible.");
			    tc1.log(LogStatus.PASS, "This test case login with CORRECT account - PASS");
			} else {
			    System.out.println("Element not found within 20 seconds.");
			    tc1.log(LogStatus.FAIL, "This test case login with CORRECT account - FAIL");
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
	public void loginError() throws InterruptedException, IOException {
		
		// LoginPage: This test login with Incorrect account.
		
		String testCaseName = "LoginMenu__Invalid_account";

		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\MOB_demo\\"+testCaseName+".jpg";
		
		ExtentReports rep = reportCapture.handleReportMob();
		ExtentTest tc1 = rep.startTest(testCaseName);
		
		try {
		
			loginPage login = new loginPage(driver_android, mobileproperties);
			login.usuario().sendKeys(wronguser);
			login.password().sendKeys(wrongpass);
			login.inicio_sesion().click();;
			
			Duration timeout = Duration.ofSeconds(5);
			WebElement con = ElementUtils.waitForElement(driver_android, timeout, driver -> login.sign_error());
			
			if(con != null) {
				System.out.println("Element found and visible.");
				String msg_ok = "[Expected] :: " + con.getText() + " - PASS";
				tc1.log(LogStatus.PASS, msg_ok);
			}
			else {
				System.out.println("Element not found within 20 seconds.");
				tc1.log(LogStatus.FAIL, "This test case tries to login with INCORRECT account. - FAIL");
			}
		
		}  catch(Exception e) {
			String msn = "THE TEST \"" + testCaseName + "\" DID NOT EXCECUTE!";
			System.out.println(msn);
			tc1.log(LogStatus.FATAL, msn );
		}
		
		screenshotCapture.takeScreenshotMob(driver_android, pathPicture);
		
		rep.endTest(tc1);
		rep.flush();
	}
	
	@Test(groups = {"mobile"})
	public void forgetPass() throws IOException {
		
		// LoginPage: This test clicks on "Olvidó Contraseña" and selects "OK".
		
		String testCaseName = "LoginMenu__Forget_Password";

		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\MOB_demo\\"+testCaseName+".jpg";
		
		ExtentReports rep = reportCapture.handleReportMob();
		ExtentTest tc1 = rep.startTest(testCaseName);
		
		try {
		
			loginPage login = new loginPage(driver_android, mobileproperties);
			login.olvido_pass().click();
			
			Duration timeout = Duration.ofSeconds(10);
			WebElement con_1 = ElementUtils.waitForElement(driver_android, timeout, driver -> login.rest_contra_msg_2());
			WebElement con_2 = ElementUtils.waitForElement(driver_android, timeout, driver -> login.rest_contra_ok());
			
			if(con_1 != null && con_2 != null) {
				System.out.println("Elements found and visible.");
				tc1.log(LogStatus.PASS, "This test clicks 'Olvidó Contraseña'. - PASS");
			}
			else {
				System.out.println("Element not found within 5 seconds.");
				tc1.log(LogStatus.FAIL, "This test clicks 'Olvidó Contraseña'. - FAIL");
			}
			
			screenshotCapture.takeScreenshotMob(driver_android, pathPicture);
			
			con_2.click();
			
			Boolean con_3 = login.inicio_sesion().isDisplayed();
			
			if(con_3 != false) {
				System.out.println("Elements found and visible.");
				tc1.log(LogStatus.PASS, "Press 'OK' in 'Olvidó Contraseña' menu. - PASS");
			}
			else {
				System.out.println("Element not found within 5 seconds.");
				tc1.log(LogStatus.FAIL, "Press 'OK' in 'Olvidó Contraseña' menu. - FAIL");
			}
		
		}  catch(Exception e) {
			String msn = "THE TEST \"" + testCaseName + "\" DID NOT EXCECUTE!";
			System.out.println(msn);
			tc1.log(LogStatus.FATAL, msn );
		}
		
		rep.endTest(tc1);
		rep.flush();
		
	}
	
	@Test(groups = {"mobile"})
	public void termPoliticas() throws IOException {
	
		// LoginPage: This test clicks on "Términos y Políticas" and selects "OK".
		
		String testCaseName = "LoginMenu__Terminos_Politicas";

		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\MOB_demo\\"+testCaseName+".jpg";
		
		ExtentReports rep = reportCapture.handleReportMob();
		ExtentTest tc1 = rep.startTest(testCaseName);
		
		try {
		
			loginPage login = new loginPage(driver_android, mobileproperties);
			login.terminos_politicas().click();
			
			Duration timeout = Duration.ofSeconds(10);
			WebElement con_2 = ElementUtils.waitForElement(driver_android, timeout, driver -> login.term_politica__msg_2());
			WebElement con_3 = ElementUtils.waitForElement(driver_android, timeout, driver -> login.term_politica_msg_1());
			WebElement con_1 = ElementUtils.waitForElement(driver_android, timeout, driver -> login.term_politica_ok());
			
			if(con_2 != null && con_3 != null && con_1 != null ) {
				System.out.println("Elements found and visible.");
				tc1.log(LogStatus.PASS, "This test clicks 'Términos y Políticas.' - PASS");
			}
			else {
				System.out.println("Element not found within 5 seconds.");
				tc1.log(LogStatus.FAIL, "This test clicks 'Términos y Políticas.' - FAIL");
			}
			
			screenshotCapture.takeScreenshotMob(driver_android, pathPicture);
			
			con_1.click();
			
			Boolean con_4 = login.inicio_sesion().isDisplayed();
			
			if(con_4 != false) {
				System.out.println("Elements found and visible.");
				tc1.log(LogStatus.PASS, "Press 'OK' in 'Términos y Políticas' menu. - PASS");
			}
			else {
				System.out.println("Element not found within 5 seconds.");
				tc1.log(LogStatus.FAIL, "Press 'OK' in 'Términos y Políticas' menu. - FAIL");
			}
		
		}  catch(Exception e) {
			String msn = "THE TEST \"" + testCaseName + "\" DID NOT EXCECUTE!";
			System.out.println(msn);
			tc1.log(LogStatus.FATAL, msn );
		}
		
		rep.endTest(tc1);
		rep.flush();
	}
	
	@Test(groups = {"mobile"})
	public void selecIdioma() throws IOException {
		
		//  LoginPage: This test case verifies some strings in "Idioma menu" and "Español" is selected by default.
		
		String testCaseName = "LoginMenu__Language";
		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\MOB_demo\\"+testCaseName+".jpg";
		
		ExtentReports rep = reportCapture.handleReportMob();
		ExtentTest tc1 = rep.startTest(testCaseName);
		
		try {
		
			String list = mobileproperties.getProperty("list_string_idioma");
			String[] expectedString = list.split(";");
			
			loginPage login = new loginPage(driver_android, mobileproperties);
			login.usuario().sendKeys(username);
			login.password().sendKeys(passwd);
			login.inicio_sesion().click();
			
			Duration timeout = Duration.ofSeconds(30);
			String t1 = ElementUtils.waitForElement(driver_android, timeout, driver -> login.idioma_seleccion()).getText();
			String t2 = ElementUtils.waitForElement(driver_android, timeout, driver -> login.idioma_eng()).getText();
			String t3 = ElementUtils.waitForElement(driver_android, timeout, driver -> login.idioma_esp()).getText();
			String t4 = ElementUtils.waitForElement(driver_android, timeout, driver -> login.idioma_siguiente()).getText();
			
			List<String> st = Arrays.asList(t1,t2,t3,t4);
			
			Integer c = 0;
			
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
			
			Boolean sel = ElementUtils.waitForElement(driver_android, timeout, driver -> login.idioma_seleccion()).isDisplayed();
			
			if (sel) {
				tc1.log(LogStatus.PASS, "'Spanish' language selected by default." );
			}
			else {
				tc1.log(LogStatus.FAIL, "'Spanish' language NOT selected by default." );
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
	public void selecControl() throws IOException {
		
		//  LoginPage: This test case verifies some strings in "Control menu" and "Adultos" selected by default.
		
			String testCaseName = "LoginMenu__Control_Parental";
			String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\MOB_demo\\"+testCaseName+".jpg";
			
			ExtentReports rep = reportCapture.handleReportMob();
			ExtentTest tc1 = rep.startTest(testCaseName);
			
			try {
			
				String list = mobileproperties.getProperty("list_string_control");
				String[] expectedString = list.split(";");
				
				loginPage login = new loginPage(driver_android, mobileproperties);
				login.usuario().sendKeys(username);
				login.password().sendKeys(passwd);
				login.inicio_sesion().click();
				
				Duration timeout = Duration.ofSeconds(30);
				ElementUtils.waitForElement(driver_android, timeout, driver -> login.idioma_siguiente()).click();
				
				Duration timeout_2 = Duration.ofSeconds(3);
				String t1 = ElementUtils.waitForElement(driver_android, timeout_2, driver -> login.control_omitir()).getText();
				String t2 = ElementUtils.waitForElement(driver_android, timeout_2, driver -> login.control_configure()).getText();
				String t3 = ElementUtils.waitForElement(driver_android, timeout_2, driver -> login.control_habilitar()).getText();
				String t4 = ElementUtils.waitForElement(driver_android, timeout_2, driver -> login.control_idioma()).getText();
				String t5 = ElementUtils.waitForElement(driver_android, timeout_2, driver -> login.control_permitido()).getText();
				String t6 = ElementUtils.waitForElement(driver_android, timeout_2, driver -> login.control_adultos()).getText(); 
	//			
				List<String> st = Arrays.asList(t1,t2,t3,t4,t5,t6);
				
				Integer c = 0;
				
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
			}
			
			screenshotCapture.takeScreenshotMob(driver_android, pathPicture);
			
			rep.endTest(tc1);
			rep.flush();
	}
	
	
}
