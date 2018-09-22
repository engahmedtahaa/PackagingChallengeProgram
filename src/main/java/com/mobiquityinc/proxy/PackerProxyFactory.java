package com.mobiquityinc.proxy;

import com.mobiquityinc.service.PackerService;
import com.mobiquityinc.service.PackerServiceImpl;

/**
 * this class for create proxy instance
 * @author AhmedTaha
 *
 */
public class PackerProxyFactory {

	public static PackerService newInstance() {
		return PackerProxy.createServiceInstance(new PackerServiceImpl());
	}
}