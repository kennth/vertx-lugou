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
 * Converter for {@link com.funmix.entity.Line}.
 *
 * NOTE: This class has been automatically generated from the {@link com.funmix.entity.Line} original class using Vert.x codegen.
 */
public class LineConverter {

  public static void fromJson(JsonObject json, Line obj) {
    if (json.getValue("drivername") instanceof String) {
      obj.setDrivername((String)json.getValue("drivername"));
    }
    if (json.getValue("endaddr") instanceof String) {
      obj.setEndaddr((String)json.getValue("endaddr"));
    }
    if (json.getValue("endtime") instanceof String) {
      obj.setEndtime((String)json.getValue("endtime"));
    }
    if (json.getValue("id") instanceof Number) {
      obj.setId(((Number)json.getValue("id")).intValue());
    }
    if (json.getValue("licenseplate") instanceof String) {
      obj.setLicenseplate((String)json.getValue("licenseplate"));
    }
    if (json.getValue("linename") instanceof String) {
      obj.setLinename((String)json.getValue("linename"));
    }
    if (json.getValue("path") instanceof String) {
      obj.setPath((String)json.getValue("path"));
    }
    if (json.getValue("process") instanceof Number) {
      obj.setProcess(((Number)json.getValue("process")).intValue());
    }
    if (json.getValue("startaddr") instanceof String) {
      obj.setStartaddr((String)json.getValue("startaddr"));
    }
    if (json.getValue("starttime") instanceof String) {
      obj.setStarttime((String)json.getValue("starttime"));
    }
    if (json.getValue("status") instanceof Number) {
      obj.setStatus(((Number)json.getValue("status")).intValue());
    }
  }

  public static void toJson(Line obj, JsonObject json) {
    if (obj.getDrivername() != null) {
      json.put("drivername", obj.getDrivername());
    }
    if (obj.getEndaddr() != null) {
      json.put("endaddr", obj.getEndaddr());
    }
    if (obj.getEndtime() != null) {
      json.put("endtime", obj.getEndtime());
    }
    json.put("id", obj.getId());
    if (obj.getLicenseplate() != null) {
      json.put("licenseplate", obj.getLicenseplate());
    }
    if (obj.getLinename() != null) {
      json.put("linename", obj.getLinename());
    }
    if (obj.getPath() != null) {
      json.put("path", obj.getPath());
    }
    json.put("process", obj.getProcess());
    if (obj.getStartaddr() != null) {
      json.put("startaddr", obj.getStartaddr());
    }
    if (obj.getStarttime() != null) {
      json.put("starttime", obj.getStarttime());
    }
    json.put("status", obj.getStatus());
  }
}