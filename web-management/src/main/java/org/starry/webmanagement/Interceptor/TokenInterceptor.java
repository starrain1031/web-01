//package org.starry.webmanagement.Interceptor;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.starry.webmanagement.utils.JwtUtils;
//
//@Slf4j
//@Component
//public class TokenInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        String url = request.getRequestURL().toString();
//
//        if(url.contains("login")){
//            log.info("login request");
//            return true;
//        }
//
//        String jwt = request.getHeader("token");
//
//        if(jwt.isEmpty()){
//            log.info("token is null");
//            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
//            return false;
//        }
//
//        try {
//            JwtUtils.parseJWT(jwt);
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.info("invalid token");
//            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
//            return false;
//        }
//
//        log.info("valid token");
//        return true;
//    }
//}
