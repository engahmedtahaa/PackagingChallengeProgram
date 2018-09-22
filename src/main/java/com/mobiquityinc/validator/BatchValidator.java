/**
 * 
 */
package com.mobiquityinc.validator;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.models.BatchModel;

/**
 * This interface for Batch Validation to validate all constrains 
 * @author AhmedTaha
 *
 */
public interface BatchValidator {

	public void validate(BatchModel batchModel) throws APIException;
}
