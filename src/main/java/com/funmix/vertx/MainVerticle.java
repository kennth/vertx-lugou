package com.funmix.vertx;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import com.funmix.Constants;
import com.funmix.entity.Contact;
import com.funmix.entity.User;
import com.funmix.service.ContactService;
import com.funmix.service.ContactServiceImpl;
import com.funmix.service.UserService;
import com.funmix.service.UserServiceImpl;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;

public class MainVerticle extends AbstractVerticle {
	
	private static final Logger log = LoggerFactory.getLogger(MainVerticle.class);

	  private static final String HOST = "0.0.0.0";
	  private static final int PORT = 8888;

	  private UserService userService;
	  private ContactService contactService;

	  private void initData() {
		  JsonObject config = new JsonObject().put("provider_class", "io.vertx.ext.jdbc.spi.impl.HikariCPDataSourceProvider").put("jdbcUrl", "jdbc:mysql://192.168.99.10:3306/helper?useUnicode=true&characterEncoding=UTF-8")
					.put("port", 3306).put("maxPoolSize", 10).put("username", "root").put("password", "funmix").put("database", "helper").put("charset", "UTF8")
					.put("queryTimeout", 30);
		  userService = new UserServiceImpl(vertx, config);
		  contactService = new ContactServiceImpl(vertx, config);
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

	    // routes
	    router.get(Constants.API_USER_GET).handler(this::handleGetUser);
	    router.get(Constants.API_USER_LIST_ALL).handler(this::handleUserGetAll);
	    router.get(Constants.API_CONTACT_LIST_ALL).handler(this::handleContactGetAll);
	    //router.post(Constants.API_USER_CREATE).handler(this::handleCreateUser);
	    router.post(Constants.API_CONTACT_CREATE).handler(this::handleCreateContact);
	    router.put(Constants.API_CONTACT_UPDATE).handler(this::handleUpdateContact);
	    router.delete(Constants.API_CONTACT_DELETE).handler(this::handleDeleteContact);
//	    router.delete(Constants.API_DELETE_ALL).handler(this::handleDeleteAll);

		vertx.createHttpServer().requestHandler(router::accept).listen(config().getInteger("http.port", PORT), config().getString("http.address", HOST), result -> {
			if (result.succeeded())
				future.complete();
			else
				future.fail(result.cause());
		});
	}

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

	private void handleGetUser(RoutingContext context) {
		String userID = context.request().getParam("userId");
		if (userID == null) {
			sendError(400, context.response());
			return;
		}

		userService.getUser(userID).setHandler(resultHandler(context, res -> {
			if (!res.isPresent())
				notFound(context);
			else {
				final String encoded = Json.encodePrettily(res.get());
				context.response().putHeader("content-type", "application/json").end(encoded);
			}
		}));
	}

	private void handleUserGetAll(RoutingContext context) {
		userService.getAll().setHandler(resultHandler(context, res -> {
			if (res == null) {
				serviceUnavailable(context);
			} else {
				context.response().putHeader("content-type", "application/json").end(Json.encodePrettily(res));
			}
		}));
	}
	
	private void handleContactGetAll(RoutingContext context) {
		System.out.println(context.getBodyAsString());
		contactService.getAll().setHandler(resultHandler(context, res -> {
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

	  private void handleDeleteContact(RoutingContext context) {
	    String contactID = context.request().getParam("contactID");
	    contactService.delete(contactID).setHandler(deleteResultHandler(context));
	  }

	  

	  private void sendError(int statusCode, HttpServerResponse response) {
	    response.setStatusCode(statusCode).end();
	  }

	  private void notFound(RoutingContext context) {
	    context.response().setStatusCode(404).end();
	  }

	  private void badRequest(RoutingContext context) {
	    context.response().setStatusCode(400).end();
	  }

	  private void serviceUnavailable(RoutingContext context) {
	    context.response().setStatusCode(503).end();
	  }

}
