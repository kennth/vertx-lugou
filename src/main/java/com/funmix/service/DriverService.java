package com.funmix.service;

import java.util.Optional;

import com.funmix.entity.Driver;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public interface DriverService {

	Future<JsonObject> getAll(JsonObject params);

	Future<Optional<Driver>> getOne(String id);

	Future<Optional<Driver>> insert(JsonObject json);

	Future<Boolean> update(JsonObject params);

	Future<Boolean> delete(String id);

}