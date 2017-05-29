package com.funmix.entity;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class Order {
	int id;
	String orderno;
	int companyid;
	int paperreceipt;
	int abnormal;
	int status;
	int endway;
	String fhunit;
	String fhunitphone;
	String fhname;
	String fhphone;
	String fhaddress;
	String shunit;
	String shunitphone;
	String shname;
	String shphone;
	String shaddress;
	String fhtime;
	String shtime;
	String productcode;
	String productname;
	String packing;
	double packagecount;
	double weight;
	int paymentmode;
	int deliverymode;
	double totalfee;
	String attachment;
	String createtime;
	String updatetime;
	String sdtime;
	String memo;
	String drivername;
	String licenseplate;
	Double fhlongitude;
	Double shlongitude;
	Double fhlatitude;
	Double shlatitude;

	public Order(JsonObject obj) {
		OrderConverter.fromJson(obj, this);
	}

	public Order(String jsonStr) {
		OrderConverter.fromJson(new JsonObject(jsonStr), this);
	}

	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		OrderConverter.toJson(this, json);
		return json;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public int getCompanyid() {
		return companyid;
	}

	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}

	public int getPaperreceipt() {
		return paperreceipt;
	}

	public void setPaperreceipt(int paperreceipt) {
		this.paperreceipt = paperreceipt;
	}

	public int getAbnormal() {
		return abnormal;
	}

	public void setAbnormal(int abnormal) {
		this.abnormal = abnormal;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getEndway() {
		return endway;
	}

	public void setEndway(int endway) {
		this.endway = endway;
	}

	public String getFhunit() {
		return fhunit;
	}

	public void setFhunit(String fhunit) {
		this.fhunit = fhunit;
	}

	public String getFhunitphone() {
		return fhunitphone;
	}

	public void setFhunitphone(String fhunitphone) {
		this.fhunitphone = fhunitphone;
	}

	public String getFhname() {
		return fhname;
	}

	public void setFhname(String fhname) {
		this.fhname = fhname;
	}

	public String getFhphone() {
		return fhphone;
	}

	public void setFhphone(String fhphone) {
		this.fhphone = fhphone;
	}

	public String getFhaddress() {
		return fhaddress;
	}

	public void setFhaddress(String fhaddress) {
		this.fhaddress = fhaddress;
	}

	public String getShunit() {
		return shunit;
	}

	public void setShunit(String shunit) {
		this.shunit = shunit;
	}

	public String getShunitphone() {
		return shunitphone;
	}

	public void setShunitphone(String shunitphone) {
		this.shunitphone = shunitphone;
	}

	public String getShname() {
		return shname;
	}

	public void setShname(String shname) {
		this.shname = shname;
	}

	public String getShphone() {
		return shphone;
	}

	public void setShphone(String shphone) {
		this.shphone = shphone;
	}

	public String getShaddress() {
		return shaddress;
	}

	public void setShaddress(String shaddress) {
		this.shaddress = shaddress;
	}

	public String getFhtime() {
		return fhtime;
	}

	public void setFhtime(String fhtime) {
		this.fhtime = fhtime;
	}

	public String getShtime() {
		return shtime;
	}

	public void setShtime(String shtime) {
		this.shtime = shtime;
	}

	public String getProductcode() {
		return productcode;
	}

	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getPacking() {
		return packing;
	}

	public void setPacking(String packing) {
		this.packing = packing;
	}

	public double getPackagecount() {
		return packagecount;
	}

	public void setPackagecount(double packagecount) {
		this.packagecount = packagecount;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getPaymentmode() {
		return paymentmode;
	}

	public void setPaymentmode(int paymentmode) {
		this.paymentmode = paymentmode;
	}

	public int getDeliverymode() {
		return deliverymode;
	}

	public void setDeliverymode(int deliverymode) {
		this.deliverymode = deliverymode;
	}

	public double getTotalfee() {
		return totalfee;
	}

	public void setTotalfee(double totalfee) {
		this.totalfee = totalfee;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getSdtime() {
		return sdtime;
	}

	public void setSdtime(String sdtime) {
		this.sdtime = sdtime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getLicenseplate() {
		return licenseplate;
	}

	public void setLicenseplate(String licenseplate) {
		this.licenseplate = licenseplate;
	}

	public Double getFhlongitude() {
		return fhlongitude;
	}

	public void setFhlongitude(Double fhlongitude) {
		this.fhlongitude = fhlongitude;
	}

	public Double getShlongitude() {
		return shlongitude;
	}

	public void setShlongitude(Double shlongitude) {
		this.shlongitude = shlongitude;
	}

	public Double getFhlatitude() {
		return fhlatitude;
	}

	public void setFhlatitude(Double fhlatitude) {
		this.fhlatitude = fhlatitude;
	}

	public Double getShlatitude() {
		return shlatitude;
	}

	public void setShlatitude(Double shlatitude) {
		this.shlatitude = shlatitude;
	}
	
}