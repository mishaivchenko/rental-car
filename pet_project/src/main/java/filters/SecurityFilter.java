package filters;


import entity.User;
import config.SecurityConfig;
import utill.PasswordEncryption;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@WebFilter( urlPatterns = { "/jsp/*" },initParams = { @WebInitParam(name = "LOGIN_PATH", value = "/jsp/login.jsp") })
public class SecurityFilter implements Filter {
        private String loginPath;
        private SecurityConfig config = SecurityConfig.getInstance();
        public void init(FilterConfig fConfig) throws ServletException {
        }

        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            User.Role role;
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            String reqURI = request.getParameter("req");
            String URI = httpRequest.getRequestURI();
            User user = (User) httpRequest.getSession().getAttribute("User");
            if(user!=null) {
                 role = user.getUserRole();
            } else role = null;
            if (check(role, URI)) {
                chain.doFilter(request, response);
            } else {
                httpResponse.sendRedirect(httpRequest.getContextPath() + loginPath);
                chain.doFilter(request, response);
            }
        }
        public void destroy() {
        loginPath = null;
        }

        public boolean check(User.Role role,String URI){
            Map<User.Role,List<String>> protectedURLs = config.getProtectedURLs();
            List<String> urls =  protectedURLs.get(role);
            if(urls!=null) {
                return urls.contains(URI);
            }
            return false;
        }

    }
