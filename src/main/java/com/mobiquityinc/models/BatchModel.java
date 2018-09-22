package com.mobiquityinc.models;

import java.util.List;



/**
 *  This class in the domain object which  represents all input data for one line in input file
 *  have list of ThingModel which represents the objects inside line 
 * @author AhmedTaha
 *
 */
public class BatchModel {

	private final double limit;
	private List<ThingModel> thingsList;

	public BatchModel(double limit, List<ThingModel> thingsList) {
		super();
		this.limit = limit;
		this.thingsList = thingsList;
	}

	public List<ThingModel> getThingsList() {
		return thingsList;
	}

	public void setThingsList(List<ThingModel> thingsList) {
		this.thingsList = thingsList;
	}

	public double getLimit() {
		return limit;
	}
}
