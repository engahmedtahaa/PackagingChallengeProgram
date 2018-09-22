package com.mobiquityinc.dao;

import java.util.List;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.models.BatchModel;


/**
 * This interface contains all method which  service can access it from the implementation of it
 * and all methods should be implement from other classes that implement it  
 * I apply Facade Design Pattern
 * @author AhmedTaha
 */
public interface BatchReaderDao {
	
    List<BatchModel> getAll(String filePath) throws APIException;

}
