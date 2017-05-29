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
 * Converter for {@link com.funmix.entity.User}.
 *
 * NOTE: This class has been automatically generated from the {@link com.funmix.entity.User} original class using Vert.x codegen.
 */
public class UserConverter {

  public static void fromJson(JsonObject json, User obj) {
    //if (json.getValue("access_status") instanceof Number) {
      obj.setAccess_status(((Number)json.getValue("access_status")).intValue());
    //}
    if (json.getValue("api_routers") instanceof String) {
      obj.setApi_routers((String)json.getValue("api_routers"));
    }
    //if (json.getValue("companyid") instanceof Number) {
      obj.setCompanyid(((Number)json.getValue("companyid")).intValue());
    //}
    if (json.getValue("created_time") instanceof String) {
      obj.setCreated_time((String)json.getValue("created_time"));
    }
    if (json.getValue("default_web_routers") instanceof String) {
      obj.setDefault_web_routers((String)json.getValue("default_web_routers"));
    }
    if (json.getValue("email") instanceof String) {
      obj.setEmail((String)json.getValue("email"));
    }
    //if (json.getValue("id") instanceof Number) {
      obj.setId(((Number)json.getValue("id")).intValue());
    //}
    if (json.getValue("password") instanceof String) {
      obj.setPassword((String)json.getValue("password"));
    }
    if (json.getValue("phone") instanceof String) {
      obj.setPhone((String)json.getValue("phone"));
    }
    //if (json.getValue("status") instanceof Number) {
      obj.setStatus(((Number)json.getValue("status")).intValue());
    //}
    if (json.getValue("token") instanceof String) {
      obj.setToken((String)json.getValue("token"));
    }
    if (json.getValue("username") instanceof String) {
      obj.setUsername((String)json.getValue("username"));
    }
    if (json.getValue("web_routers") instanceof String) {
      obj.setWeb_routers((String)json.getValue("web_routers"));
    }
  }

  public static void toJson(User obj, JsonObject json) {
    json.put("access_status", obj.getAccess_status());
    if (obj.getApi_routers() != null) {
      json.put("api_routers", obj.getApi_routers());
    }
    json.put("companyid", obj.getCompanyid());
    if (obj.getCreated_time() != null) {
      json.put("created_time", obj.getCreated_time());
    }
    if (obj.getDefault_web_routers() != null) {
      json.put("default_web_routers", obj.getDefault_web_routers());
    }
    if (obj.getEmail() != null) {
      json.put("email", obj.getEmail());
    }
    json.put("id", obj.getId());
    if (obj.getPassword() != null) {
      json.put("password", obj.getPassword());
    }
    if (obj.getPhone() != null) {
      json.put("phone", obj.getPhone());
    }
    json.put("status", obj.getStatus());
    if (obj.getToken() != null) {
      json.put("token", obj.getToken());
    }
    if (obj.getUsername() != null) {
      json.put("username", obj.getUsername());
    }
    if (obj.getWeb_routers() != null) {
      json.put("web_routers", obj.getWeb_routers());
    }
  }
}