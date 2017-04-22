package com.funmix.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

public class MainVerticleOld extends AbstractVerticle {
	private JDBCClient client;
	@Override
	public void start(Future<Void> fut) {
		JsonObject config = new JsonObject().put("provider_class", "io.vertx.ext.jdbc.spi.impl.HikariCPDataSourceProvider").put("jdbcUrl", "jdbc:mysql://192.168.99.10:3306/helper")
				.put("port", 3306).put("maxPoolSize", 10).put("username", "root").put("password", "funmix").put("database", "helper").put("charset", "UTF8")
				.put("queryTimeout", 30);
		client = JDBCClient.createShared(vertx, config);
		// Create a router object.
		Router router = Router.router(vertx);

		// Bind "/" to our hello message.
		router.route("/").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.putHeader("content-type", "text/html").end("<h1>Hello World!</h1>");
		});

		router.route("/assets/*").handler(StaticHandler.create("assets"));

		router.get("/user").handler(this::getUserTest);
		router.route("/user*").handler(BodyHandler.create());
		router.post("/user").handler(this::saveUser);

		// Create the HTTP server and pass the "accept" method to the request handler.
		vertx.createHttpServer().requestHandler(router::accept).listen(
				// Retrieve the port from the configuration, default to 8080.
				config().getInteger("http.port", 80), result -> {
					if (result.succeeded()) {
						fut.complete();
					} else {
						fut.fail(result.cause());
					}
				});
	}
	
	private void saveUser(RoutingContext routingContext) {
		JsonObject json = routingContext.getBodyAsJson();
		System.out.println(json);
		int pageNumber = json.getInteger("pageNumber").intValue() - 1;
		int pageSize = json.getInteger("pageSize").intValue();
		client.getConnection(res -> {
			if (res.succeeded()) {
				SQLConnection connection = res.result();
				connection.queryWithParams("select * from user limit ?,?", new JsonArray().add(pageNumber).add(pageSize), rs -> {
					if (rs.succeeded()) {
						routingContext.response().putHeader("content-type", "application/json; charset=utf-8")
								.end(Json.encodePrettily(new JsonObject().put("status", 200).put("data", new JsonObject().put("list", rs.result().getRows()))));
					} else {
						routingContext.response().putHeader("content-type", "application/json; charset=utf-8").end("query failed:" + rs.toString());
					}
				});
				connection.close();
			} else {
				routingContext.response().putHeader("content-type", "application/json; charset=utf-8").end("Failed to get connection");
			}
		});
	}
	
	private void getUserTest(RoutingContext routingContext) {
		client.getConnection(res -> {
			if (res.succeeded()) {
				SQLConnection connection = res.result();
				connection.query("select * from tuser", rs -> {
					if (rs.succeeded()) {
						routingContext.response().putHeader("content-type", "application/json; charset=utf-8")
								.end(Json.encodePrettily(new JsonObject().put("status", 200).put("data", new JsonObject().put("list", rs.result().getRows())))
						);
					} else {
						routingContext.response().putHeader("content-type", "application/json; charset=utf-8").end("query failed");
					}
				});
				connection.close();
			} else {
				routingContext.response().putHeader("content-type", "application/json; charset=utf-8").end("Failed to get connection");
			}
		});

	}

}
