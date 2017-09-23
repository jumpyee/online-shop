package com.stoliar.petproject.gadgetshop.filter;

import com.stoliar.petproject.gadgetshop.entity.User;
import com.stoliar.petproject.gadgetshop.filter.exception.AccessDeniedException;
import com.stoliar.petproject.gadgetshop.filter.exception.NotAuthenticatedException;
import com.stoliar.petproject.gadgetshop.filter.exception.UserBannedException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecurityFilter implements Filter {

    private List<String> ignoreList = new ArrayList<>();
    private List<String> needAuthorizationList = new ArrayList<>();
    private Map<String, String> needRoleMap = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ignore("/templates/");
        ignore("/lib/");
        ignore("/images/");
        ignore("/banned");
        ignore("/user/logout");
        ignore("/403");
        ignore("/404");

        needAuthorization("/user");
        needAuthorization("/async/cart");
        needAuthorization("/async/order");
        needAuthorization("/banned");

        needRole("/async/user/admin", "admin");
        needRole("/user/admin", "admin");
    }

    private void ignore(String uri) {
        ignoreList.add(uri);
    }

    private void needAuthorization(String uri) {
        needAuthorizationList.add(uri);
    }

    private void needRole(String uri, String role) {
        needRoleMap.put(uri, role);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        try {
            checkAccess(request);
            chain.doFilter(request, response);
        } catch (NotAuthenticatedException e) {
            response.sendRedirect("/authorize");
        } catch (AccessDeniedException e) {
            response.sendRedirect("/403");
        } catch (UserBannedException e) {
            response.sendRedirect("/banned");
        }

    }

    private void checkAccess(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        // check ignore list
        for (String uri : ignoreList) {
            if (requestUri.startsWith(uri)) {
                return;
            }
        }
        //check ban list
        if (isBanned(request)) {
            throw new UserBannedException();
        }
        // check need authorization list
        for (String uri : needAuthorizationList) {
            if (requestUri.startsWith(uri)) {
                if (isAnonymous(request)) {
                    throw new NotAuthenticatedException();
                }
            }
        }
        // check need role map
        for (Map.Entry<String, String> entry : needRoleMap.entrySet()) {
            String uri = entry.getKey();
            String role = entry.getValue();
            if (requestUri.startsWith(uri)) {
                if (isAnonymous(request)) {
                    throw new NotAuthenticatedException();
                }
                if (!hasRole(role, request)) {
                    throw new AccessDeniedException();
                }
            }
        }
    }

    private boolean isAnonymous(HttpServletRequest request) {
        return !isAuthenticated(request);
    }

    private boolean isBanned(HttpServletRequest request) {
        boolean banned = false;
        if (isAuthenticated(request)) {
            banned = getAuthenticatedUser(request).isBanned();
        }
        return banned;
    }

    private boolean isAuthenticated(HttpServletRequest request) {
        User user = null;
        HttpSession session = request.getSession(false);
        if (session != null) {
            user = (User) session.getAttribute("authenticatedUser");
        }
        return user != null;
    }

    private User getAuthenticatedUser(HttpServletRequest request) {
        User user = null;
        HttpSession session = request.getSession(false);
        if (session != null) {
            user = (User) session.getAttribute("authenticatedUser");
        }
        return user;
    }

    private boolean hasRole(String role, HttpServletRequest request) {
        boolean valid = false;
        if (isAuthenticated(request)) {
            valid = getAuthenticatedUser(request).getRole().equalsIgnoreCase(role);
        }
        return valid;
    }

    @Override
    public void destroy() {
    }
}
