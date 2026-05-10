package org.starry.webmanagement.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.starry.webmanagement.utils.JwtUtils;

import java.io.IOException;

@Slf4j
@WebFilter("/*")
public class DemoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String requestURI = httpServletRequest.getRequestURI();

        if (requestURI.contains("/login")){
            chain.doFilter(request, response);
            return;
        }

        String token = httpServletRequest.getHeader("token");
        if (token != null && !token.isEmpty()){
            try {
                Claims payload = JwtUtils.parseJWT(token);
                chain.doFilter(request, response);
            } catch (Exception e){
                log.info("token is invalid");
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                throw new RuntimeException(e);
            }
        }
        else{
            log.info("token is null");
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
