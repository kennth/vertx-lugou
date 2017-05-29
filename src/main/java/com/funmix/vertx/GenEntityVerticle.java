package com.funmix.vertx;

import java.util.List;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class GenEntityVerticle extends AbstractVerticle {
	private JDBCClient client;

	@Override
	public void start(Future<Void> fut) {
		JsonObject config = new JsonObject()
				.put("provider_class", "io.vertx.ext.jdbc.spi.impl.HikariCPDataSourceProvider")
				.put("jdbcUrl", "jdbc:mysql://localhost:3306/helper").put("port", 3306).put("maxPoolSize", 10)
				.put("username", "root").put("password", "funmix").put("database", "helper").put("charset", "UTF8")
				.put("queryTimeout", 30);
		client = JDBCClient.createShared(vertx, config);
		// Create a router object.
		Router router = Router.router(vertx);

		// Bind "/" to our hello message.
		router.route("/").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.putHeader("content-type", "text/html").end("<h1>Hello World!</h1>");
		});
		router.route("/:tablename").handler(this::genEntity);
		// Create the HTTP server and pass the "accept" method to the request
		// handler.
		vertx.createHttpServer().requestHandler(router::accept).listen(
				// Retrieve the port from the configuration, default to 8080.
				config().getInteger("http.port", 7777), result -> {
					if (result.succeeded()) {
						fut.complete();
					} else {
						fut.fail(result.cause());
					}
				});
	}

	private void genEntity(RoutingContext routingContext) {
		String tablename = routingContext.request().getParam("tablename");
		client.getConnection(res -> {
			if (res.succeeded()) {
				SQLConnection connection = res.result();
				String sql = "SELECT column_name,data_type,numeric_precision FROM information_schema.COLUMNS where table_name=? ORDER BY ORDINAL_POSITION";
				connection.queryWithParams(sql, new JsonArray().add(tablename), rs -> {
					if (rs.succeeded()) {
						List<JsonObject> list = rs.result().getRows();
						StringBuffer out = new StringBuffer();
						String entity = tablename.substring(1, 2).toUpperCase() + tablename.substring(2);
						out.append("package com.funmix.entity;<br>");
						out.append(
								"import io.vertx.codegen.annotations.DataObject;<br>import io.vertx.core.json.JsonObject;<br>");
						out.append("@DataObject(generateConverter = true)<br>");
						out.append("public class ").append(entity).append(" {<br>");
						list.forEach(r -> {
							switch (r.getString("data_type")) {
							case "float":
							case "double":
							case "decimal":
								out.append("double");
								break;
							case "bigint":
								out.append("long");
								break;
							case "int":
								out.append("int");
								break;
							case "date":
								out.append("String");
								break;
							case "timestamp":
							case "datetime":
								out.append("String");
								break;
							default:
								out.append("String");
								break;
							}
							out.append(" ").append(r.getString("column_name")).append(";<br>");
						});
						out.append("public ").append(entity).append("(JsonObject obj) { ").append(entity)
								.append("Converter.fromJson(obj, this);}<br>");
						out.append("public ").append(entity).append("(String jsonStr) { ").append(entity)
								.append("Converter.fromJson(new JsonObject(jsonStr), this);}<br>");
						out.append("public JsonObject toJson() {JsonObject json = new JsonObject();").append(entity)
								.append("Converter.toJson(this, json);return json;}<br>");
						out.append("}");
						routingContext.response().putHeader("content-type", "text/html; charset=utf-8").end(out.toString());
					} else {
						routingContext.response().putHeader("content-type", "text/html; charset=utf-8").end(rs.cause().toString());
					}
				});
				connection.close();
			} else {
				routingContext.response().putHeader("content-type", "text/html; charset=utf-8").end(res.cause().toString());
			}
		});
	}
}
