package com.example.emailsender.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/hello", "/h2/**").permitAll()
                .anyRequest().authenticated()
            .and()
            // Shortcut for successHandler, second parameter: "always use?"
                .formLogin().defaultSuccessUrl("/hello", true)
            .and()
                .httpBasic()
            // TODO: Remove before migration to production,
            // H2 Console runs inside a frame. Spring Security disables rendering pages within an iframe. 
            .and().headers().frameOptions().sameOrigin()
            // Disable CSRF Protection for H2 Console
            .and().csrf().ignoringAntMatchers("/h2/**");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer(){
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/hello").allowedOrigins("http://localhost:3000");
            }
        };
    }

}
