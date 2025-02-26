package org.netlife.utilities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class scrollToElement {

	public void scrollToElement_1(AndroidDriver driver, int x) {
		String y = Integer.toString(x);
		String resourceId = "live_strip_" + y +"_heading_text";
		driver.findElement(AppiumBy.androidUIAutomator(
		        "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().resourceId(\"" + resourceId + "\"))"));
		}
}
