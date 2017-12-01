package com.dawidkotarba.blog.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Profile("DEV")
        //-Dspring.profiles.active=DEV
class SecurityConfigDev extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/*").permitAll();

        // h2 console csrf disable
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
