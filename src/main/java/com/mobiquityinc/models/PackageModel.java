package com.mobiquityinc.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * This class in the domain object which  represents package which will be sent 
 * contains list of ThingModel
 * @author AhmedTaha
 *
 */
public class PackageModel {
	private  List<ThingModel> thingsList;

    public PackageModel(List<ThingModel> thingsList) {
        this.thingsList = Collections.unmodifiableList(thingsList);
    }
    public PackageModel(ThingModel [] thingsList) {
        this.thingsList = Arrays.asList(thingsList);
    }

    public PackageModel() {
    	super();
	}
	public List<ThingModel> getThings() {
        return thingsList;
    }

    /* override toString Method to format output object as string
     * @see java.lang.Object#toString()
     */
	
    @Override
    public String toString() {
        if (thingsList == null || thingsList.isEmpty()) return "-";
        return thingsList.stream().map(thingModel -> String.valueOf(thingModel.getIndex())).collect(Collectors.joining(", "));
    }
}
