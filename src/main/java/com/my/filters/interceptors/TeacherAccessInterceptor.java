package com.my.filters.interceptors;

import com.my.db.entity.User;
import com.my.db.enums.Roles;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TeacherAccessInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("loggedUser");

        if (user.getRole() == Roles.STUDENT) {
            response.sendRedirect("/");
            return false;
        }

        return true;
    }
}
