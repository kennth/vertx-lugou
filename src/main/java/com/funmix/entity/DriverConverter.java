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
 * Converter for {@link com.funmix.entity.Driver}.
 *
 * NOTE: This class has been automatically generated from the {@link com.funmix.entity.Driver} original class using Vert.x codegen.
 */
public class DriverConverter {

  public static void fromJson(JsonObject json, Driver obj) {
    if (json.getValue("created_time") instanceof String) {
      obj.setCreated_time((String)json.getValue("created_time"));
    }
    if (json.getValue("driverlicense") instanceof String) {
      obj.setDriverlicense((String)json.getValue("driverlicense"));
    }
    if (json.getValue("email") instanceof String) {
      obj.setEmail((String)json.getValue("email"));
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
    if (json.getValue("status") instanceof Number) {
      obj.setStatus(((Number)json.getValue("status")).intValue());
    }
    if (json.getValue("username") instanceof String) {
      obj.setUsername((String)json.getValue("username"));
    }
  }

  public static void toJson(Driver obj, JsonObject json) {
    if (obj.getCreated_time() != null) {
      json.put("created_time", obj.getCreated_time());
    }
    if (obj.getDriverlicense() != null) {
      json.put("driverlicense", obj.getDriverlicense());
    }
    if (obj.getEmail() != null) {
      json.put("email", obj.getEmail());
    }
    json.put("id", obj.getId());
    if (obj.getMemo() != null) {
      json.put("memo", obj.getMemo());
    }
    if (obj.getPhone() != null) {
      json.put("phone", obj.getPhone());
    }
    json.put("status", obj.getStatus());
    if (obj.getUsername() != null) {
      json.put("username", obj.getUsername());
    }
  }
}