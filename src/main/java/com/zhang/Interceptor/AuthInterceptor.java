package com.zhang.Interceptor;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javafx.tk.TKClipboard;
import com.zhang.anno.Auth;
import com.zhang.dto.JsonMessage;
import com.zhang.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: zhang
 * @Description: com.zhang.Interceptor
 * @Date：Created in 17:43 2021/1/6
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Resource
    private JwtUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            // 判断类上的注解
            boolean auth = false;
            Auth classAuth = hm.getBeanType().getAnnotation(Auth.class);
            if (classAuth != null) {
                auth = classAuth.auth();
            }
            // 方法是否有注解
            Auth methodAuth = hm.getMethodAnnotation(Auth.class);
            if (methodAuth != null) {
                auth = methodAuth.auth();
            }
            if(auth){
                System.err.println("需要鉴权");
                String token = request.getParameter("token");
                System.out.println("token"+ token);
                if(token == null || "".equals(token)){
                    authFailOutput(response,"登录失效,请重新登录",1002);
                    return false;
                }else {
                    try {
                        if(!jwtUtil.verify(token) || !jwtUtil.checkExprieTime(token)){
                            authFailOutput(response,"登录失效,请重新登录",1002);
                            return false;
                        }
                    } catch (Exception e) {
                        authFailOutput(response,"登录失效,请重新登录",1002);
                        return false;
                    }
                }
                return true;
            }
        }
        return true;
    }
    /**
     * json输出
     */
    private void authFailOutput(HttpServletResponse response, String msg,Integer code) {
        response.setContentType("application/json;charset=UTF-8");
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setId(code);
        jsonMessage.setMsg(msg);
        jsonMessage.setLocation("userLogin");
        try {
            response.getWriter().println(JSON.toJSONString(jsonMessage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}