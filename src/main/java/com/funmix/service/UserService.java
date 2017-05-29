package com.funmix.service;

import java.util.List;
import java.util.Optional;

import com.funmix.entity.User;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public interface UserService {	
	Future<Optional<User>> getOne(String userID);
	Future<JsonObject> getAll(JsonObject params);
	Future<Boolean> delete(String id);
	Future<Boolean> update(JsonObject params);
	Future<Optional<User>> login(String username, String password);
	Future<Optional<User>> insert(JsonObject json);
	 
}
