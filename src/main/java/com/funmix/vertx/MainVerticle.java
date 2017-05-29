package com.funmix.vertx;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import com.funmix.Constants;
import com.funmix.common.Utils;
import com.funmix.entity.*;
import com.funmix.service.*;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.StaticHandler;

public class MainVerticle extends AbstractVerticle {

	private static final Logger log = LoggerFactory.getLogger(MainVerticle.class);

	private static final String HOST = "0.0.0.0";
	private static final int PORT = 80;

	private UserService userService;
	private CompanyService companyService;
	private DriverService driverService;
	private TruckService truckService;
	private OrderService orderService;
	private ContactService contactService;
	private SiteService siteService;
	private LineService lineService;

	private void initData() {
		JsonObject config = new JsonObject()
				.put("provider_class", "io.vertx.ext.jdbc.spi.impl.HikariCPDataSourceProvider")
				.put("jdbcUrl", "jdbc:mysql://localhost:3306/helper?useUnicode=true&characterEncoding=UTF-8")
				.put("port", 3306).put("maxPoolSize", 10).put("username", "root").put("password", "funmix")
				.put("database", "helper").put("charset", "UTF8").put("queryTimeout", 30);
		userService = new UserServiceImpl(vertx, config);
		companyService = new CompanyServiceImpl(vertx, config);
		driverService = new DriverServiceImpl(vertx, config);
		truckService = new TruckServiceImpl(vertx, config);
		orderService = new OrderServiceImpl(vertx, config);
		contactService = new ContactServiceImpl(vertx, config);
		siteService = new SiteServiceImpl(vertx, config);
		lineService = new LineServiceImpl(vertx, config);
	}

