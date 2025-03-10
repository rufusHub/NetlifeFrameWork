package org.netlife.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;


public class Base {

	public ChromeDriver driver_chrome;
	public AndroidDriver driver_android;
	public Properties webproperties;
	public Properties mobileproperties;
	public Actions action_chrome;
	public AppiumDriverLocalService service;
	public WebDriverWait wait;
	public WebDriver driver;
	
	String propPathweb = "../NetlifeFramework/WebElement.properties";
	String propPathMob = "../NetlifeFramework/MobElement.properties";

	String url = "https://web-stag.hispasatprod.opentv.com/";
	public String username = "NC1"; 
	public String passwd = "123";
	public String wronguser = "XXX";
	public String wrongpass = "666";
	
	String apkName = "AMOB_NETLIFE_24.40.040.apk";
	//String apkName = "ApiDemo.apk";
	String deviceName = "R5CRA26RMNY";		//"R5CRA26RMNY" "L4SDU17907000554"
	
	
	
	
	
	
	@BeforeMethod(alwaysRun = true, groups = {"web"})
	public void BrowserLaunch() throws IOException {
		// Load WEB properties.
		File f = new File(this.propPathweb);
		FileReader fr = new FileReader(f);
		webproperties = new Properties();
		webproperties.load(fr);
		
		// Launch browser, URL and maximize.
		driver_chrome = new ChromeDriver();
		driver_chrome.get(this.url);
		//driver_chrome.manage().window().maximize();
		
		// Start Actions
		action_chrome = new Actions(driver_chrome);
	}
	
	
	@AfterMethod(alwaysRun = true, groups = {"web"})
	public void BrowserClose() throws InterruptedException {
		Thread.sleep(2000);
		driver_chrome.quit();
	}
	
	
	
	
	
	
	@SuppressWarnings("deprecation")
//	@BeforeMethod(alwaysRun = true, groups = {"mobile"})
	public void appLaunch() throws IOException {
		
//		// Load mobile properties.
//		File f = new File(this.propPathMob);
//		FileReader fr = new FileReader(f);
//		mobileproperties = new Properties();
//		mobileproperties.load(fr);
		
		// Load mobile properties with UTF-8 encoding
		File f = new File(this.propPathMob);
		try (InputStreamReader reader = new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8)) {
		    mobileproperties = new Properties();
		    mobileproperties.load(reader);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		
		
        // Define environment(fix issue).		
		Map<String, String> env = new HashMap<String, String>(System.getenv());
		env.put("ANDROID_HOME", "C:\\Users\\bermudez\\AppData\\Local\\Android\\Sdk");
		//env.put("ANDROID_HOME", "/home/rufo/Android/Sdk");
        env.put("JAVA_HOME", "C:\\Program Files\\Java\\jdk-22");
        //env.put("JAVA_HOME", "/usr/lib/jvm/java-11-openjdk-amd64");
		// Start Appium server from code.
		File js = new File("C:\\Users\\bermudez\\AppData\\Local\\Programs\\Appium Server GUI\\resources\\app\\node_modules\\appium\\build\\lib\\main.js");
		//File js = new File("/home/rufo/.nvm/versions/node/v20.12.1/lib/node_modules/appium/build/lib/main.js");
		service = new AppiumServiceBuilder()
				  .withEnvironment(env)
				  .withAppiumJS(js)
				  .withIPAddress("0.0.0.0")
				  .withArgument(GeneralServerFlag.BASEPATH, "wd/hub")
				  .usingPort(4723)
				  .build();
		service.start();
	    // Desire capabilities.
		File app = new File("C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\MOB_demo\\" + this.apkName);
		//File app = new File("/home/rufo/Documents/" + this.apkName);
		DesiredCapabilities cap = new DesiredCapabilities();		 
		cap.setCapability("app", app.getAbsolutePath());
		cap.setCapability("deviceName", this.deviceName);
		cap.setCapability("platformName", "Android");
		cap.setCapability("automationName","UiAutomator2"); 
		driver_android = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		//wait = new WebDriverWait(driver_android, Duration.ofMillis(30000));
	}
	
	
	@SuppressWarnings("deprecation")
//	@AfterMethod(alwaysRun = true, groups = {"mobile"})
	public void appClose() throws InterruptedException {
		Thread.sleep(5000);
		driver_android.closeApp();
		service.stop();
	}
}



//netstat -ano | findstr :<4728>
//tasklist /FI "PID eq <17364>"
//taskkill /PID <19964> /F



