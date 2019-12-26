package com.sweetmart.core.oauth.config;

import com.sweetmart.core.oauth.exception.AuthExceptionEntryPoint;
import com.sweetmart.core.oauth.exception.MyAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    DefaultTokenServices tokenServices;
    @Autowired
    private AuthExceptionEntryPoint authExceptionEntryPoint;
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    public ResourceServerConfig() {
    }

    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("lh-coupon-api").stateless(true);
        resources
                .tokenServices(tokenServices)
                .authenticationEntryPoint(authExceptionEntryPoint)
                .accessDeniedHandler(myAccessDeniedHandler);
    }

    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/order/**").authenticated()
                .anyRequest().permitAll()
                .and().anonymous()
                // .and().formLogin()
                // .and().httpBasic()
                .and().cors()
                .and().csrf().disable();
    }
}