	@Override
	public void start(Future<Void> future) throws Exception {
		initData();
		Router router = Router.router(vertx);
		// CORS support
		Set<String> allowHeaders = new HashSet<>();
		allowHeaders.add("x-requested-with");
		allowHeaders.add("Access-Control-Allow-Origin");
		allowHeaders.add("origin");
		allowHeaders.add("Content-Type");
		allowHeaders.add("accept");
		Set<HttpMethod> allowMethods = new HashSet<>();
		allowMethods.add(HttpMethod.GET);
		allowMethods.add(HttpMethod.POST);
		allowMethods.add(HttpMethod.DELETE);
		allowMethods.add(HttpMethod.PATCH);

		router.route().handler(BodyHandler.create());
		router.route().handler(CorsHandler.create("*").allowedHeaders(allowHeaders).allowedMethods(allowMethods));
		
		// routes -- login
		router.post(Constants.API_LOGIN).handler(this::handleLogin);
		
		// routes -- user
		router.get(Constants.API_USER_GET).handler(this::handleGetUser);
		router.get(Constants.API_USER_LIST).handler(this::handleUserGetAll);
		router.post(Constants.API_USER_CREATE).handler(this::handleCreateUser);
		router.patch(Constants.API_USER_UPDATE).handler(this::handleUpdateUser);
		router.delete(Constants.API_USER_DELETE).handler(this::handleDeleteUser);
		
		// routes -- company
		router.get(Constants.API_COMPANY_GET).handler(this::handleGetCompany);
		router.get(Constants.API_COMPANY_LIST).handler(this::handleCompanyGetAll);
		router.post(Constants.API_COMPANY_CREATE).handler(this::handleCreateCompany);
		router.patch(Constants.API_COMPANY_UPDATE).handler(this::handleUpdateCompany);
		router.delete(Constants.API_COMPANY_DELETE).handler(this::handleDeleteCompany);
		
		// routes -- driver
		router.get(Constants.API_DRIVER_GET).handler(this::handleGetDriver);
		router.get(Constants.API_DRIVER_LIST).handler(this::handleDriverGetAll);
		router.post(Constants.API_DRIVER_CREATE).handler(this::handleCreateDriver);
		router.patch(Constants.API_DRIVER_UPDATE).handler(this::handleUpdateDriver);
		router.delete(Constants.API_DRIVER_DELETE).handler(this::handleDeleteDriver);
		
		// routes -- truck
		router.get(Constants.API_TRUCK_GET).handler(this::handleGetTruck);
		router.get(Constants.API_TRUCK_LIST).handler(this::handleTruckGetAll);
		router.post(Constants.API_TRUCK_CREATE).handler(this::handleCreateTruck);
		router.patch(Constants.API_TRUCK_UPDATE).handler(this::handleUpdateTruck);
		router.delete(Constants.API_TRUCK_DELETE).handler(this::handleDeleteTruck);
		
		// routes -- order
		router.get(Constants.API_ORDER_GET).handler(this::handleGetOrder);
		router.get(Constants.API_ORDER_LIST).handler(this::handleOrderGetAll);
		router.post(Constants.API_ORDER_CREATE).handler(this::handleCreateOrder);
		router.patch(Constants.API_ORDER_UPDATE).handler(this::handleUpdateOrder);
		router.delete(Constants.API_ORDER_DELETE).handler(this::handleDeleteOrder);
				
		router.get(Constants.API_ORDER_STATICS).handler(this::handleGetOrderStatics);
		// routes -- contact
		router.get(Constants.API_CONTACT_LIST).handler(this::handleContactGetAll);
		router.post(Constants.API_CONTACT_CREATE).handler(this::handleCreateContact);
		router.patch(Constants.API_CONTACT_UPDATE).handler(this::handleUpdateContact);
		router.delete(Constants.API_CONTACT_DELETE).handler(this::handleDeleteContact);
		
		// routes -- site
		router.get(Constants.API_SITE_GET).handler(this::handleGetSite);
		router.get(Constants.API_SITE_LIST).handler(this::handleSiteGetAll);
		router.post(Constants.API_SITE_CREATE).handler(this::handleCreateSite);
		router.patch(Constants.API_SITE_UPDATE).handler(this::handleUpdateSite);
		router.delete(Constants.API_SITE_DELETE).handler(this::handleDeleteSite);
		
		// routes -- line
		router.get(Constants.API_LINE_GET).handler(this::handleGetLine);
		router.get(Constants.API_LINE_LIST).handler(this::handleLineGetAll);
		router.post(Constants.API_LINE_CREATE).handler(this::handleCreateLine);
		router.patch(Constants.API_LINE_UPDATE).handler(this::handleUpdateLine);
		router.delete(Constants.API_LINE_DELETE).handler(this::handleDeleteLine);

		vertx.createHttpServer().requestHandler(router::accept).listen(config().getInteger("http.port", PORT),
				config().getString("http.address", HOST), result -> {
					if (result.succeeded())
						future.complete();
					else
						future.fail(result.cause());
				});
	}
	
	//login
	private void handleLogin(RoutingContext context) {
		JsonObject params = context.getBodyAsJson();		
		if( params.getValue("username") != null && params.getValue("password") != null){
			userService.login(params.getString("username"),params.getString("password")).setHandler(resultHandler(context, res -> {
				if (!res.isPresent())
					sendMessage(1,"用户名或者密码不正确!",context);
				else {
					final String encoded = Json.encodePrettily(new JsonObject().put("status", 200)
							.put("data", new JsonObject().put("userinfo", res.get().toJson())));
					context.response().putHeader("content-type", "application/json").end(encoded);
				}
			}));
		}else{
			sendError(400, context.response());
		}		
	}
	
	//user start
	private void handleUpdateUser(RoutingContext context) {		
		JsonObject params = context.getBodyAsJson();
		userService.update(params).setHandler(resultHandler(context, res->{
			if(res){
				context.response().setStatusCode(200).putHeader("content-type", "application/json").end(Json.encodePrettily(
					new JsonObject().put("status", 200).put("data", "")
				));
			}else{
				sendError(1, context.response());
			}
		}));
	}
	
	private void handleDeleteUser(RoutingContext context) {		
		String userID = context.request().getParam("userId");
		userService.delete(userID).setHandler(resultHandler(context, res->{
			if(res){
				context.response().setStatusCode(201).putHeader("content-type", "application/json").end(Json.encodePrettily(
						new JsonObject().put("status", 200).put("data", userID)
				));
			}else{
				sendError(1, context.response());
			}
		}));
	}
	
