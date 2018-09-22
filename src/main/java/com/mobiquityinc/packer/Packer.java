package com.mobiquityinc.packer;

import java.util.List;
import java.util.stream.Collectors;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.models.BatchModel;
import com.mobiquityinc.models.PackageModel;
import com.mobiquityinc.proxy.PackerProxyFactory;

import com.mobiquityinc.service.PackerService;


/**
 * This class which will be the main interface of APP 
 * contains pack method which responsible for select best package
 * @author AhmedTaha
 *
 */
public class Packer {
	
	private static final PackerService packerService = PackerProxyFactory.newInstance();
	
	/**
	 * This Method responsible for select best packages according to list of Things 
	 * init the packing process 
	 * @param filePathabsolute path to a test file
	 * @return best packages for each test cases in string format
	 * @throws APIException
	 */
	public static String pack(String filePath) throws APIException {
		
		List<BatchModel> batchesList = packerService.getAll(filePath);
		return packerService.pack(batchesList).stream().map(PackageModel::toString).collect(Collectors.joining("\n"));
	}
	
}
