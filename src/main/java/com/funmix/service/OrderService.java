package com.funmix.service;

import java.util.List;
import java.util.Optional;

import com.funmix.entity.Order;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public interface OrderService {
	Future<JsonObject> getAll(JsonObject params);
	Future<Optional<Order>> getOne(String id);
	Future<Optional<Order>> insert(JsonObject json);
	Future<Boolean> update(JsonObject params);
	Future<Boolean> delete(String id);
	Future<Optional<List<JsonObject>>> getOrderStatics();
}