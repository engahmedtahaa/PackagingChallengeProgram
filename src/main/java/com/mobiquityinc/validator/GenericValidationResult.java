package com.mobiquityinc.validator;

import java.util.Optional;

/**
 * @author AhmedTaha
 * This Generic result of our validator component
 * which has the methods ok, fail, and isValid. It also has the method getFieldNameIfInvalid,
 * which will optionally return the invalid field’s name if the constraint is violated.
 */
public class GenericValidationResult {
	private boolean valid;

	public boolean isValid() {
		return valid;
	}

	public static GenericValidationResult ok() {
		return new GenericValidationResult(true);
	}

	private GenericValidationResult(boolean valid) {
		this.valid = valid;
	}

	public static GenericValidationResult fail() {
		return new GenericValidationResult(false);
	}

	public Optional<String> getFieldNameIfInvalid(String field) {
		return this.valid ? Optional.empty() : Optional.of(field);
	}
}