	private void handleCreateUser(RoutingContext context) {
		try {
			userService.insert(context.getBodyAsJson()).setHandler(resultHandler(context, res -> {
				if (res.isPresent()){
					System.out.println("res:" + res.get());
					JsonObject data = new JsonObject().put("status", 200).put("userinfo", res.get().toJson());
					context.response().setStatusCode(201).putHeader("content-type", "application/json").end(Json.encodePrettily(data));
				} else {
					serviceUnavailable(context);
				}
			}));
		} catch (DecodeException e) {
			sendError(400, context.response());
		}
	}

	private void handleGetUser(RoutingContext context) {
		String userID = context.request().getParam("userId");
		if (userID == null) {
			sendError(400, context.response());
			return;
		}
		userService.getOne(userID).setHandler(resultHandler(context, res -> {
			if (!res.isPresent())
				notFound(context);
			else {
				final String encoded = Json.encodePrettily(new JsonObject().put("status", 200)
						.put("data", new JsonObject().put("userinfo", res.get().toJson())));
				context.response().putHeader("content-type", "application/json").end(encoded);
			}
		}));
	}

	private void handleUserGetAll(RoutingContext context) {
		JsonObject params = new JsonObject();
		context.request().params().forEach(r->{
			params.put(r.getKey(), r.getValue());
		});
		userService.getAll(params).setHandler(resultHandler(context, res -> {
			if (res == null) {
				serviceUnavailable(context);
			} else {
				context.response().putHeader("content-type", "application/json").end(Json.encodePrettily(res));
			}
		}));
	}
	//user end
	
	//company start
	private void handleUpdateCompany(RoutingContext context) {		
		JsonObject params = context.getBodyAsJson();
		companyService.update(params).setHandler(resultHandler(context, res->{
			if(res){
				context.response().setStatusCode(200).putHeader("content-type", "application/json").end(Json.encodePrettily(
					new JsonObject().put("status", 200).put("msg", "修改成功!")
				));
			}else{
				sendError(1, context.response());
			}
		}));
	}
	
	private void handleDeleteCompany(RoutingContext context) {		
		String companyID = context.request().getParam("companyId");
		companyService.delete(companyID).setHandler(resultHandler(context, res->{
			if(res){
				context.response().setStatusCode(201).putHeader("content-type", "application/json").end(Json.encodePrettily(
						new JsonObject().put("status", 200).put("data", companyID)
				));
			}else{
				sendError(1, context.response());
			}
		}));
	}
	
	private void handleCreateCompany(RoutingContext context) {
		try {				
			companyService.insert(context.getBodyAsJson()).setHandler(resultHandler(context, res -> {
				if (res.isPresent()){
					JsonObject data = new JsonObject().put("status", 200).put("companyinfo", res.get().toJson());
					context.response().setStatusCode(201).putHeader("content-type", "application/json").end(Json.encodePrettily(data));
				} else {
					serviceUnavailable(context);
				}
			}));
		} catch (DecodeException e) {
			sendError(400, context.response());
		}
	}

	private void handleGetCompany(RoutingContext context) {
		String companyID = context.request().getParam("companyId");
		if (companyID == null) {
			sendError(400, context.response());
			return;
		}
		companyService.getOne(companyID).setHandler(resultHandler(context, res -> {
			if (!res.isPresent())
				notFound(context);
			else {
				final String encoded = Json.encodePrettily(new JsonObject().put("status", 200)
						.put("data", new JsonObject().put("companyinfo", res.get().toJson())));
				context.response().putHeader("content-type", "application/json").end(encoded);
			}
		}));
	}

	private void handleCompanyGetAll(RoutingContext context) {
		JsonObject params = new JsonObject();
		context.request().params().forEach(r->{
			params.put(r.getKey(), r.getValue());
		});
		companyService.getAll(params).setHandler(resultHandler(context, res -> {
			if (res == null) {
				serviceUnavailable(context);
			} else {
				context.response().putHeader("content-type", "application/json").end(Json.encodePrettily(res));
			}
		}));
	}
	//company end

