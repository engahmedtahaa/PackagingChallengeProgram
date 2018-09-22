package com.mobiquityinc.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.exception.APIExceptionUtil;
import com.mobiquityinc.models.BatchModel;
import com.mobiquityinc.models.ThingModel;

/**
 * This class implement @interface BatchReaderDao 
 * This call act As Data Access object which parse file that provides and i consdier it as datasource
 * I apply Facade Design Pattern
 * @author AhmedTaha
 */

public class BatchReaderDaoImpl implements BatchReaderDao {

	
	/* this method for reterive all single Line in File and Put them in List of BatchModel 
	 * this the implementation method @see com.mobiquityinc.dao.BatchReaderDao#getAll(java.lang.String)
	 * read line by line in file 
	 * @throws APIException in case of file not found or error while reading and parsing file  
	 */
	
	@Override
	public List<BatchModel> getAll(String filePath) throws APIException {

		List<BatchModel> batchesList;
		try (Stream<String> fileLines = Files.lines(Paths.get(filePath))) {
			batchesList = fileLines.map(readBatch).collect(Collectors.toList());
		} catch (IOException ioex) {
			throw new APIException(APIExceptionUtil.IO_EXCEPTION, APIExceptionUtil.IO_EXCEPTION_MSG);
		} catch (Exception ex) {
			throw new APIException(APIExceptionUtil.GENERAL_EXCEPTION, APIExceptionUtil.EXCEPTION_PARSING_MSG);
		}
		return batchesList!=null?batchesList:Collections.emptyList();
	}

	/**
	 * @param String line of file
	 * Functional method which takes line as string and parse it and convert it to BatchModel
	 * return parsing of line in file as follow weight limit and list of things inside that line 
	 * @return BatchModel
	 */
	private static Function<String, BatchModel> readBatch = line -> {
		String[] splitedBatch = line.split(":");
		double weightLimit = Double.parseDouble(splitedBatch[0].trim());
		List<ThingModel> thingsList = Arrays.asList(splitedBatch[1].trim().split(" ")).stream().map(thing -> {
			String[] thingData = thing.split(",");
			int index = Integer.parseInt(thingData[0].substring(1));
			double weight = Double.parseDouble(thingData[1]);
			double cost = Double.parseDouble(thingData[2].substring(1, thingData[2].length() - 1));
			return new ThingModel(index, weight, cost);
		}).collect(Collectors.toList());

		return new BatchModel(weightLimit, thingsList);
	};

}
