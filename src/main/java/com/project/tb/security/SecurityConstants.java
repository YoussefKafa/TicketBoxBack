package com.project.tb.security;
public class SecurityConstants {
public static final String SIGN_UP_URL = "/api/users/**";
public static final String STADIUM_URL = "/api/stadium/**";
public static final String ADMIN_URL = "/api/employee/**";
public static final String SECRET = "SecretKeyToGenJWTs";
public static final String TOKEN_PREFIX = "Bearer "; //"Give the bearer of this token access".
public static final String HEADER_STRING = "Authorization";
public static final long EXPIRATION_TIME = 400_000; //in ms // _ for better readability

}
