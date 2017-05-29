package com.funmix.entity;

import java.time.*;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class User {
	int id;
	int companyid;
	String username;
	String email;
	String phone;
	String password;
	String created_time;
	int status;
	String token;
	int access_status;
	String web_routers;
	String api_routers;
	String default_web_routers;

	public User(JsonObject obj) {
		UserConverter.fromJson(obj, this);
	}

	public User(String jsonStr) {
		System.out.println(1);
		UserConverter.fromJson(new JsonObject(jsonStr), this);
		System.out.println(2);
	}

	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		UserConverter.toJson(this, json);
		return json;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompanyid() {
		return companyid;
	}

	public void setCompanyid(int companyid) {
		this.companyid = companyid;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getAccess_status() {
		return access_status;
	}

	public void setAccess_status(int access_status) {
		this.access_status = access_status;
	}

	public String getWeb_routers() {
		return web_routers;
	}

	public void setWeb_routers(String web_routers) {
		this.web_routers = web_routers;
	}

	public String getApi_routers() {
		return api_routers;
	}

	public void setApi_routers(String api_routers) {
		this.api_routers = api_routers;
	}

	public String getDefault_web_routers() {
		return default_web_routers;
	}

	public void setDefault_web_routers(String default_web_routers) {
		this.default_web_routers = default_web_routers;
	}
	
}