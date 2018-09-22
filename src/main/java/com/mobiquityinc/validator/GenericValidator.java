package com.mobiquityinc.validator;

/**
 *This FunctionalInterface which validate the givien object we can make combination 
 *of or and and default methods
 *@author AhmedTaha
 */
@FunctionalInterface
public interface GenericValidator<K> {
	public GenericValidationResult validate(K param);

	default GenericValidator<K> and(GenericValidator<K> other) {
		return (param) -> {
			GenericValidationResult result = this.validate(param);
			return !result.isValid() ? result : other.validate(param);
		};
	}

	default GenericValidator<K> or(GenericValidator<K> other) {
		return (param) -> {
			GenericValidationResult result = this.validate(param);
			return result.isValid() ? result : other.validate(param);
		};
	}

}
