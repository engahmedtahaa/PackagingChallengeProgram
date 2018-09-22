package com.mobiquityinc.service;

import java.util.List;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.models.BatchModel;
import com.mobiquityinc.models.PackageModel;

/**
 * This the PackerService interface which contain the public access method 
 * accessed by proxy 
 * this interface will be the Facade Pattern Main Service
 * @author AhmedTaha
 */
public interface PackerService {

	public List<PackageModel> pack(List<BatchModel> batchesList)throws APIException;
	public List<BatchModel> getAll(String filePath) throws APIException;
}
