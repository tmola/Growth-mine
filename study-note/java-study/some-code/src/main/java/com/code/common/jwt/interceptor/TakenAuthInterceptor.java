package com.code.common.jwt.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.code.common.exception.MyException;
import com.code.common.jwt.annotation.NoTakenAuth;
import com.code.common.jwt.annotation.TakenAuth;
import com.code.modules.system.entity.SysUser;
import com.code.modules.system.services.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/18
 */
public class TakenAuthInterceptor implements HandlerInterceptor {
    @Autowired
    private SysUserService userService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) return true;

        String token = request.getHeader("token");
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        //检查是否有NoTakenAuth注释，有则跳过认证
        if (method.isAnnotationPresent(NoTakenAuth.class)) return true;

        if (method.isAnnotationPresent(TakenAuth.class)) {
            TakenAuth takenAuth = method.getAnnotation(TakenAuth.class);
            if(token == null)
                throw new MyException("无token，请重新登录");
            // 获取 token 中的 user id
            String userId;
            try {
                userId = JWT.decode(token).getAudience().get(0);
            } catch (JWTDecodeException j) {
                throw new RuntimeException("401");
            }
            SysUser user = userService.findUserById(userId);
            if (user == null) {
                throw new RuntimeException("用户不存在，请重新登录");
            }
            // 验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
            try {
                jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {
                throw new RuntimeException("401");
            }
            return true;
        }

        return true;
    }


}
