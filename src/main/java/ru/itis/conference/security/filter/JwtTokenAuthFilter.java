package ru.itis.conference.security.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.itis.conference.security.authentication.JwtTokenAuthentication;
import ru.itis.conference.security.provider.JwtTokenAuthProvider;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.StringTokenizer;

@Component
public class JwtTokenAuthFilter implements Filter {

    @Value("${jwt.prefix}")
    private String prefix;


    private final JwtTokenAuthProvider jwtTokenAuthProvider;


    public JwtTokenAuthFilter(JwtTokenAuthProvider jwtTokenAuthProvider) {
        this.jwtTokenAuthProvider = jwtTokenAuthProvider;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String authorizationHeader = request.getHeader("Authorization");
        JwtTokenAuthentication  authentication;

        SecurityContext context = SecurityContextHolder.getContext();

        if(authorizationHeader == null) {
            authentication = new JwtTokenAuthentication(null);
            authentication.setAuthenticated(false);
        } else {
            String token = "";
            if(!StringUtils.isBlank(authorizationHeader)) {
                String prefix = this.prefix + " ";
                token = authorizationHeader.substring(prefix.length());
            }
            authentication = new JwtTokenAuthentication(token);
            context.setAuthentication(jwtTokenAuthProvider.authenticate(authentication));
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
