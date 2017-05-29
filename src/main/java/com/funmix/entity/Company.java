package com.funmix.entity;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class Company {
	int id;
	String companyname;
	String phone;
	String address;
	String contact;
	String contact_phone;
	String created_time;
	String memo;
	double longitude;
	double latitude;
	int status;
	
	public Company(JsonObject obj) {
		CompanyConverter.fromJson(obj, this);
	}

	public Company(String jsonStr) {
		CompanyConverter.fromJson(new JsonObject(jsonStr), this);
	}

	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		CompanyConverter.toJson(this, json);
		return json;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContact_phone() {
		return contact_phone;
	}

	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}

	public String getCreated_time() {
		return created_time;
	}

	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
	
}