package com.project.tb.security;
public class SecurityConstants {
public static final String SIGN_UP_URL = "/api/users/save";
public static final String ALL_OF_USERS = "/api/users/**";
public static final String USER_STATISTICS = "/api/users/statistics/**";
public static final String LOGIN_URL = "/api/users/login";
public static final String STADIUM_URL = "/api/stadium/**";
public static final String GAME_URL = "/api/game/**";
public static final String ADMIN_URL = "/admin/**";
public static final String EMPLOYEE_URL = "/api/employee/**";
public static final String TEAM_URL = "/api/team/**";
public static final String TICKET_URL = "/api/ticket/**";
public static final String GAMETEAMS_URL = "/api/gameTeams/**";
public static final String SECRET = "SecretKeyToGenJWTs";
public static final String TOKEN_PREFIX = "Bearer "; //"Give the bearer of this token access".
public static final String HEADER_STRING = "Authorization";
public static final long EXPIRATION_TIME = 400_000; //in ms // _ for better readability

}