	//driver start
	private void handleUpdateDriver(RoutingContext context) {		
		JsonObject params = context.getBodyAsJson();
		driverService.update(params).setHandler(resultHandler(context, res->{
			if(res){
				context.response().setStatusCode(200).putHeader("content-type", "application/json").end(Json.encodePrettily(
					new JsonObject().put("status", 200).put("msg", "修改成功!")
				));
			}else{
				sendError(1, context.response());
			}
		}));
	}
	
	private void handleDeleteDriver(RoutingContext context) {		
		String driverID = context.request().getParam("driverId");
		driverService.delete(driverID).setHandler(resultHandler(context, res->{
			if(res){
				context.response().setStatusCode(201).putHeader("content-type", "application/json").end(Json.encodePrettily(
						new JsonObject().put("status", 200).put("data", driverID)
				));
			}else{
				sendError(1, context.response());
			}
		}));
	}
	
	private void handleCreateDriver(RoutingContext context) {
		try {				
			driverService.insert(context.getBodyAsJson()).setHandler(resultHandler(context, res -> {
				if (res.isPresent()){
					JsonObject data = new JsonObject().put("status", 200).put("driverinfo", res.get().toJson());
					context.response().setStatusCode(201).putHeader("content-type", "application/json").end(Json.encodePrettily(data));
				} else {
					serviceUnavailable(context);
				}
			}));
		} catch (DecodeException e) {
			sendError(400, context.response());
		}
	}

	private void handleGetDriver(RoutingContext context) {
		String driverID = context.request().getParam("driverId");
		if (driverID == null) {
			sendError(400, context.response());
			return;
		}
		driverService.getOne(driverID).setHandler(resultHandler(context, res -> {
			if (!res.isPresent())
				notFound(context);
			else {
				final String encoded = Json.encodePrettily(new JsonObject().put("status", 200)
						.put("data", new JsonObject().put("driverinfo", res.get().toJson())));
				context.response().putHeader("content-type", "application/json").end(encoded);
			}
		}));
	}

	private void handleDriverGetAll(RoutingContext context) {
		JsonObject params = new JsonObject();
		context.request().params().forEach(r->{
			params.put(r.getKey(), r.getValue());
		});
		driverService.getAll(params).setHandler(resultHandler(context, res -> {
			if (res == null) {
				serviceUnavailable(context);
			} else {
				context.response().putHeader("content-type", "application/json").end(Json.encodePrettily(res));
			}
		}));
	}
	//driver end
	
	//truck start
	private void handleUpdateTruck(RoutingContext context) {		
		JsonObject params = context.getBodyAsJson();
		truckService.update(params).setHandler(resultHandler(context, res->{
			if(res){
				context.response().setStatusCode(200).putHeader("content-type", "application/json").end(Json.encodePrettily(
					new JsonObject().put("status", 200).put("msg", "修改成功!")
				));
			}else{
				sendError(1, context.response());
			}
		}));
	}
	
	private void handleDeleteTruck(RoutingContext context) {		
		String truckID = context.request().getParam("truckId");
		truckService.delete(truckID).setHandler(resultHandler(context, res->{
			if(res){
				context.response().setStatusCode(201).putHeader("content-type", "application/json").end(Json.encodePrettily(
						new JsonObject().put("status", 200).put("data", truckID)
				));
			}else{
				sendError(1, context.response());
			}
		}));
	}
	
	private void handleCreateTruck(RoutingContext context) {
		try {				
			truckService.insert(context.getBodyAsJson()).setHandler(resultHandler(context, res -> {
				if (res.isPresent()){
					JsonObject data = new JsonObject().put("status", 200).put("truckinfo", res.get().toJson());
					context.response().setStatusCode(201).putHeader("content-type", "application/json").end(Json.encodePrettily(data));
				} else {
					serviceUnavailable(context);
				}
			}));
		} catch (DecodeException e) {
			sendError(400, context.response());
		}
	}

