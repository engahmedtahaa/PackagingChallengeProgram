package com.mobiquityinc.validator;

import java.util.function.Predicate;

/**
 * This is class is the implementation of GenericValidator
 * @author AhmedTaha
 *
 */

public class GenericValidatorImpl<K> implements GenericValidator<K> {
	private Predicate<K> predicate;

	public static <K> GenericValidatorImpl<K> from(Predicate<K> predicate) {
		return new GenericValidatorImpl<K>(predicate);
	}

	private GenericValidatorImpl(Predicate<K> predicate) {
		this.predicate = predicate;
	}

	@Override
	public GenericValidationResult validate(K param) {
		return predicate.test(param) ? GenericValidationResult.ok() : GenericValidationResult.fail();
	}

}
