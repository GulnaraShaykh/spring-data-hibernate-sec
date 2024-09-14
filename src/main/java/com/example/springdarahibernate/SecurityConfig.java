package com.example.springdarahibernate;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/public/**").permitAll() // Открытый доступ для всех
                .anyRequest().authenticated() // Требовать авторизацию для всех остальных запросов
                .and()
                .formLogin(); // Стандартная форма логина
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("readUser")
                .password("{noop}password")
                .roles("READ")
                .and()
                .withUser("writeUser")
                .password("{noop}password")
                .roles("WRITE")
                .and()
                .withUser("adminUser")
                .password("{noop}password")
                .roles("DELETE");
    }
}
