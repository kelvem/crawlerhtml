package com.kelvem.crawler.sample.xulrunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import org.mozilla.xpcom.GREVersionRange;
import org.mozilla.xpcom.Mozilla;

public class SimplestInitializationCode {
	public static void main(String[] args) {
		// JavaXPCOM initialization
		GREVersionRange[] range = new GREVersionRange[1];
		range[0] = new GREVersionRange("1.8", true, "1.9+", true);
		Properties props = null;
		File grePath = null;
		try {
			grePath = Mozilla.getGREPathWithProperties(range, props);
		} catch (FileNotFoundException e) {
			System.out.println("XULRunner not found. It is possible that it be wrong installed");
			return;
		}

		Mozilla mozilla = Mozilla.getInstance();
		mozilla.initialize(grePath);
		try {  
			mozilla.initXPCOM(grePath, null);

		} catch (Throwable t) {
			System.out.println("initXPCOM failed");
			t.printStackTrace();
			return;
		}
		// From this line, we can use JavaXPCOM
		System.out.println("/n--> initialized/n");

		// ... your application code ...
		// JavaXPOM finalization
		mozilla.shutdownXPCOM(null);
	}
}
