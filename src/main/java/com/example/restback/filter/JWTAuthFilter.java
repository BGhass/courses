package com.example.restback.filter;

import com.example.restback.security.UserAuthenticationProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTAuthFilter extends OncePerRequestFilter {
    private final UserAuthenticationProvider userAuthenticationProvider;

    public JWTAuthFilter(UserAuthenticationProvider userAuthenticationProvider) {
        this.userAuthenticationProvider = userAuthenticationProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (!StringUtils.isEmpty(header)) {
            String[] authParams = header.split(" ");

            if (authParams.length == 2 && "Bearer".equals(authParams[0])) {
                SecurityContextHolder.getContext().setAuthentication(
                        userAuthenticationProvider.validateToken(authParams[1])
                );
            }
        }

        filterChain.doFilter(request, response);
    }
}
