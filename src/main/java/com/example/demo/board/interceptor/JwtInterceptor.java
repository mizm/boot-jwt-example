package com.example.demo.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.board.error.UnauthorizedException;
import com.example.demo.board.service.JwtService;

@Component
public class JwtInterceptor implements HandlerInterceptor {
	private static final String HEADER_AUTH = "Authorization";

	@Autowired
	private JwtService jwtService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//요거 넣어 줘야 options 막음.
		System.out.println(request.getMethod());
		if(request.getMethod().equals("OPTIONS")) {
			return true;
		}
		final String token = request.getHeader(HEADER_AUTH);
		System.out.println(token);
		if (token != null && jwtService.isUsable(token)) {
			return true;
		} else {
			throw new UnauthorizedException();
		}

	}

}
