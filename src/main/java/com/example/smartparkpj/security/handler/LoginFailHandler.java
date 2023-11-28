//package com.example.smartparkpj.security.handler;
//
//import com.example.smartparkpj.security.exception.UserInactiveException;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//
//@Log4j2
//public class LoginFailHandler implements AuthenticationFailureHandler {
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//        log.info("-------------after login fail-----------------");
//
//        String errorMessage;
//    }
//
////        if (exception instanceof UserInactiveException) {
////            errorMessage = "User is inactive";
////        } else {
////            errorMessage = "Login failed";
////        }
////        response.sendRedirect("/login?error=" + URLEncoder.encode(errorMessage, StandardCharsets.UTF_8.toString()));
////    }
////}
