package org.netlife.assertions;

import org.netlife.utilities.logsCapture;

public class Assertion {

	public static Boolean assertion_1(String actual, String expected, String className) {
		if(actual.equals(expected)) {
			logsCapture.takeLog("Assertion is getting passed", className);
			return true;
		}
		else {
			logsCapture.takeLog("Assertion is getting failed", className);
			return false;
		}
	}
	
	public static void assertion_2(Boolean res, String className ) {
		if(res) {
			logsCapture.takeLog("Assertion is getting passed", className);
		}
		else {
			logsCapture.takeLog("Assertion is getting failed", className);
		}
	}
}
