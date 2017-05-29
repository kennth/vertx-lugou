package com.funmix.entity;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class Driver {
	int id;
	String username;
	String email;
	String phone;
	String created_time;
	int status;
	String driverlicense;
	String memo;

	public Driver(JsonObject obj) {
		DriverConverter.fromJson(obj, this);
	}

	public Driver(String jsonStr) {
		DriverConverter.fromJson(new JsonObject(jsonStr), this);
	}

	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		DriverConverter.toJson(this, json);
		return json;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreated_time() {
		return created_time;
	}

	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDriverlicense() {
		return driverlicense;
	}

	public void setDriverlicense(String driverlicense) {
		this.driverlicense = driverlicense;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}