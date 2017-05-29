package com.funmix.service;

import java.util.Optional;

import com.funmix.entity.Site;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public interface SiteService {

	Future<JsonObject> getAll(JsonObject params);

	Future<Optional<Site>> getOne(String id);

	Future<Optional<Site>> insert(JsonObject json);

	Future<Boolean> update(JsonObject params);

	Future<Boolean> delete(String id);

}