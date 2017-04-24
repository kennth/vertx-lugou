package com.funmix;

/**
 * Vert.x Blueprint Application - Todo Backend
 * Constants class
 */
public final class Constants {

  private Constants() {}

  /** API Route */
  public static final String API_USER_GET = "/api/user/:userId";
  public static final String API_USER_LIST_ALL = "/api/user";
  public static final String API_USER_CREATE = "/api/user";
  public static final String API_USER_UPDATE = "/api/user/";
  public static final String API_USER_DELETE = "/api/user/:userId";

  public static final String API_CONTACT_GET = "/api/contact/:contactId";
  public static final String API_CONTACT_LIST_ALL = "/api/contact";
  public static final String API_CONTACT_CREATE = "/api/contact";
  public static final String API_CONTACT_UPDATE = "/api/contact/";
  public static final String API_CONTACT_DELETE = "/api/contact/:contactId";
}
