package com.unihannover.gamedev.models;

/**
 * Represents an achievement
 */
abstract public class Achievement implements Model {

	protected String id;
	protected String collectorId;
	protected String name;
	protected String description;
	protected float value;

	public Achievement(){}
    public Achievement(String id, String name, String description, Float value, String collectorId){
    	this.id = id;
    	this.name = name;
    	this.description = description;
    	this.value = value;
    	this.collectorId = collectorId;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toJSON()
	{
		StringBuilder json = new StringBuilder();
		json.append("{");
		json.append("\"collectorId\":  " + "\"" + collectorId + "\"" + ",");
		json.append("\"description\":  " + "\"" + description + "\"" + ",");
		json.append("\"id\": " + "\"" + id + "\"" + ",");
		json.append("\"name\": " + "\"" + name + "\"" + ",");
		json.append("\"value\": " + value);
		json.append("}");
		return json.toString();
	}

	// *** Autogenerated Setters & Getters ***

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCollectorId() {
		return collectorId;
	}

	public void setCollectorId(String collectorId) {
		this.collectorId = collectorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}
}