	private void handleGetTruck(RoutingContext context) {
		String truckID = context.request().getParam("truckId");
		if (truckID == null) {
			sendError(400, context.response());
			return;
		}
		truckService.getOne(truckID).setHandler(resultHandler(context, res -> {
			if (!res.isPresent())
				notFound(context);
			else {
				final String encoded = Json.encodePrettily(new JsonObject().put("status", 200)
						.put("data", new JsonObject().put("truckinfo", res.get().toJson())));
				context.response().putHeader("content-type", "application/json").end(encoded);
			}
		}));
	}

	private void handleTruckGetAll(RoutingContext context) {
		JsonObject params = new JsonObject();
		context.request().params().forEach(r->{
			params.put(r.getKey(), r.getValue());
		});
		truckService.getAll(params).setHandler(resultHandler(context, res -> {
			if (res == null) {
				serviceUnavailable(context);
			} else {
				context.response().putHeader("content-type", "application/json").end(Json.encodePrettily(res));
			}
		}));
	}
	//truck end	
	
	//order start
	private void handleUpdateOrder(RoutingContext context) {		
		JsonObject params = context.getBodyAsJson();
		orderService.update(params).setHandler(resultHandler(context, res->{
			if(res){
				context.response().setStatusCode(200).putHeader("content-type", "application/json").end(Json.encodePrettily(
					new JsonObject().put("status", 200).put("msg", "修改成功!")
				));
			}else{
				sendError(1, context.response());
			}
		}));
	}
	
	private void handleDeleteOrder(RoutingContext context) {		
		String orderID = context.request().getParam("orderId");
		orderService.delete(orderID).setHandler(resultHandler(context, res->{
			if(res){
				context.response().setStatusCode(201).putHeader("content-type", "application/json").end(Json.encodePrettily(
						new JsonObject().put("status", 200).put("data", orderID)
				));
			}else{
				sendError(1, context.response());
			}
		}));
	}
	
	private void handleCreateOrder(RoutingContext context) {
		try {				
			orderService.insert(context.getBodyAsJson()).setHandler(resultHandler(context, res -> {
				if (res.isPresent()){
					JsonObject data = new JsonObject().put("status", 200).put("orderinfo", res.get().toJson());
					context.response().setStatusCode(201).putHeader("content-type", "application/json").end(Json.encodePrettily(data));
				} else {
					serviceUnavailable(context);
				}
			}));
		} catch (DecodeException e) {
			sendError(400, context.response());
		}
	}

	private void handleGetOrder(RoutingContext context) {
		String orderID = context.request().getParam("orderId");
		if (orderID == null) {
			sendError(400, context.response());
			return;
		}
		orderService.getOne(orderID).setHandler(resultHandler(context, res -> {
			if (!res.isPresent())
				notFound(context);
			else {
				final String encoded = Json.encodePrettily(new JsonObject().put("status", 200)
						.put("data", new JsonObject().put("orderinfo", res.get().toJson())));
				context.response().putHeader("content-type", "application/json").end(encoded);
			}
		}));
	}

	private void handleOrderGetAll(RoutingContext context) {
		JsonObject params = new JsonObject();
		context.request().params().forEach(r->{
			params.put(r.getKey(), r.getValue());
		});
		orderService.getAll(params).setHandler(resultHandler(context, res -> {
			if (res == null) {
				serviceUnavailable(context);
			} else {
				context.response().putHeader("content-type", "application/json").end(Json.encodePrettily(res));
			}
		}));
	}
	
	private void handleGetOrderStatics(RoutingContext context) {
		orderService.getOrderStatics().setHandler(resultHandler(context, res -> {
			if (!res.isPresent())
				notFound(context);
			else {
				final String encoded = Json.encodePrettily(new JsonObject().put("status", 200)
						.put("data", res.get()) );
				context.response().putHeader("content-type", "application/json").end(encoded);
			}
		}));
	}
	//order end	
	
	//site start
	private void handleUpdateSite(RoutingContext context) {		
		JsonObject params = context.getBodyAsJson();
		siteService.update(params).setHandler(resultHandler(context, res->{
			if(res){
				context.response().setStatusCode(200).putHeader("content-type", "application/json").end(Json.encodePrettily(
					new JsonObject().put("status", 200).put("msg", "修改成功!")
				));
			}else{
				sendError(1, context.response());
			}
		}));
	}
	
