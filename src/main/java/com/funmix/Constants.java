package com.funmix;

/**
 * Vert.x Blueprint Application - Todo Backend
 * Constants class
 */
public final class Constants {

  private Constants() {}

  /** API Route */
  public static final String API_LOGIN = "/api/login/login";
  
  public static final String API_USER_GET = "/api/user/:userId";
  public static final String API_USER_LIST = "/api/user";
  public static final String API_USER_CREATE = "/api/user";
  public static final String API_USER_UPDATE = "/api/user/:userId";
  public static final String API_USER_DELETE = "/api/user/:userId";

  public static final String API_CONTACT_GET = "/api/contact/:contactId";
  public static final String API_CONTACT_LIST = "/api/contact";
  public static final String API_CONTACT_CREATE = "/api/contact";
  public static final String API_CONTACT_UPDATE = "/api/contact/:contactId";
  public static final String API_CONTACT_DELETE = "/api/contact/:contactId";
  
  public static final String API_DRIVER_GET = "/api/driver/:driverId";
  public static final String API_DRIVER_LIST = "/api/driver";
  public static final String API_DRIVER_CREATE = "/api/driver";
  public static final String API_DRIVER_UPDATE = "/api/driver/:driverId";
  public static final String API_DRIVER_DELETE = "/api/driver/:driverId";
  
  public static final String API_TRUCK_GET = "/api/truck/:truckId";
  public static final String API_TRUCK_LIST = "/api/truck";
  public static final String API_TRUCK_CREATE = "/api/truck";
  public static final String API_TRUCK_UPDATE = "/api/truck/:truckId";
  public static final String API_TRUCK_DELETE = "/api/truck/:truckId";
  
  public static final String API_COMPANY_GET = "/api/company/:companyId";
  public static final String API_COMPANY_LIST = "/api/company";
  public static final String API_COMPANY_CREATE = "/api/company";
  public static final String API_COMPANY_UPDATE = "/api/company/:companyId";
  public static final String API_COMPANY_DELETE = "/api/company/:companyId";
  
  public static final String API_ORDER_GET = "/api/order/:orderId";
  public static final String API_ORDER_LIST = "/api/order";
  public static final String API_ORDER_CREATE = "/api/order";
  public static final String API_ORDER_UPDATE = "/api/order/:orderId";
  public static final String API_ORDER_DELETE = "/api/order/:orderId";
  
  public static final String API_SITE_GET = "/api/site/:siteId";
  public static final String API_SITE_LIST = "/api/site";
  public static final String API_SITE_CREATE = "/api/site";
  public static final String API_SITE_UPDATE = "/api/site/:siteId";
  public static final String API_SITE_DELETE = "/api/site/:siteId";
  
  public static final String API_LINE_GET = "/api/line/:lineId";
  public static final String API_LINE_LIST = "/api/line";
  public static final String API_LINE_CREATE = "/api/line";
  public static final String API_LINE_UPDATE = "/api/line/:lineId";
  public static final String API_LINE_DELETE = "/api/line/:lineId";
  
  public static final String API_ORDER_STATICS = "/api/order/statics";
}
