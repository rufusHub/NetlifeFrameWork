package org.netlife.webPages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.collections.Lists;

public class settingsPage {

	ChromeDriver driver;
	Properties pr;
	
	public settingsPage(ChromeDriver driver, Properties pr) {
		this.driver = driver;
		this.pr = pr;
	}
	
	public void selectSettingsIcon() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath(pr.getProperty("settingicon"))).click();	
	}
	
	public void selectControlParental() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath(pr.getProperty("controlparent"))).click();
		
	}

	public List<String> getParentalControlOptions() throws InterruptedException {
		Thread.sleep(2000);
		List<WebElement> menuoptions = driver.findElements(By.xpath(pr.getProperty("ctrlparntlmenu")));
		
		List<String> st = new ArrayList<String>();
		for(WebElement e : menuoptions) {
			st.add(e.getText());
		}
		return st;
	}
	
	public String getDefaltParental() throws InterruptedException {
		Thread.sleep(2000);
		String txt = driver.findElement(By.xpath(pr.getProperty("defaultrating"))).getText();
		return txt;
	}
	
	public void selectDeviceInfo() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath(pr.getProperty("acercadeldisp"))).click();
	}
	
	public String getActualVersionTitle() throws InterruptedException {
		Thread.sleep(2000);
		String txt = driver.findElement(By.xpath(pr.getProperty("versionactual"))).getText();
		return txt;
	}
	
	public String getActualVersionNum() throws InterruptedException {
		Thread.sleep(2000);
		String txt = driver.findElement(By.xpath(pr.getProperty("veractnum"))).getText();
		return txt;
	}
	
	public String getPrincipalVersionTitle() throws InterruptedException {
		Thread.sleep(2000);
		String txt = driver.findElement(By.xpath(pr.getProperty("versionprincipal"))).getText();
		return txt;
	}
	public String getPrincipalVersionNum() throws InterruptedException {
		Thread.sleep(2000);
		String txt = driver.findElement(By.xpath(pr.getProperty("verprinnum"))).getText();
		return txt;
	}
	
	
}
