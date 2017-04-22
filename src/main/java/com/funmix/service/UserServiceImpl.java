package com.funmix.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.funmix.entity.User;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;

public class UserServiceImpl implements UserService {
	private final Vertx			vertx;
	private final JsonObject	config;
	private final JDBCClient	client;

	private static final String	SQL_INSERT		= "INSERT INTO `tuser` " + "(`title`, `completed`, `order`, `url`) VALUES (?, ?, ?, ?, ?)";
	private static final String	SQL_QUERY		= "SELECT * FROM tuser WHERE id = ? limit 1";
	private static final String	SQL_QUERY_ALL	= "SELECT * FROM tuser";
	private static final String	SQL_UPDATE		= "UPDATE `tuser`\n" + "SET\n" + "`id` = ?,\n" + "`title` = ?,\n" + "`completed` = ?,\n" + "`order` = ?,\n" + "`url` = ?\n"
			+ "WHERE `id` = ?;";
	private static final String	SQL_DELETE		= "DELETE FROM `tuser` WHERE `id` = ?";

	public UserServiceImpl(JsonObject config) {
		this(Vertx.vertx(), config);
	}

	public UserServiceImpl(Vertx vertx, JsonObject config) {
		this.vertx = vertx;
		this.config = config;
		this.client = JDBCClient.createShared(vertx, config);
	}

	private Handler<AsyncResult<SQLConnection>> connHandler(Future future, Handler<SQLConnection> handler) {
		return conn -> {
			if (conn.succeeded()) {
				final SQLConnection connection = conn.result();
				handler.handle(connection);
			} else {
				future.fail(conn.cause());
			}
		};
	}

	@Override
	public Future<List<User>> getAll() {
		Future<List<User>> result = Future.future();
		client.getConnection(connHandler(result, connection -> connection.query(SQL_QUERY_ALL, r -> {
			if (r.failed()) {
				result.fail(r.cause());
			} else {
				List<User> users = r.result().getRows().stream().map(User::new).collect(Collectors.toList());
				result.complete(users);
			}
			connection.close();
		})));
		return result;
	}

	@Override
	public Future<Optional<User>> getUser(String userID) {
		Future<Optional<User>> result = Future.future();
		client.getConnection(connHandler(result, connection -> {
			connection.queryWithParams(SQL_QUERY, new JsonArray().add(userID), r -> {
				if (r.failed()) {
					result.fail(r.cause());
				} else {
					List<JsonObject> list = r.result().getRows();
					if (list == null || list.isEmpty()) {
						result.complete(Optional.empty());
					} else {
						result.complete(Optional.of(new User(list.get(0))));
					}
				}
				connection.close();
			});
		}));
		return result;
	}
	
	

}
