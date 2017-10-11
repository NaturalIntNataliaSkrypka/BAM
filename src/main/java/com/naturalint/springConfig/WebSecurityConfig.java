package com.naturalint.springConfig;

/**
 * Created by skn on 10/11/2017.
 */

import com.naturalint.security.CookiesPreAuthenticatedProcessingFilter;
import com.naturalint.security.PreAuthenticatedAuthenticationProvider;
import com.naturalint.security.BamAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:/application-${spring.profiles.active:default}.properties")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CookiesPreAuthenticatedProcessingFilter filter = new CookiesPreAuthenticatedProcessingFilter();
        filter.setAuthenticationManager(authenticationManager());

        http.csrf().disable()
                .addFilter(filter)
                .authorizeRequests()
                .antMatchers("/googleScraper", "/scraperResults**", "/scraperStatus**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .successHandler(authenticationSuccessHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll();

        http.authorizeRequests().anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity security) {
        security.ignoring().antMatchers("/css/**", "/img/**", "/js/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.eraseCredentials(Boolean.FALSE);
        auth.authenticationProvider(preAuthenticatedAuthenticationProvider);
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new BamAuthenticationSuccessHandler();
    }

    @Bean
    public PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider() {
        return new PreAuthenticatedAuthenticationProvider();
    }
}