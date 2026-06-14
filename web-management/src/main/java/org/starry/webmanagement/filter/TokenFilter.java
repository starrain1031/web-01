package org.starry.webmanagement.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.starry.webmanagement.utils.CurrentHolder;
import org.starry.webmanagement.utils.JwtUtils;

import java.io.IOException;

/**
 * Servlet filter that validates JWT tokens and stores the current employee id for the request.
 */
@Slf4j
@WebFilter("/*")
public class TokenFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    /**
     * Validates the request token before allowing protected endpoints to continue.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String requestURI = httpServletRequest.getRequestURI();

//        if (requestURI.contains("/login")){
        if ("/login".equals(requestURI)){
            chain.doFilter(request, response);
            return;
        }

        String token = httpServletRequest.getHeader("token");
        if (token != null && !token.isEmpty()) {
            Claims payload;
            Integer empId;

            try {
                payload = JwtUtils.parseJWT(token);
                empId = Integer.valueOf(payload.get("id").toString());
            } catch (Exception e) {
                log.info("token is invalid: {}", e.getMessage());
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            try {
                CurrentHolder.setCurrentId(empId);
                chain.doFilter(request, response);
            } finally {
                CurrentHolder.remove();
            }
        } else {
            log.info("token is null");
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
