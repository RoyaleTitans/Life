package com.royaletitans.life.utils;

public class Debugger {
	public static void info(String msg) {
		String clazz = Thread.currentThread().getStackTrace()[2].getClassName();
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		
		System.out.println(Color.INFO_BOLD + "[ INFO  ] " + Color.RESET + Color.INFO + Utils.pad(clazz.substring(clazz.lastIndexOf(".") + 1).trim() + "::" + method.replace("<", "").replace(">", "")) + " : " + msg + Color.RESET);
	}
	
	public static void warning(String msg) {
		String clazz = Thread.currentThread().getStackTrace()[2].getClassName();
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		
		System.out.println(Color.WARNING_BOLD + "[WARNING] " + Color.RESET + Color.WARNING + Utils.pad(clazz.substring(clazz.lastIndexOf(".") + 1).trim() + "::" + method.replace("<", "").replace(">", "")) + " : " + msg + Color.RESET);
	}
	
	public static void error(String msg) {
		String clazz = Thread.currentThread().getStackTrace()[2].getClassName();
		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		
		System.out.println(Color.ERROR_BOLD + "[ ERROR ] " + Color.RESET + Color.ERROR + Utils.pad(clazz.substring(clazz.lastIndexOf(".") + 1).trim() + "::" + method.replace("<", "").replace(">", "")) + " : " + msg + Color.RESET);
	}
	
	public static class Color {
	    public static final String RESET = "\033[0m";

	    public static final String INFO = "\033[0;97m"; 
	    public static final String INFO_BOLD = "\033[1;97m";

	    public static final String WARNING = "\033[0;93m";
	    public static final String WARNING_BOLD = "\033[1;93m";
	    
	    public static final String ERROR = "\033[0;91m";
	    public static final String ERROR_BOLD = "\033[1;91m";
	}
}
