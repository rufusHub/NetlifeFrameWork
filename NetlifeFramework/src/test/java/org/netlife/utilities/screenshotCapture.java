package org.netlife.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;

public class screenshotCapture {

	public static void takeScreenshot(ChromeDriver driver, String path) throws IOException {
		File f = driver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f, new File(path));
	}
}