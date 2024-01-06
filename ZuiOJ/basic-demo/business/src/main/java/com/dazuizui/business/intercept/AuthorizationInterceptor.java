package com.dazuizui.business.intercept;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在请求处理之前调用，可以进行前置处理
        String authorizationHeader = request.getHeader("Authorization");

        //取消就会发生下面的错误json
//        System.err.println("??");
//        String errorMessage = "{\"error\": \"Unauthorized\", \"message\": \"身份验证过期\"}";
//        sendErrorResponse(response, errorMessage, HttpStatus.UNAUTHORIZED);

        return true;
    }

    private void sendErrorResponse(HttpServletResponse response, String errorMessage, HttpStatus status) throws IOException, IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getOutputStream().write(errorMessage.getBytes("UTF-8"));
        response.getOutputStream().flush();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 在请求处理之后调用，可以进行后置处理
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 在请求完成之后调用，可以进行最终的清理工作
    }
}
