package com.project.tb.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.project.tb.exceptions.InvalidLoginResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest arg0, HttpServletResponse arg1, AuthenticationException arg2)
            throws IOException, ServletException {
      InvalidLoginResponse loginResponse=new InvalidLoginResponse();
      String jsonLoginResponse=new Gson().toJson(loginResponse);
      arg1.setContentType("application/json");
      arg1.setStatus(401);
      arg1.getWriter().print(jsonLoginResponse);
    } // we use the entry point to make sure that the user knows that he is authorized


}