package com.mobiquityinc.service;

import java.util.List;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.models.BatchModel;
import com.mobiquityinc.models.PackageModel;


/**
 * This Class is the implementation of PackerService
 * @author AhmedTaha
 */
public class PackerServiceImpl implements PackerService {

	@Override
	public List<PackageModel> pack(List<BatchModel> batchesList) throws APIException {
		return new PackageService().pack(batchesList);
	}

	@Override
	public List<BatchModel> getAll(String filePath) throws APIException {
		return new BatchReaderService().getAll(filePath);
	}

}
