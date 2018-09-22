/**
 * 
 */
package com.mobiquityinc.validator;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.exception.APIExceptionUtil;
import com.mobiquityinc.models.BatchModel;

/**
 * This is class is the implementation of BatchValidator
 * we will validate all task constrains 
 * @author AhmedTaha
 *
 */
public class BatchValidatorImpl implements BatchValidator {

	/**
	 * This Method to validate BatchModel according to our constrains 
	 * i put the result in stringbuilder and check if not empty i will throw API Exception
	 * Every error have full Description  message 
	 * @param batchModel
	 * @throw APIException
	 */
	@Override
	public void validate(BatchModel batchModel) throws APIException {
		StringBuilder errorFields = new StringBuilder();
		errorFields.append((ValidatorUtil.packageWeightLessThanMaxWeight).validate(batchModel.getLimit())
				.getFieldNameIfInvalid(" Batch has invalid Weight Limit which is "+batchModel.getLimit()+"  and valid weight must be less then or eqaul Max Weight which is 100 \n")
				.orElse(""));
		batchModel.getThingsList().stream().forEach(thingModel -> {
			errorFields.append((ValidatorUtil.validCost).validate(thingModel.getCost()).getFieldNameIfInvalid(
					"Batch has Invalid Cost of One of his Things which it's index is " + thingModel.getIndex()
							+ " and valid Cost Must be less or Equal than 100 and Thing Cost is " + thingModel.getCost()+"\n").orElse(""));
			errorFields.append((ValidatorUtil.validWeight).validate(thingModel.getWeight()).getFieldNameIfInvalid(
					"Batch has Invalid Weight of One of his Things which it's index is  " + thingModel.getIndex()
							+ " and valid Weight must be less than 100 and Thing Weight is " + thingModel.getWeight()+"\n").orElse(""));
		});

		String errors = errorFields.toString();
		if (!errors.isEmpty()) {
			throw new APIException(APIExceptionUtil.INCORRECT_PARAMETER_EXCEPTION,errors);
		}
	}
}
