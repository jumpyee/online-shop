package com.sombra.stoliar.filter;

import com.sombra.stoliar.entity.User;
import com.sombra.stoliar.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserSessionFilter implements Filter {

    private final UserService userService;

    public UserSessionFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        updateUserInSession(request);
        chain.doFilter(request, response);
    }

    private void updateUserInSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("authenticatedUser");
            if (user != null) {
                String email = user.getEmail();
                user = userService.findUserByEmail(email);
                session.setAttribute("authenticatedUser", user);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
