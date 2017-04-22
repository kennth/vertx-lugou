package com.funmix.entity;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class Contact {
    private Integer id;

    private String company;

    private String contact;

    private String phone;

    private String fhdz;

    private String htzq;

    private String memo;

    private Integer rate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getFhdz() {
        return fhdz;
    }

    public void setFhdz(String fhdz) {
        this.fhdz = fhdz == null ? null : fhdz.trim();
    }

    public String getHtzq() {
        return htzq;
    }

    public void setHtzq(String htzq) {
        this.htzq = htzq == null ? null : htzq.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
    
    public Contact(JsonObject obj) {
    	ContactConverter.fromJson(obj, this);
	}

	public Contact(String jsonStr) {
		ContactConverter.fromJson(new JsonObject(jsonStr), this);
	}

	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		ContactConverter.toJson(this, json);
		return json;
	}
}