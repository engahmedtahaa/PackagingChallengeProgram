package com.mobiquityinc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;

import com.google.gson.Gson;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.exception.APIExceptionUtil;
import com.mobiquityinc.log.PackerLogger;
import com.mobiquityinc.log.PackerLoggerFactory;
import com.mobiquityinc.models.BatchModel;
import com.mobiquityinc.service.PackerService;


/**
 * This Class is Dynamic Proxy  and InvocationHandler implementation 
 * use java reflection to log parameters and method name 
 * this the proxy of APP  
 * @author AhmedTaha
 *
 */
public class PackerProxy implements InvocationHandler {

	private PackerService packerService;
	private PackerLogger logger = PackerLoggerFactory.getLogger();

	private PackerProxy(PackerService packerService) {
		this.packerService = packerService;
	}

	public static PackerService createServiceInstance(PackerService packerService) {

		return (PackerService) Proxy.newProxyInstance(packerService.getClass().getClassLoader(),
				packerService.getClass().getInterfaces(), new PackerProxy(packerService));
	}
	
	/**
	 * This Method responsible proceed the calls of any method and it is like interceptor 
	 * we can add logs here in one place handle exception in one place 
	 * convert method parameters to json to be trackable and readable
	 * @param Object : This is the proxy instance on which method is invoked
	 * @param Method: This corresponds to interface method which is invoked on proxy instance.
	 * @param Object[]: It contains an array of arguments passed in method invocation.
	 * @return the result of exceuteion
	 * @throws APIException
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = null;
		try {
			logger.log(Level.INFO, "---Exceute Method-- ==> " + method.getName());
			StringBuilder stringCarrier = new StringBuilder("");
			for (int i = 0; args != null && i < args.length; i++) {
				if (!(args[i] instanceof Integer)) {
					stringCarrier.append(args[i] + " ");
				}
				if ((args[i] instanceof List<?>) && args[i] != null) {
					@SuppressWarnings("unchecked")
					ArrayList<BatchModel> paramList = (ArrayList<BatchModel>) args[i];
					Gson gson = new Gson();
					String paramtrsAsString = gson.toJson(paramList);
					stringCarrier.append("Data List As Json" + paramtrsAsString);
				}
			}
			logger.log(Level.INFO, "--Parameters of " + method.getName() + " is ---" + stringCarrier.toString());
			result = method.invoke(packerService, args);
		} catch (Exception ex) {
			logger.log(Level.ERROR, "Error During Exceuting", ex);
			throw new APIException(APIExceptionUtil.GENERAL_EXCEPTION, ex.getMessage(), ex);
		}
		return result;
	}
}
