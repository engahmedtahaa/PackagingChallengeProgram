/**
 * 
 */
package com.mobiquityinc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Level;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.log.PackerLogger;
import com.mobiquityinc.log.PackerLoggerFactory;
import com.mobiquityinc.models.BatchModel;
import com.mobiquityinc.models.PackageModel;
import com.mobiquityinc.models.ThingModel;
import com.mobiquityinc.validator.BatchValidator;
import com.mobiquityinc.validator.BatchValidatorImpl;

/**
 * this service class of Package creation and select best things it is
 * responsible for pack function
 * 
 * @author AhmedTaha
 */
public class PackageService {
	private BatchValidator batchValidator = new BatchValidatorImpl();
	private PackerLogger logger = PackerLoggerFactory.getLogger();

	/**
	 * This Method to return List of packages that packages according to best selection of each batch
	 * @param batchesList
	 * @return List of PackageModel
	 * @throws APIException
	 */
	public List<PackageModel> pack(List<BatchModel> batchesList) throws APIException {
		List<PackageModel> packagesList = new ArrayList<>();
		batchesList.forEach(batchModel -> {
			try {
				batchValidator.validate(batchModel);
				PackageModel packageModel = new PackageModel(getBestPackageThings(batchModel));
				packagesList.add(packageModel);
			} catch (APIException e) {
				logger.log(Level.ERROR, "Error During Validate Batch", e);
				e.printStackTrace();
			}

		});
		return packagesList;
	}

	/**
	 * this method to filter List of Things which have weight big than batch limit weight
	 * @param batchModel
	 * @return List<ThingModel>
	 */
	private List<ThingModel> filter(BatchModel batchModel) {
		List<ThingModel> btachThingsList = batchModel.getThingsList().stream()
				.filter(thingModel -> thingModel.getWeight() <= batchModel.getLimit()).collect(Collectors.toList());
		return btachThingsList;
	}

	/**
	 * Method generate all possible things combinations. all combinations are unique
	 * i follow the Probability Algorithm
	 * @return array of possible Things
	 */
	private List<List<ThingModel>> getAllAvaibleThingsCombinations(List<ThingModel> thingsList) {
		List<List<ThingModel>> unquieCombinationsThingList = new ArrayList<>();
		for (ThingModel thingModel : thingsList) {
			List<List<ThingModel>> allCombinationHolder = new ArrayList<>();
			for (List<ThingModel> combination : unquieCombinationsThingList) {
				List<ThingModel> newCombination = new ArrayList<>(combination);
				newCombination.add(thingModel);
				allCombinationHolder.add(newCombination);
			}
			unquieCombinationsThingList.addAll(allCombinationHolder);
			List<ThingModel> current = new ArrayList<>();
			current.add(thingModel);
			unquieCombinationsThingList.add(current);
		}
		return unquieCombinationsThingList;
	}

	/**
	 * This Method for select best Things Combination
	 * @param combinations
	 * @param limit
	 * @return List<ThingModel>
	 */
	private List<ThingModel> selectBestThingsCombination(List<List<ThingModel>> combinations, double limit) {
		List<ThingModel> bestPackage = new ArrayList<>();
		double bestCost = 0;
		double bestWeight = 100;
		for (List<ThingModel> combination : combinations) {
			double combinationWeight = combination.stream().mapToDouble(ThingModel::getWeight).sum();
			if (combinationWeight <= limit) {
				double combinationPrice = combination.stream().mapToDouble(ThingModel::getCost).sum();
				if (combinationPrice > bestCost) {
					bestCost = combinationPrice;
					bestPackage = combination;
					bestWeight = combinationWeight;
				} else if (combinationPrice == bestCost) {
					if (combinationWeight < bestWeight) {
						bestPackage = combination;
						bestWeight = combinationWeight;
					}
				}
			}
		}
		return bestPackage;
	}

	/**
	 * This return Best Package by every line in input file
	 * @param batchModel
	 * @return List<ThingModel>
	 */
	private List<ThingModel> getBestPackageThings(BatchModel batchModel) {
		List<ThingModel> btachThingsList = filter(batchModel);
		List<List<ThingModel>> unquieCombinationsThingList = getAllAvaibleThingsCombinations(btachThingsList);
		List<ThingModel> bestPackageThingList = selectBestThingsCombination(unquieCombinationsThingList,
				batchModel.getLimit());
		return bestPackageThingList;
	}
}
