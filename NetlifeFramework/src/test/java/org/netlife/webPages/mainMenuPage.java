package org.netlife.webPages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class mainMenuPage {
	
	ChromeDriver driver;
	Properties pr;
	
	public mainMenuPage(ChromeDriver driver, Properties pr) {
		this.driver = driver;
		this.pr = pr;
	}
	
	public List<String> getMainMenuOptions() throws InterruptedException {
		Thread.sleep(5000);
		List<WebElement> menuoptions = driver.findElements(By.xpath(pr.getProperty("menuoptions")));
		
		List<String> st = new ArrayList<String>();
		for(WebElement e : menuoptions){
		    st.add(e.getText());
		}
		return st;
		
		
//		for (WebElement elem : menuoptions) {
//			System.out.println("Elementos: " + elem.getText());
//		}
//				
//		String txt1 = driver.findElement(By.xpath(pr.getProperty("maininicio"))).getText();
//		String txt2 = driver.findElement(By.xpath(pr.getProperty("maincanales"))).getText();
//		String txt3 = driver.findElement(By.xpath(pr.getProperty("mainmiscontenidos"))).getText();
//		String txt4 = driver.findElement(By.xpath(pr.getProperty("mainuser"))).getText();
//		String txt5 = driver.findElement(By.xpath(pr.getProperty("mainsearch"))).getText();
//		String txt6 = driver.findElement(By.xpath(pr.getProperty("mainsettings"))).getText();
//		return Arrays.asList(txt1, txt2, txt3, txt4, txt5, txt6);
//		
	}
}
