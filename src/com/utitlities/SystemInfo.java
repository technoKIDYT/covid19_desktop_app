package com.utitlities;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class SystemInfo {

	private static final long kilobytes = 1024;
	private static final long megabytes = kilobytes * 1024;
	private static final long gigabytes = megabytes * 1024;
	private static final String nameOS = "os.name";
	private static final String versionOS = "os.version";
	private static final String architectureOS = "os.arch";
	private static InetAddress inetAddress;
	static {
		try {
			inetAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			System.out.println("Caught an exception " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static String getOSName() {
		return System.getProperty(nameOS);
	}

	public static String getOSVersion() {
		return System.getProperty(versionOS);
	}

	public static String getOSArchitecture() {
		return System.getProperty(architectureOS);
	}

	public static int getAvailableProcessors() {
		return Runtime.getRuntime().availableProcessors();
	}

	private static File[] getAllFileSystemRoots() {
		return File.listRoots();
	}

	public static float getTotalSpace() {
		File[] roots = getAllFileSystemRoots();
		float floatTotalMemory = 0;
		for (File root : roots) {
			floatTotalMemory = (root.getTotalSpace() / (float) gigabytes);
		}
		return floatTotalMemory;
	}

	public static float getFreeSpace() {
		File[] roots = getAllFileSystemRoots();
		float floatFreeSpace = 0;
		for (File root : roots) {
			floatFreeSpace = (root.getFreeSpace() / (float) gigabytes);
		}
		return floatFreeSpace;
	}

	public static float getUsableSpace() {
		File[] roots = getAllFileSystemRoots();
		float floatUsableSpace = 0;
		for (File root : roots) {
			floatUsableSpace = (root.getUsableSpace() / (float) gigabytes);
		}
		return floatUsableSpace;
	}

	public static String getRoot() {
		File[] roots = getAllFileSystemRoots();
		String strRoot = "";
		for (File root : roots) {
			strRoot = root.getAbsolutePath();
		}
		return strRoot;
	}

	public static String getHostName() {
		return inetAddress.getHostName();
	}

	public static String getHostAddress() {
		return inetAddress.getHostAddress();
	}
}
