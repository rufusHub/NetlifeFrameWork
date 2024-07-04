package org.netlife.utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class logsCapture {

	public static void takeLog(String msg, String className) {
		DOMConfigurator.configure("../NetlifeFramework/Layout.xml");
		Logger L = Logger.getLogger(className);
		L.info(msg);
	}
}