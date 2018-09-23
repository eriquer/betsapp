package com.bets.mgr;

import com.bets.mgr.handler.LoginSuccessHandler;
import com.bets.mgr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Configuration
    public class MvcConfigurer {
        @Bean
        public BCryptPasswordEncoder createPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    // Utilidad de Spring para generar un controller de forma rápida
    @Bean
    public WebMvcConfigurer controller403() {
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/403").setViewName("403");
            }
        };
    }

    /* In memory
    @Autowired
    public void configurer(AuthenticationManagerBuilder auth) throws Exception {
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication().withUser(users.username("admin").password("admin").roles("USER", "ADMIN"))
            .withUser(users.username("user").password("user").roles("USER"));
    }
    */

    /* Data Source example
    @Autowired
    private DataSource dataSource;
    */

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    /* Datasource example @Autowired
    public void configurer(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .passwordEncoder(passwordEncoder)
            .usersByUsernameQuery("select username,password,enabled from usr_users where username=?")
            .authoritiesByUsernameQuery("select u.username, a.name from usr_roles a "
                    + "inner join usr_users u on a.user_id = u.id where u.username=?");
    }
    */

    @Autowired
    public void configurer(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
            .passwordEncoder(passwordEncoder)
            .getUserDetailsService();
    }

    /*
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/form/**", "/matches/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
            .and()
            .formLogin().successHandler(loginSuccessHandler)
                .loginPage("/login").permitAll()
            .and()
                .logout().permitAll()
            .and()
                .exceptionHandling();
    }
    */

    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .formLogin().successHandler(loginSuccessHandler)
            .loginPage("/login").permitAll()
            .and()
            .logout().permitAll()
            .and()
            .exceptionHandling().accessDeniedPage("/403");
    }

}