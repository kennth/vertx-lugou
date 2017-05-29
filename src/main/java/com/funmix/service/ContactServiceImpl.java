package com.funmix.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.funmix.entity.Contact;
import com.funmix.entity.User;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;

public class ContactServiceImpl implements ContactService {
	private final Vertx			vertx;
	private final JsonObject	config;
	private final JDBCClient	client;

	private static final String	SQL_INSERT		= "INSERT INTO tcontact " + "(company,contact,phone,fhdz,htzq,memo,rate) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String	SQL_QUERY		= "SELECT * FROM tcontact WHERE id = ? limit 1";
	private static final String	SQL_QUERY_ALL	= "SELECT * FROM tcontact order by ? limit ?,?";
	private static final String	SQL_QUERY_COUNT	= "SELECT count(*) as count FROM tcontact";
	private static final String	SQL_UPDATE		= "UPDATE tcontact set company=?,contact=?,phone=?,fhdz=?,htzq=?,memo=?,rate=? WHERE id = ?";
	private static final String	SQL_DELETE		= "DELETE FROM tcontact WHERE id = ?";

	public ContactServiceImpl(JsonObject config) {
		this(Vertx.vertx(), config);
	}

	public ContactServiceImpl(Vertx vertx, JsonObject config) {
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
	
	public Future<List<User>> getAll() {
		Future<List<User>> result = Future.future();
		client.getConnection(connHandler(result, connection -> {
			
			connection.query(SQL_QUERY_ALL, r -> {
				if (r.failed()) {
					result.fail(r.cause());
				} else {
					List<User> users = r.result().getRows().stream().map(User::new).collect(Collectors.toList());
					result.complete(users);
				}				
			});
			connection.close();
		}));
		return result;
	}
	
	@Override
	public Future<JsonObject> getAll(JsonArray params) {
		Future<JsonObject> result = Future.future();
		System.out.println(Json.encodePrettily(params));
		JsonObject jrs = new JsonObject();		
		client.getConnection(connHandler(result, connection -> {
			connection.query(SQL_QUERY_COUNT, r -> {				
				if (r.failed()) {
					System.out.println(r.cause());
					result.fail(r.cause());
				} else {
					int count = r.result().getRows().get(0).getInteger("count");
					System.out.println(count);
					jrs.put("count", count);					
				}
				//result.complete(jrs);
			});					
			connection.queryWithParams(SQL_QUERY_ALL, params, r -> {				
				if (r.failed()) {
					System.out.println(r.cause());
					result.fail(r.cause());
				} else {					
					jrs.put("data", r.result().getRows().stream().map(Contact::new).collect(Collectors.toList()));					
				}
				result.complete(jrs); 
			});		
			
			connection.close();
			
		}));
		return result;
	}	

	@Override
	public Future<Optional<Contact>> getOne(String contactID) {
		Future<Optional<Contact>> result = Future.future();
		client.getConnection(connHandler(result, connection -> {
			connection.queryWithParams(SQL_QUERY, new JsonArray().add(contactID), r -> {
				if (r.failed()) {
					result.fail(r.cause());
				} else {
					List<JsonObject> list = r.result().getRows();
					if (list == null || list.isEmpty()) {
						result.complete(Optional.empty());
					} else {
						result.complete(Optional.of(new Contact(list.get(0))));
					}
				}
				connection.close();
			});
		}));
		return result;
	}
	
	@Override
	public Future<Boolean> insert(Contact contact) {
		Future<Boolean> result = Future.future();
		client.getConnection(connHandler(result, connection -> {
			connection.updateWithParams(SQL_INSERT, 
				new JsonArray()
					.add(contact.getCompany())
					.add(contact.getContact())
					.add(contact.getPhone())
					.add(contact.getFhdz())
					.add(contact.getHtzq())
					.add(contact.getMemo())
					.add(contact.getRate()), r -> {
						if (r.failed()) {
							result.fail(r.cause());
						} else {
							result.complete(true);
						}
						connection.close();
					});
		}));
		return result;
	}
	
	@Override
	public Future<Boolean> update(Contact contact) {
		Future<Boolean> result = Future.future();
		client.getConnection(connHandler(result, connection -> {
			connection.updateWithParams(SQL_UPDATE, 
				new JsonArray()
					.add(contact.getCompany())
					.add(contact.getContact())
					.add(contact.getPhone())
					.add(contact.getFhdz())
					.add(contact.getHtzq())
					.add(contact.getMemo())
					.add(contact.getRate())
					.add(contact.getId()), r -> {
						if (r.failed()) {
							result.fail(r.cause());
						} else {
							result.complete(true);
						}
						connection.close();
					});
		}));
		return result;
	}
	
	
	private Future<Boolean> deleteProcess(String sql, JsonArray params) {
	    Future<Boolean> result = Future.future();
	    client.getConnection(connHandler(result, connection ->
	      connection.updateWithParams(sql, params, r -> {
	        if (r.failed()) {
	          result.complete(false);
	        } else {
	          result.complete(true);
	        }
	        connection.close();
	      })));
	    return result;
	  }
	
	@Override
	  public Future<Boolean> delete(String todoId) {
	    return deleteProcess(SQL_DELETE, new JsonArray().add(todoId));
	  }
}
