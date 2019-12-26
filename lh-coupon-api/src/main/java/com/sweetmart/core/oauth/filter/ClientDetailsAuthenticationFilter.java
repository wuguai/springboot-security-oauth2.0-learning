package com.sweetmart.core.oauth.filter;

import com.sweetmart.core.oauth.response.BaseResponse;
import com.sweetmart.core.oauth.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@Component
public class ClientDetailsAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private ClientDetailsService clientDetailsService;

    public ClientDetailsAuthenticationFilter() {
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!request.getRequestURI().equals("/oauth/token")) {
            filterChain.doFilter(request, response);
        } else {
            String[] clientDetails = this.isHasClientDetails(request);
            if (clientDetails == null) {
                BaseResponse bs = new BaseResponse(HttpStatus.UNAUTHORIZED.value(), "请求中未包含客户端信息");
                HttpUtils.writer(bs, response);
            } else {
                this.handle(request, response, clientDetails, filterChain);
            }
        }
    }

    private void handle(HttpServletRequest request, HttpServletResponse response, String[] clientDetails, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            filterChain.doFilter(request, response);
        } else {
            ClientDetails details = this.clientDetailsService.loadClientByClientId(clientDetails[0]);
            if ((new BCryptPasswordEncoder()).matches(clientDetails[1], details.getClientSecret())) {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(details.getClientId(), details.getClientSecret(), details.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(token);
            }

            filterChain.doFilter(request, response);
        }
    }

    private String[] isHasClientDetails(HttpServletRequest request) {
        String[] params = null;
        String header = request.getHeader("Authorization");
        String id;
        String secret;
        if (header != null) {
            id = header.substring(0, 5);
            if (id.toLowerCase().contains("basic")) {
                secret = header.substring(6);
                String defaultClientDetails = new String(Base64.getDecoder().decode(secret));
                String[] clientArrays = defaultClientDetails.split(":");
                if (clientArrays.length != 2) {
                    return params;
                }

                params = clientArrays;
            }
        }

        id = request.getParameter("client_id");
        secret = request.getParameter("client_secret");
        if (header == null && id != null) {
            params = new String[]{id, secret};
        }

        return params;
    }
}

