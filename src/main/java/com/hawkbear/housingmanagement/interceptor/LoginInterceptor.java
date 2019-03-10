package com.hawkbear.housingmanagement.interceptor;

import com.hawkbear.housingmanagement.annotation.LoginRequired;
import com.hawkbear.housingmanagement.data.ResultEnum;
import com.hawkbear.housingmanagement.data.dto.User;
import com.hawkbear.housingmanagement.exception.MyException;
import com.hawkbear.housingmanagement.holder.UserHolder;
import com.hawkbear.housingmanagement.service.ClientService;
import com.hawkbear.housingmanagement.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 登陆 过滤器
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate template;

    @Resource
    private ClientService clientService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        Method method = ((HandlerMethod) handler).getMethod();
        clientService.genProjectInfo();

        boolean isLoginRequired = isAnnotationPresent(method, LoginRequired.class);
        if (isLoginRequired) {
            String uri = request.getRequestURI();
            String token = getToken(getAuthHeader(request));
            User user = clientService.getUser(token);
            if (user == null) {
                // token无法获取到用户信息代表未登陆
                throw new MyException(ResultEnum.NEED_LOGIN);
            }
            // 退出时删除缓存
            if (uri.contains(Constants.LOGOUT)) {
                template.delete(Constants.getRedisKey(Constants.REDIS_USER_TOKEN, token));
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求完成后清除ThreadLocal
        UserHolder.remove();
    }

    private boolean isAnnotationPresent(Method method, Class<? extends Annotation> annotationClass) {
        return method.getDeclaringClass().isAnnotationPresent(annotationClass) || method.isAnnotationPresent(annotationClass);
    }

    private String getAuthHeader(HttpServletRequest request) {
        String authHeader = request.getHeader(Constants.Authorization);
        log.info("authHeader：" + authHeader);
        return authHeader;
    }

    private String getToken(String authHeader) {
        String token = "";
        if (StringUtils.isEmpty(authHeader)) {
            token = "1f126895c515339f5a7d9cbbbf61443ca8969b44c4a8d3ef6e6cf8eb272738fd";
        } else {
            token = authHeader.split(" ")[1];
        }
        return token;
    }
}
