/**
 * 
 */
package com.mobiquityinc.service;

import java.util.List;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.models.BatchModel;
import com.mobiquityinc.dao.BatchReaderDao;
import com.mobiquityinc.dao.BatchReaderDaoImpl;

/**
 * due to applying Facade design pattern this service class 
 * of batch reader dao which will bypass the request to BatchReaderDao 
 * @author AhmedTaha
 * 
 */
public class BatchReaderService {

	private BatchReaderDao batchReaderDao;

	public BatchReaderService() {
		batchReaderDao = new BatchReaderDaoImpl();
	}

	public List<BatchModel> getAll(String filePath) throws APIException {
		return batchReaderDao.getAll(filePath);
	}
}