	private void handleDeleteSite(RoutingContext context) {		
		String siteID = context.request().getParam("siteId");
		siteService.delete(siteID).setHandler(resultHandler(context, res->{
			if(res){
				context.response().setStatusCode(201).putHeader("content-type", "application/json").end(Json.encodePrettily(
						new JsonObject().put("status", 200).put("data", siteID)
				));
			}else{
				sendError(1, context.response());
			}
		}));
	}
	
	private void handleCreateSite(RoutingContext context) {
		try {				
			siteService.insert(context.getBodyAsJson()).setHandler(resultHandler(context, res -> {
				if (res.isPresent()){
					JsonObject data = new JsonObject().put("status", 200).put("siteinfo", res.get().toJson());
					context.response().setStatusCode(201).putHeader("content-type", "application/json").end(Json.encodePrettily(data));
				} else {
					serviceUnavailable(context);
				}
			}));
		} catch (DecodeException e) {
			sendError(400, context.response());
		}
	}

	private void handleGetSite(RoutingContext context) {
		String siteID = context.request().getParam("siteId");
		if (siteID == null) {
			sendError(400, context.response());
			return;
		}
		siteService.getOne(siteID).setHandler(resultHandler(context, res -> {
			if (!res.isPresent())
				notFound(context);
			else {
				final String encoded = Json.encodePrettily(new JsonObject().put("status", 200)
						.put("data", new JsonObject().put("siteinfo", res.get().toJson())));
				context.response().putHeader("content-type", "application/json").end(encoded);
			}
		}));
	}

	private void handleSiteGetAll(RoutingContext context) {
		JsonObject params = new JsonObject();
		context.request().params().forEach(r->{
			params.put(r.getKey(), r.getValue());
		});
		siteService.getAll(params).setHandler(resultHandler(context, res -> {
			if (res == null) {
				serviceUnavailable(context);
			} else {
				context.response().putHeader("content-type", "application/json").end(Json.encodePrettily(res));
			}
		}));
	}
	//site end
	
	//line start
	private void handleUpdateLine(RoutingContext context) {		
		JsonObject params = context.getBodyAsJson();
		lineService.update(params).setHandler(resultHandler(context, res->{
			if(res){
				context.response().setStatusCode(200).putHeader("content-type", "application/json").end(Json.encodePrettily(
					new JsonObject().put("status", 200).put("msg", "修改成功!")
				));
			}else{
				sendError(1, context.response());
			}
		}));
	}
	
	private void handleDeleteLine(RoutingContext context) {		
		String lineID = context.request().getParam("lineId");
		lineService.delete(lineID).setHandler(resultHandler(context, res->{
			if(res){
				context.response().setStatusCode(201).putHeader("content-type", "application/json").end(Json.encodePrettily(
						new JsonObject().put("status", 200).put("data", lineID)
				));
			}else{
				sendError(1, context.response());
			}
		}));
	}
	
	private void handleCreateLine(RoutingContext context) {
		try {				
			lineService.insert(context.getBodyAsJson()).setHandler(resultHandler(context, res -> {
				if (res.isPresent()){
					JsonObject data = new JsonObject().put("status", 200).put("lineinfo", res.get().toJson());
					context.response().setStatusCode(201).putHeader("content-type", "application/json").end(Json.encodePrettily(data));
				} else {
					serviceUnavailable(context);
				}
			}));
		} catch (DecodeException e) {
			sendError(400, context.response());
		}
	}

	private void handleGetLine(RoutingContext context) {
		String lineID = context.request().getParam("lineId");
		if (lineID == null) {
			sendError(400, context.response());
			return;
		}
		lineService.getOne(lineID).setHandler(resultHandler(context, res -> {
			if (!res.isPresent())
				notFound(context);
			else {
				final String encoded = Json.encodePrettily(new JsonObject().put("status", 200)
						.put("data", new JsonObject().put("lineinfo", res.get().toJson())));
				context.response().putHeader("content-type", "application/json").end(encoded);
			}
		}));
	}

