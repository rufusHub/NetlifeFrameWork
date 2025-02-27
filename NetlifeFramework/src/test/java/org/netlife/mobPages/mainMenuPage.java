package org.netlife.mobPages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class mainMenuPage {
	
	AndroidDriver driver;
	Properties pr;
	
	public mainMenuPage(AndroidDriver driver, Properties pr) {
		this.driver = driver;
		this.pr = pr;
	}

	public WebElement inicio_button() {
		return driver.findElement(By.xpath(pr.getProperty("inicio_button")));
	}
	
	public WebElement inicio_label() {
		return driver.findElement(By.xpath(pr.getProperty("inicio_label")));
	}
	
	public WebElement inicio_search() {
		return driver.findElement(By.xpath(pr.getProperty("inicio_search")));
	}

	public WebElement inicio_strip(int x) {
		String y =  Integer.toString(x);
		String m = "inicio_strip_" + y;
		return driver.findElement(AppiumBy.androidUIAutomator(pr.getProperty(m)));
	}
	
	public WebElement inicio_strip_0() {
		return driver.findElement(By.xpath(pr.getProperty("inicio_strip_0")));
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	
	public WebElement main_inicio() {
		return driver.findElement(By.xpath(pr.getProperty("main_inicio")));
	}

	public WebElement main_vivo() {
		return driver.findElement(By.xpath(pr.getProperty("main_vivo")));
	}

	public WebElement main_guia() {
		return driver.findElement(By.xpath(pr.getProperty("main_guia")));
	}

	public WebElement main_contenidos() {
		return driver.findElement(By.xpath(pr.getProperty("main_contenidos")));
	}

	public WebElement main_buscar() {
		return driver.findElement(By.xpath(pr.getProperty("main_buscar")));
	}
	
	public WebElement main_config() {
		return driver.findElement(By.xpath(pr.getProperty("main_config")));
	}
	
	public WebElement main_config_icon() {
		return driver.findElement(By.xpath(pr.getProperty("main_config_icon")));
	}

	/////////////////////////////////////////////////////////////////////////////////
	
	public WebElement config_config() {
		return driver.findElement(By.xpath(pr.getProperty("config_config")));
	}
	
	public WebElement config_back_button() {
		return driver.findElement(By.xpath(pr.getProperty("config_back_button")));
	}
	
	public WebElement config_config_general_button() {
		return driver.findElement(By.xpath(pr.getProperty("config_config_general_button")));
	}
	
	public WebElement config_guia_button() {
		return driver.findElement(By.xpath(pr.getProperty("config_guia_button")));
	}
	
	public List<WebElement> config_all_options(){
		return  driver.findElements(AppiumBy.androidUIAutomator(pr.getProperty("config_resource_id")));
	}
	
	
}
