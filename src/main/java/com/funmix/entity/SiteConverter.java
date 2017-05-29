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
 * Converter for {@link com.funmix.entity.Site}.
 *
 * NOTE: This class has been automatically generated from the {@link com.funmix.entity.Site} original class using Vert.x codegen.
 */
public class SiteConverter {

  public static void fromJson(JsonObject json, Site obj) {
    if (json.getValue("address") instanceof String) {
      obj.setAddress((String)json.getValue("address"));
    }
    if (json.getValue("contact") instanceof String) {
      obj.setContact((String)json.getValue("contact"));
    }
    if (json.getValue("contact_phone") instanceof String) {
      obj.setContact_phone((String)json.getValue("contact_phone"));
    }
    if (json.getValue("id") instanceof Number) {
      obj.setId(((Number)json.getValue("id")).intValue());
    }
    if (json.getValue("latitude") instanceof Number) {
      obj.setLatitude(((Number)json.getValue("latitude")).doubleValue());
    }
    if (json.getValue("longitude") instanceof Number) {
      obj.setLongitude(((Number)json.getValue("longitude")).doubleValue());
    }
    if (json.getValue("memo") instanceof String) {
      obj.setMemo((String)json.getValue("memo"));
    }
    if (json.getValue("phone") instanceof String) {
      obj.setPhone((String)json.getValue("phone"));
    }
    if (json.getValue("sitename") instanceof String) {
      obj.setSitename((String)json.getValue("sitename"));
    }
  }

  public static void toJson(Site obj, JsonObject json) {
    if (obj.getAddress() != null) {
      json.put("address", obj.getAddress());
    }
    if (obj.getContact() != null) {
      json.put("contact", obj.getContact());
    }
    if (obj.getContact_phone() != null) {
      json.put("contact_phone", obj.getContact_phone());
    }
    json.put("id", obj.getId());
    json.put("latitude", obj.getLatitude());
    json.put("longitude", obj.getLongitude());
    if (obj.getMemo() != null) {
      json.put("memo", obj.getMemo());
    }
    if (obj.getPhone() != null) {
      json.put("phone", obj.getPhone());
    }
    if (obj.getSitename() != null) {
      json.put("sitename", obj.getSitename());
    }
  }
}