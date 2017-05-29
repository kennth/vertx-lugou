package com.funmix.service;

import java.util.Optional;

import com.funmix.entity.Line;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public interface LineService {

	/* (non-Javadoc)
	 * @see com.funmix.service.LineService#getAll(io.vertx.core.json.JsonObject)
	 */
	/* (non-Javadoc)
	 * @see com.funmix.service.LineServer#getAll(io.vertx.core.json.JsonObject)
	 */
	Future<JsonObject> getAll(JsonObject params);

	/* (non-Javadoc)
	 * @see com.funmix.service.LineService#getOne(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.funmix.service.LineServer#getOne(java.lang.String)
	 */
	Future<Optional<Line>> getOne(String id);

	/* (non-Javadoc)
	 * @see com.funmix.service.LineService#insert(io.vertx.core.json.JsonObject)
	 */
	/* (non-Javadoc)
	 * @see com.funmix.service.LineServer#insert(io.vertx.core.json.JsonObject)
	 */
	Future<Optional<Line>> insert(JsonObject json);

	/* (non-Javadoc)
	 * @see com.funmix.service.LineService#update(io.vertx.core.json.JsonObject)
	 */
	/* (non-Javadoc)
	 * @see com.funmix.service.LineServer#update(io.vertx.core.json.JsonObject)
	 */
	Future<Boolean> update(JsonObject params);

	/* (non-Javadoc)
	 * @see com.funmix.service.LineService#delete(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.funmix.service.LineServer#delete(java.lang.String)
	 */
	Future<Boolean> delete(String id);

}