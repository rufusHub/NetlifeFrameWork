package org.netlife.assertions;

import java.util.List;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class MobReportAssertions {

	public void assertion_1(String expectedValue, String currentValue, ExtentTest tc) {
		
		String mesg = "[Expected] :: " + expectedValue + "<br>[Received] :: " +  currentValue;
		
		if (currentValue.equals(expectedValue)) {
			System.out.println("OK");
			tc.log(LogStatus.PASS, mesg);
		}
		else {
			System.out.println("FAILS");
			tc.log(LogStatus.FAIL, mesg);
		}
	}
	
	public void assertion_2(String[] expectedValues, List<String> currentValues, ExtentTest tc) {
		
		Integer c = 0;
		 
		for (String i : currentValues) {
			
			String mesg = "[Expected] :: " + expectedValues[c] + "<br>[Received] :: " + i ;
			
			if (i.equals(expectedValues[c])) {
				System.out.println(i + " -> OK");
				tc.log(LogStatus.PASS, mesg );
			}
			else {
				System.out.println(i + " -> FAIL");
				tc.log(LogStatus.FAIL, mesg );
			}
			c++;
		}
	}
}



