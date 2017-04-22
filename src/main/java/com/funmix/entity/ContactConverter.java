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
 * Converter for {@link com.funmix.entity.Contact}.
 *
 * NOTE: This class has been automatically generated from the {@link com.funmix.entity.Contact} original class using Vert.x codegen.
 */
public class ContactConverter {

  public static void fromJson(JsonObject json, Contact obj) {
    if (json.getValue("company") instanceof String) {
      obj.setCompany((String)json.getValue("company"));
    }
    if (json.getValue("contact") instanceof String) {
      obj.setContact((String)json.getValue("contact"));
    }
    if (json.getValue("fhdz") instanceof String) {
      obj.setFhdz((String)json.getValue("fhdz"));
    }
    if (json.getValue("htzq") instanceof String) {
      obj.setHtzq((String)json.getValue("htzq"));
    }
    if (json.getValue("id") instanceof Number) {
      obj.setId(((Number)json.getValue("id")).intValue());
    }
    if (json.getValue("memo") instanceof String) {
      obj.setMemo((String)json.getValue("memo"));
    }
    if (json.getValue("phone") instanceof String) {
      obj.setPhone((String)json.getValue("phone"));
    }
    if (json.getValue("rate") instanceof Number) {
      obj.setRate(((Number)json.getValue("rate")).intValue());
    }
  }

  public static void toJson(Contact obj, JsonObject json) {
    if (obj.getCompany() != null) {
      json.put("company", obj.getCompany());
    }
    if (obj.getContact() != null) {
      json.put("contact", obj.getContact());
    }
    if (obj.getFhdz() != null) {
      json.put("fhdz", obj.getFhdz());
    }
    if (obj.getHtzq() != null) {
      json.put("htzq", obj.getHtzq());
    }
    if (obj.getId() != null) {
      json.put("id", obj.getId());
    }
    if (obj.getMemo() != null) {
      json.put("memo", obj.getMemo());
    }
    if (obj.getPhone() != null) {
      json.put("phone", obj.getPhone());
    }
    if (obj.getRate() != null) {
      json.put("rate", obj.getRate());
    }
  }
}