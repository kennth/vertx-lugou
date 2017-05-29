package com.funmix.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.funmix.common.Utils;
import com.funmix.entity.Driver;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.sql.UpdateResult;

public class DriverServiceImpl implements DriverService {
	private static final Logger log = LoggerFactory.getLogger(DriverServiceImpl.class);
			
	private final Vertx			vertx;
	private final JsonObject	config;
	private final JDBCClient	client;

	private static final String	SQL_INSERT		= "INSERT INTO tdriver (username,email,phone,driverlicense,memo,status) "
												+ "VALUES (?, ?, ?, ?, ?, ?)";
	private static final String	SQL_QUERY		= "SELECT * FROM tdriver WHERE id = ? limit 1";
	private static final String	SQL_QUERY_ALL	= "SELECT * FROM tdriver";
	private static final String	SQL_QUERY_COUNT	= "SELECT count(*) as count FROM tdriver";
	
	private static final String	SQL_UPDATE		= "UPDATE tdriver SET ";
	private static final String	SQL_DELETE		= "DELETE FROM `tdriver` WHERE id = ?";	

	public DriverServiceImpl(JsonObject config) {
		this(Vertx.vertx(), config);
	}

	public DriverServiceImpl(Vertx vertx, JsonObject config) {
		this.vertx = vertx;
		this.config = config;
		this.client = JDBCClient.createShared(vertx, config);
	}

	private Handler<AsyncResult<SQLConnection>> connHandler(Future<?> future, Handler<SQLConnection> handler) {
		return conn -> {
			if (conn.succeeded()) {
				final SQLConnection connection = conn.result();
				handler.handle(connection);
			} else {
				future.fail(conn.cause());
			}
		};
	}	
	
	/* (non-Javadoc)
	 * @see com.funmix.service.DriverService#getAll(io.vertx.core.json.JsonObject)
	 */
	@Override
	public Future<JsonObject> getAll(JsonObject params) {
		Future<JsonObject> result = Future.future();
		StringBuffer sqlCount = new StringBuffer(SQL_QUERY_COUNT);
		StringBuffer sqlQuery = new StringBuffer(SQL_QUERY_ALL);
		StringBuffer order = new StringBuffer();
		StringBuffer where = new StringBuffer();
		JsonArray queryparams = new JsonArray();
		JsonArray countparams = new JsonArray(); 
		params.forEach(r ->{
			String key = r.getKey();
			if(key.equals("orderby")){
				order.append(" order by ").append(r.getValue());
			}else if(key.equals("page")){				
			}else if(key.equals("page_size")){				
			}else{
				if(where.length()==0)
					where.append(" where ");
				else
					where.append(" and ");
				where.append(key).append(" like ? ");
				queryparams.add("%" + r.getValue() + "%");
				countparams.add("%" + r.getValue() + "%" );
			}
		});
		
		if(where.length()>0){
			sqlCount.append(where);
			sqlQuery.append(where);
		}		
		JsonObject jrs = new JsonObject();		
		client.getConnection(connHandler(result, connection -> {
			log.info(sqlCount);
			if(countparams.size() == 0)
				connection.query(sqlCount.toString(), r -> {				
					if (r.failed()) {
						result.fail(r.cause());
					} else {
						int count = r.result().getRows().get(0).getInteger("count");
						jrs.put("total", count);					
					}
				});		
			else
				connection.queryWithParams(sqlCount.toString(), countparams, r -> {					
					if (r.failed()) {
						result.fail(r.cause());
					} else {
						int count = r.result().getRows().get(0).getInteger("count");
						jrs.put("total", count);					
					}
				});
			if(order.length()>0){
				sqlQuery.append(order);
				queryparams.add(params.getString("orderby"));
			}
			if(params.getValue("page")==null){
				params.put("page", "0");
				params.put("page_size", "" + Integer.MAX_VALUE);
			}
			sqlQuery.append(" limit ?,? ");
			log.info(sqlQuery);
			int page = Integer.parseInt(params.getString("page"));
			int limit = Integer.parseInt(params.getString("page_size"));
			queryparams.add(Utils.calcPage(page,limit)).add(limit);			
			connection.queryWithParams(sqlQuery.toString(), queryparams, r -> {				
				if (r.failed()) {
					result.fail(r.cause());
				} else {					
					jrs.put("data", r.result().getRows().stream().map(Driver::new).collect(Collectors.toList()));	
					jrs.put("per_page", limit);
					jrs.put("current_page", page);					
				}
				result.complete(new JsonObject().put("status", 200).put("data", new JsonObject().put("list", jrs))); 
			});			
			connection.close();
			
		}));
		return result;
	}	

