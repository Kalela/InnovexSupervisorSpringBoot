package com.kalela.InnovexSolutions.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String API_ROOT_URL = "/**";

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        List<String> permitAllEndpointList = Arrays.asList(
                "/tasks",
                "/console"
        );

        httpSecurity.cors().and().csrf().disable()
                .exceptionHandling()
                .and()
                .authorizeRequests()
                .antMatchers(permitAllEndpointList.toArray(new String[permitAllEndpointList.size()]))
                .permitAll();
    }
}
