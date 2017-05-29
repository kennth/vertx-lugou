package com.funmix.service;

import java.util.Optional;

import com.funmix.entity.Truck;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public interface TruckService {

	/* (non-Javadoc)
	 * @see com.funmix.service.TruckService#getAll(io.vertx.core.json.JsonObject)
	 */
	Future<JsonObject> getAll(JsonObject params);

	/* (non-Javadoc)
	 * @see com.funmix.service.TruckService#getOne(java.lang.String)
	 */
	Future<Optional<Truck>> getOne(String id);

	/* (non-Javadoc)
	 * @see com.funmix.service.TruckService#insert(io.vertx.core.json.JsonObject)
	 */
	Future<Optional<Truck>> insert(JsonObject json);

	/* (non-Javadoc)
	 * @see com.funmix.service.TruckService#update(io.vertx.core.json.JsonObject)
	 */
	Future<Boolean> update(JsonObject params);

	/* (non-Javadoc)
	 * @see com.funmix.service.TruckService#delete(java.lang.String)
	 */
	Future<Boolean> delete(String id);

}