	private void handleLineGetAll(RoutingContext context) {
		JsonObject params = new JsonObject();
		context.request().params().forEach(r->{
			params.put(r.getKey(), r.getValue());
		});
		lineService.getAll(params).setHandler(resultHandler(context, res -> {
			if (res == null) {
				serviceUnavailable(context);
			} else {
				context.response().putHeader("content-type", "application/json").end(Json.encodePrettily(res));
			}
		}));
	}
	//line end	
	
	//contact start
	private void handleCreateContact(RoutingContext context) {
		try {
			System.out.println(context.getBodyAsString());
			final Contact contact = new Contact(new JsonObject(context.getBodyAsString()));
			final String encoded = Json.encodePrettily(contact);
			contactService.insert(contact).setHandler(resultHandler(context, res -> {
				if (res) {
					context.response().setStatusCode(201).putHeader("content-type", "application/json").end(encoded);
				} else {
					serviceUnavailable(context);
				}
			}));
		} catch (DecodeException e) {
			sendError(400, context.response());
		}
	}

	private void handleContactGetAll(RoutingContext context) {
		int page = Integer.parseInt(context.request().getParam("page"));
		int limit = Integer.parseInt(context.request().getParam("limit"));
		String orderby = context.request().getParam("orderby");
		JsonArray params = new JsonArray();
		params.add(orderby).add(Utils.calcPage(page, limit)).add(limit);
		contactService.getAll(params).setHandler(resultHandler(context, res -> {
			if (res == null) {
				serviceUnavailable(context);
			} else {
				context.response().putHeader("content-type", "application/json").end(Json.encodePrettily(res));
			}
		}));
	}

	private void handleUpdateContact(RoutingContext context) {
		try {
			System.out.println(context.getBodyAsString());
			final Contact contact = new Contact(new JsonObject(context.getBodyAsString()));
			final String encoded = Json.encodePrettily(contact);
			contactService.update(contact).setHandler(resultHandler(context, res -> {
				if (res) {
					context.response().setStatusCode(201).putHeader("content-type", "application/json").end(encoded);
				} else {
					serviceUnavailable(context);
				}
			}));
		} catch (DecodeException e) {
			badRequest(context);
		}
	}

	private void handleDeleteContact(RoutingContext context) {
		String contactID = context.request().getParam("contactID");
		contactService.delete(contactID).setHandler(deleteResultHandler(context));
	}
	
	//contact end
	/**
	 * Wrap the result handler with failure handler (503 Service Unavailable)
	 */
	private <T> Handler<AsyncResult<T>> resultHandler(RoutingContext context, Consumer<T> consumer) {
		return res -> {
			if (res.succeeded()) {
				consumer.accept(res.result());
			} else {
				serviceUnavailable(context);
			}
		};
	}

	private Handler<AsyncResult<Boolean>> deleteResultHandler(RoutingContext context) {
		return res -> {
			if (res.succeeded()) {
				if (res.result()) {
					context.response().setStatusCode(204).end();
				} else {
					serviceUnavailable(context);
				}
			} else {
				serviceUnavailable(context);
			}
		};
	}

	private void sendError(int statusCode, HttpServerResponse response) {
		response.setStatusCode(statusCode).end("{status:"+ statusCode + ",msg:'" + statusCode + "'}");
	}

	private void notFound(RoutingContext context) {
		context.response().setStatusCode(404).end("{status:404,msg:'404'}");
	}

	private void badRequest(RoutingContext context) {
		context.response().setStatusCode(400).end("{status:400,msg:'400'}");
	}

	private void serviceUnavailable(RoutingContext context) {
		context.response().setStatusCode(503).end("{status:503,msg:'503'}");
	}
	
	private void sendMessage(int status,String msg,RoutingContext context){
		context.response().setStatusCode(200).putHeader("content-type", "application/json").end(Json.encodePrettily(
				new JsonObject().put("status", status).put("msg", msg)
			));
	}

}
