package com.mobiquityinc.models;


/**
 *  This class in the domain object which  represents all input data of thing object which will be 
 *  added inside PackgeModel
 * @author AhmedTaha
 *
 */
public class ThingModel {
	private int index;
	private double weight;
	private double cost;

	public ThingModel() {
		super();
	}

	public ThingModel(int index, double weight, double cost) {
		super();
		this.index = index;
		this.weight = weight;
		this.cost = cost;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}