	/* (non-Javadoc)
	 * @see com.funmix.service.DriverService#getOne(java.lang.String)
	 */
	@Override
	public Future<Optional<Driver>> getOne(String id) {
		Future<Optional<Driver>> result = Future.future();
		client.getConnection(connHandler(result, connection -> {
			connection.queryWithParams(SQL_QUERY, new JsonArray().add(id), r -> {
				if (r.failed()) {
					result.fail(r.cause());
				} else {
					List<JsonObject> list = r.result().getRows();
					if (list == null || list.isEmpty()) {
						result.complete(Optional.empty());
					} else {
						result.complete(Optional.of(new Driver(list.get(0))));
					}
				}
				connection.close();
			});
		}));
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.funmix.service.DriverService#insert(io.vertx.core.json.JsonObject)
	 */
	@Override
	public Future<Optional<Driver>> insert(JsonObject json) {		
		Future<Optional<Driver>> result = Future.future();
		JsonArray params = new JsonArray();		
		//username,email,phone,driverlicense,memo,status
		params.add(json.getString("username"));
		params.add(json.getString("email"));
		params.add(json.getString("phone"));
		params.add(json.getString("driverlicense"));
		params.add(json.getString("memo"));
		params.add(json.getString("status"));
		client.getConnection(connHandler(result, connection -> {			
			connection.updateWithParams(SQL_INSERT, params,r -> {
				if (r.failed()) {
					result.fail(r.cause());
				} else {
					UpdateResult urs = r.result();
					params.clear();
					params.add(urs.getKeys().getInteger(0));
					log.info(urs.getKeys().getInteger(0));
					connection.queryWithParams(SQL_QUERY, params, rs -> {
						if (rs.failed()) {
							result.fail(rs.cause());
							log.info(rs.cause());
						} else {
							List<JsonObject> list = rs.result().getRows();
							if (list == null || list.isEmpty()) {
								result.complete(Optional.empty());
							} else {							
								result.complete(Optional.of(new Driver(list.get(0))));
							}
						}						
					});
				}
				connection.close();
			});			
		}));
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.funmix.service.DriverService#update(io.vertx.core.json.JsonObject)
	 */
	@Override
	public Future<Boolean> update(JsonObject params) {
		Future<Boolean> result = Future.future();
		int id = params.getInteger("id");
		JsonArray setparams = new JsonArray();
		StringBuffer sqlSet = new StringBuffer(); 
		params.forEach(r ->{
			if(!r.getKey().equals("id")){
				sqlSet.append(r.getKey()).append("=?,");
				setparams.add(r.getValue());
			}
		});
		if(sqlSet.length()>0){
			sqlSet.deleteCharAt(sqlSet.length()-1);
		}else{
			sqlSet.append("status=abs(status-1)");
		}
		sqlSet.append(" where id = ?");
		setparams.add(id);
		String sql = SQL_UPDATE + sqlSet.toString();
		log.info(sql);
		client.getConnection(connHandler(result, connection -> {
			connection.updateWithParams(sql, 
					setparams, r -> {
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
	
	/* (non-Javadoc)
	 * @see com.funmix.service.DriverService#delete(java.lang.String)
	 */
	@Override
	  public Future<Boolean> delete(String id) {
	    return deleteProcess(SQL_DELETE, new JsonArray().add(id));
	  }

}
