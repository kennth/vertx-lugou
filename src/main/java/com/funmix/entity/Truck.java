package com.funmix.entity;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class Truck {
	int id;
	String licenseplate;
	String truck_type;
	double tonnage;
	double volume_length;
	double volume_width;
	double volume_height;
	double longitude;
	double latitude;
	String lastupdate;

	public Truck(JsonObject obj) {
		TruckConverter.fromJson(obj, this);
	}

	public Truck(String jsonStr) {
		TruckConverter.fromJson(new JsonObject(jsonStr), this);
	}

	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		TruckConverter.toJson(this, json);
		return json;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLicenseplate() {
		return licenseplate;
	}

	public void setLicenseplate(String licenseplate) {
		this.licenseplate = licenseplate;
	}

	public String getTruck_type() {
		return truck_type;
	}

	public void setTruck_type(String truck_type) {
		this.truck_type = truck_type;
	}

	public double getTonnage() {
		return tonnage;
	}

	public void setTonnage(double tonnage) {
		this.tonnage = tonnage;
	}

	public double getVolume_length() {
		return volume_length;
	}

	public void setVolume_length(double volume_length) {
		this.volume_length = volume_length;
	}

	public double getVolume_width() {
		return volume_width;
	}

	public void setVolume_width(double volume_width) {
		this.volume_width = volume_width;
	}

	public double getVolume_height() {
		return volume_height;
	}

	public void setVolume_height(double volume_height) {
		this.volume_height = volume_height;
	}
	
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(String lastupdate) {
		this.lastupdate = lastupdate;
	}
	
	
}