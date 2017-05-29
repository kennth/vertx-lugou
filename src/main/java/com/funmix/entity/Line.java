package com.funmix.entity;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class Line {
	int id;
	String linename;
	String licenseplate;
	String drivername;
	String startaddr;
	String endaddr;
	String path;
	int process;
	String starttime;
	String endtime;
	int status;

	public Line(JsonObject obj) {
		LineConverter.fromJson(obj, this);
	}

	public Line(String jsonStr) {
		LineConverter.fromJson(new JsonObject(jsonStr), this);
	}

	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		LineConverter.toJson(this, json);
		return json;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLinename() {
		return linename;
	}

	public void setLinename(String linename) {
		this.linename = linename;
	}

	public String getLicenseplate() {
		return licenseplate;
	}

	public void setLicenseplate(String licenseplate) {
		this.licenseplate = licenseplate;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getStartaddr() {
		return startaddr;
	}

	public void setStartaddr(String startaddr) {
		this.startaddr = startaddr;
	}

	public String getEndaddr() {
		return endaddr;
	}

	public void setEndaddr(String endaddr) {
		this.endaddr = endaddr;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getProcess() {
		return process;
	}

	public void setProcess(int process) {
		this.process = process;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}