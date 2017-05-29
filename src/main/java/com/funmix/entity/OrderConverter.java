/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.funmix.entity;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link com.funmix.entity.Order}.
 *
 * NOTE: This class has been automatically generated from the {@link com.funmix.entity.Order} original class using Vert.x codegen.
 */
public class OrderConverter {

  public static void fromJson(JsonObject json, Order obj) {
    if (json.getValue("abnormal") instanceof Number) {
      obj.setAbnormal(((Number)json.getValue("abnormal")).intValue());
    }
    if (json.getValue("attachment") instanceof String) {
      obj.setAttachment((String)json.getValue("attachment"));
    }
    if (json.getValue("companyid") instanceof Number) {
      obj.setCompanyid(((Number)json.getValue("companyid")).intValue());
    }
    if (json.getValue("createtime") instanceof String) {
      obj.setCreatetime((String)json.getValue("createtime"));
    }
    if (json.getValue("deliverymode") instanceof Number) {
      obj.setDeliverymode(((Number)json.getValue("deliverymode")).intValue());
    }
    if (json.getValue("drivername") instanceof String) {
      obj.setDrivername((String)json.getValue("drivername"));
    }
    if (json.getValue("endway") instanceof Number) {
      obj.setEndway(((Number)json.getValue("endway")).intValue());
    }
    if (json.getValue("fhaddress") instanceof String) {
      obj.setFhaddress((String)json.getValue("fhaddress"));
    }
    if (json.getValue("fhlatitude") instanceof Number) {
      obj.setFhlatitude(((Number)json.getValue("fhlatitude")).doubleValue());
    }
    if (json.getValue("fhlongitude") instanceof Number) {
      obj.setFhlongitude(((Number)json.getValue("fhlongitude")).doubleValue());
    }
    if (json.getValue("fhname") instanceof String) {
      obj.setFhname((String)json.getValue("fhname"));
    }
    if (json.getValue("fhphone") instanceof String) {
      obj.setFhphone((String)json.getValue("fhphone"));
    }
    if (json.getValue("fhtime") instanceof String) {
      obj.setFhtime((String)json.getValue("fhtime"));
    }
    if (json.getValue("fhunit") instanceof String) {
      obj.setFhunit((String)json.getValue("fhunit"));
    }
    if (json.getValue("fhunitphone") instanceof String) {
      obj.setFhunitphone((String)json.getValue("fhunitphone"));
    }
    if (json.getValue("id") instanceof Number) {
      obj.setId(((Number)json.getValue("id")).intValue());
    }
    if (json.getValue("licenseplate") instanceof String) {
      obj.setLicenseplate((String)json.getValue("licenseplate"));
    }
    if (json.getValue("memo") instanceof String) {
      obj.setMemo((String)json.getValue("memo"));
    }
    if (json.getValue("orderno") instanceof String) {
      obj.setOrderno((String)json.getValue("orderno"));
    }
    if (json.getValue("packagecount") instanceof Number) {
      obj.setPackagecount(((Number)json.getValue("packagecount")).doubleValue());
    }
    if (json.getValue("packing") instanceof String) {
      obj.setPacking((String)json.getValue("packing"));
    }
    if (json.getValue("paperreceipt") instanceof Number) {
      obj.setPaperreceipt(((Number)json.getValue("paperreceipt")).intValue());
    }
    if (json.getValue("paymentmode") instanceof Number) {
      obj.setPaymentmode(((Number)json.getValue("paymentmode")).intValue());
    }
    if (json.getValue("productcode") instanceof String) {
      obj.setProductcode((String)json.getValue("productcode"));
    }
    if (json.getValue("productname") instanceof String) {
      obj.setProductname((String)json.getValue("productname"));
    }
    if (json.getValue("sdtime") instanceof String) {
      obj.setSdtime((String)json.getValue("sdtime"));
    }
    if (json.getValue("shaddress") instanceof String) {
      obj.setShaddress((String)json.getValue("shaddress"));
    }
    if (json.getValue("shlatitude") instanceof Number) {
      obj.setShlatitude(((Number)json.getValue("shlatitude")).doubleValue());
    }
    if (json.getValue("shlongitude") instanceof Number) {
      obj.setShlongitude(((Number)json.getValue("shlongitude")).doubleValue());
    }
    if (json.getValue("shname") instanceof String) {
      obj.setShname((String)json.getValue("shname"));
    }
    if (json.getValue("shphone") instanceof String) {
      obj.setShphone((String)json.getValue("shphone"));
    }
    if (json.getValue("shtime") instanceof String) {
      obj.setShtime((String)json.getValue("shtime"));
    }
    if (json.getValue("shunit") instanceof String) {
      obj.setShunit((String)json.getValue("shunit"));
    }
    if (json.getValue("shunitphone") instanceof String) {
      obj.setShunitphone((String)json.getValue("shunitphone"));
    }
    if (json.getValue("status") instanceof Number) {
      obj.setStatus(((Number)json.getValue("status")).intValue());
    }
    if (json.getValue("totalfee") instanceof Number) {
      obj.setTotalfee(((Number)json.getValue("totalfee")).doubleValue());
    }
    if (json.getValue("updatetime") instanceof String) {
      obj.setUpdatetime((String)json.getValue("updatetime"));
    }
    if (json.getValue("weight") instanceof Number) {
      obj.setWeight(((Number)json.getValue("weight")).doubleValue());
    }
  }

