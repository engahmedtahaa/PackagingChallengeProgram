package com.mobiquityinc.log;

import com.mobiquityinc.util.Defines;


/**
 * This factory class responsible for create the Logger object and i apply singleton Design pattern on it 
 * to return only one created instance which write in one file or the same file   
 * @author AhmedTaha
 *
 */
public class PackerLoggerFactory {

	private static PackerLogger logger;

	static {
		logger = new PackerLogger(PackerLogger.class, Defines.LOGS, Defines.PACKER_LOGS, Defines.PACKER_LOGS_LOGS_APP);
	}

	/**
	 * this for return singleton logger
	 * @return PackerLogger
	 */
	public synchronized static PackerLogger getLogger() {
		return logger;
	}
}
