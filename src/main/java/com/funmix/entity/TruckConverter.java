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
 * Converter for {@link com.funmix.entity.Truck}.
 *
 * NOTE: This class has been automatically generated from the {@link com.funmix.entity.Truck} original class using Vert.x codegen.
 */
public class TruckConverter {

  public static void fromJson(JsonObject json, Truck obj) {
    if (json.getValue("id") instanceof Number) {
      obj.setId(((Number)json.getValue("id")).intValue());
    }
    if (json.getValue("lastupdate") instanceof String) {
      obj.setLastupdate((String)json.getValue("lastupdate"));
    }
    if (json.getValue("latitude") instanceof Number) {
      obj.setLatitude(((Number)json.getValue("latitude")).doubleValue());
    }
    if (json.getValue("licenseplate") instanceof String) {
      obj.setLicenseplate((String)json.getValue("licenseplate"));
    }
    if (json.getValue("longitude") instanceof Number) {
      obj.setLongitude(((Number)json.getValue("longitude")).doubleValue());
    }
    if (json.getValue("tonnage") instanceof Number) {
      obj.setTonnage(((Number)json.getValue("tonnage")).doubleValue());
    }
    if (json.getValue("truck_type") instanceof String) {
      obj.setTruck_type((String)json.getValue("truck_type"));
    }
    if (json.getValue("volume_height") instanceof Number) {
      obj.setVolume_height(((Number)json.getValue("volume_height")).doubleValue());
    }
    if (json.getValue("volume_length") instanceof Number) {
      obj.setVolume_length(((Number)json.getValue("volume_length")).doubleValue());
    }
    if (json.getValue("volume_width") instanceof Number) {
      obj.setVolume_width(((Number)json.getValue("volume_width")).doubleValue());
    }
  }

  public static void toJson(Truck obj, JsonObject json) {
    json.put("id", obj.getId());
    if (obj.getLastupdate() != null) {
      json.put("lastupdate", obj.getLastupdate());
    }
    json.put("latitude", obj.getLatitude());
    if (obj.getLicenseplate() != null) {
      json.put("licenseplate", obj.getLicenseplate());
    }
    json.put("longitude", obj.getLongitude());
    json.put("tonnage", obj.getTonnage());
    if (obj.getTruck_type() != null) {
      json.put("truck_type", obj.getTruck_type());
    }
    json.put("volume_height", obj.getVolume_height());
    json.put("volume_length", obj.getVolume_length());
    json.put("volume_width", obj.getVolume_width());
  }
}