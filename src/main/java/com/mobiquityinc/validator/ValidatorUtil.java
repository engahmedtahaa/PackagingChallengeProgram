package com.mobiquityinc.validator;
/**
 * This class will be used to statically call these validation methods. In the code below,
 * the validations are declared using the functional interface we created
 * @author AhmedTaha
 *
 */

public class ValidatorUtil {
    public static final double PACKAGE_MAX_WEIGHT = 100;
    public static final double THINGS_MAX_WEIGHT = 100;
    public static final double THING_MAX_COST = 100;
    public static final int THING_MAX_COUNT = 15;
    
    public static final GenericValidator  <String> notNullString = GenericValidatorImpl.from(val -> val != null);
    public static final GenericValidator <String> notEmptyString = GenericValidatorImpl.from(val -> !val.isEmpty());
    public static final GenericValidator <Double> packageWeightLessThanMaxWeight = GenericValidatorImpl.from(val -> val <= PACKAGE_MAX_WEIGHT);
    public static final GenericValidator <Double> packageThingLessThanMaxCount = GenericValidatorImpl.from(val -> val <= THING_MAX_COUNT);
    public static final GenericValidator <Double> validWeight = GenericValidatorImpl.from(val -> val <= THINGS_MAX_WEIGHT);
    public static final GenericValidator <Double> validCost = GenericValidatorImpl.from(val -> val <= THING_MAX_COST);


}
