package net.weg.eng.controller;

import net.weg.eng.util.CacheUtil;
import net.weg.iceberg.infraestructure.controller.SecurityController;
import net.weg.iceberg.infraestructure.passport.IProfile;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HtmlFileFilter implements Filter {


    public static final String MAESTRO = "/maestro";
    public static final String LOGIN_HTML = "index.htm";
    public static final String MENU_HTML = "menu.html";
    public static final String MAESTRO_ROOT = "/maestro";
    public static final String HTML = ".html";
    public static final String JSP = ".jsp";
    public static final String APPCACHE = ".appcache";

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(true);
        setLoginRedirectUrl(httpServletRequest, session);
        IProfile profile = SecurityController.getProfile(session);
        ignoreCachePages(httpServletRequest, httpServletResponse);
        if (checkWebPageAccess(profile, httpServletRequest.getRequestURI()) || checkWebPathAccess(profile, httpServletRequest.getRequestURI())) {
            SecurityController.saveRequestURI(session, httpServletRequest);
            httpServletResponse.sendRedirect(MAESTRO);
        }
        if (ServerRedirect.isRequestRedirect(httpServletRequest)) {
            httpServletResponse.sendRedirect(ServerRedirect.getRequestRedirectURL(httpServletRequest));
        }
        chain.doFilter(request, response);
    }

    private void setLoginRedirectUrl(HttpServletRequest httpServletRequest, HttpSession session) {
        boolean hasReferer = httpServletRequest.getHeader("referer") != null;
        if (hasReferer) {
            String referer = httpServletRequest.getHeader("referer");
            boolean isLoginPage = referer.contains("/maestro/login");
            boolean isAuthenticationForm = referer.contains("/maestro/index.htm");
            boolean isMaestroHome = referer.endsWith("/maestro") || referer.endsWith("/maestro/");
            if (!isLoginPage && !isAuthenticationForm && !isMaestroHome) {
                session.setAttribute("loginRedirect", referer);
            }
        }
    }

    private void ignoreCachePages(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        if (httpServletRequest.getRequestURI().contains(LOGIN_HTML) ||
                httpServletRequest.getRequestURI().contains(MENU_HTML) ||
                httpServletRequest.getRequestURI().endsWith(MAESTRO) ||
                httpServletRequest.getRequestURI().endsWith(MAESTRO_ROOT) ||
                httpServletRequest.getRequestURI().endsWith(LOGIN_HTML)) {
            CacheUtil.disableHttpResponseCache(httpServletResponse);
        }
    }

    private boolean checkWebPageAccess(IProfile profile, String uri) {
        return profile == null &&
                (((uri.contains(HTML) || uri.contains(JSP)) && !uri.endsWith(LOGIN_HTML)) || uri.endsWith(APPCACHE));
    }

    private boolean checkWebPathAccess(IProfile profile, String uri) {
        return profile != null &&
                (uri.endsWith(MAESTRO) || uri.endsWith(MAESTRO_ROOT) || uri.endsWith(LOGIN_HTML));
    }

    @Override
    public void destroy() {

    }
}