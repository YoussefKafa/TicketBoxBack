package com.project.tb.security;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.project.tb.models.User;
import com.project.tb.services.CustomUserDetailsService;
import static com.project.tb.security.SecurityConstants.HEADER_STRING;
import static com.project.tb.security.SecurityConstants.TOKEN_PREFIX;;
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	private JwtTokenProvider tokenProvider;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			//grab a token
			String token=getJwtFromRequest(request);
			//if it is not null and there is no exception
			if(StringUtils.hasText(token)&& tokenProvider.validateToken(token)) {
				//get id
				Long userId=tokenProvider.getUserIdFromJwt(token);
				//get user details
				User userDetails=customUserDetailsService.loadUserById(userId);
				//set up the authentication for the token
				UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(
						userDetails, null, java.util.Collections.emptyList());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		} catch (Exception e) {
			//save any problem in the log
	logger.error("could not set user authentication in security context",e);
		}
		filterChain.doFilter(request, response);
	}
private String getJwtFromRequest(HttpServletRequest request) {
	//get the header of the request (which is the token)
	String bearerToken=request.getHeader(HEADER_STRING);
	//if there is text and it is not blank and starts with token_prefix
	if(StringUtils.hasText(bearerToken)&&bearerToken.startsWith(TOKEN_PREFIX)) {
		//start at 7 and return the rest of it (which means without bearer word
		return bearerToken.substring(7,bearerToken.length());
	}
	//if there is no token passed
	return null;
}
}
