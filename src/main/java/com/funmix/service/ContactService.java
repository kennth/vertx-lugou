package com.funmix.service;

import java.util.List;
import java.util.Optional;

import com.funmix.entity.Contact;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public interface ContactService {

	Future<JsonObject> getAll(JsonArray params);
	Future<Boolean> insert(Contact contact);
	Future<Boolean> update(Contact contact);
	Future<Boolean> delete(String todoId);
	Future<Optional<Contact>> getOne(String contactID);
}
