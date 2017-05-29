package com.funmix.service;

import java.util.List;
import java.util.Optional;

import com.funmix.entity.Company;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public interface CompanyService {
	Future<JsonObject> getAll(JsonObject params);
	Future<Optional<Company>> getOne(String userID);
	Future<Optional<Company>> insert(JsonObject json);
	Future<Boolean> update(JsonObject params);
	Future<Boolean> delete(String id);
}