  public static void toJson(Order obj, JsonObject json) {
    json.put("abnormal", obj.getAbnormal());
    if (obj.getAttachment() != null) {
      json.put("attachment", obj.getAttachment());
    }
    json.put("companyid", obj.getCompanyid());
    if (obj.getCreatetime() != null) {
      json.put("createtime", obj.getCreatetime());
    }
    json.put("deliverymode", obj.getDeliverymode());
    if (obj.getDrivername() != null) {
      json.put("drivername", obj.getDrivername());
    }
    json.put("endway", obj.getEndway());
    if (obj.getFhaddress() != null) {
      json.put("fhaddress", obj.getFhaddress());
    }
    if (obj.getFhlatitude() != null) {
      json.put("fhlatitude", obj.getFhlatitude());
    }
    if (obj.getFhlongitude() != null) {
      json.put("fhlongitude", obj.getFhlongitude());
    }
    if (obj.getFhname() != null) {
      json.put("fhname", obj.getFhname());
    }
    if (obj.getFhphone() != null) {
      json.put("fhphone", obj.getFhphone());
    }
    if (obj.getFhtime() != null) {
      json.put("fhtime", obj.getFhtime());
    }
    if (obj.getFhunit() != null) {
      json.put("fhunit", obj.getFhunit());
    }
    if (obj.getFhunitphone() != null) {
      json.put("fhunitphone", obj.getFhunitphone());
    }
    json.put("id", obj.getId());
    if (obj.getLicenseplate() != null) {
      json.put("licenseplate", obj.getLicenseplate());
    }
    if (obj.getMemo() != null) {
      json.put("memo", obj.getMemo());
    }
    if (obj.getOrderno() != null) {
      json.put("orderno", obj.getOrderno());
    }
    json.put("packagecount", obj.getPackagecount());
    if (obj.getPacking() != null) {
      json.put("packing", obj.getPacking());
    }
    json.put("paperreceipt", obj.getPaperreceipt());
    json.put("paymentmode", obj.getPaymentmode());
    if (obj.getProductcode() != null) {
      json.put("productcode", obj.getProductcode());
    }
    if (obj.getProductname() != null) {
      json.put("productname", obj.getProductname());
    }
    if (obj.getSdtime() != null) {
      json.put("sdtime", obj.getSdtime());
    }
    if (obj.getShaddress() != null) {
      json.put("shaddress", obj.getShaddress());
    }
    if (obj.getShlatitude() != null) {
      json.put("shlatitude", obj.getShlatitude());
    }
    if (obj.getShlongitude() != null) {
      json.put("shlongitude", obj.getShlongitude());
    }
    if (obj.getShname() != null) {
      json.put("shname", obj.getShname());
    }
    if (obj.getShphone() != null) {
      json.put("shphone", obj.getShphone());
    }
    if (obj.getShtime() != null) {
      json.put("shtime", obj.getShtime());
    }
    if (obj.getShunit() != null) {
      json.put("shunit", obj.getShunit());
    }
    if (obj.getShunitphone() != null) {
      json.put("shunitphone", obj.getShunitphone());
    }
    json.put("status", obj.getStatus());
    json.put("totalfee", obj.getTotalfee());
    if (obj.getUpdatetime() != null) {
      json.put("updatetime", obj.getUpdatetime());
    }
    json.put("weight", obj.getWeight());
  }
}