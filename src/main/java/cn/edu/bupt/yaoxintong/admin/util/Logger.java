package cn.edu.bupt.yaoxintong.admin.util;

public class Logger {

	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Logger.class);

	private static class LazyHolder {
		private static final Logger INSTANCE = new Logger();
	}

	public static final Logger getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void info(String string) {
		// TODO Auto-generated method stub
		logger.info(string);
	}
}