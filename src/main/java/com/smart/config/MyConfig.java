//package com.smart.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class MyConfig {
//
//    @Bean
//    public UserDetailsService getUserDetailService() {
//        return new UserDetailsServiceImpl();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        
//    	DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(this.getUserDetailService());
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        
//        return daoAuthenticationProvider;
//    }
//    
//
//   
//    @Bean
//    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                                .authorizeHttpRequests()
//                                .requestMatchers("/admin/**")
//                                .hasRole("ADMIN")
//                                .requestMatchers("/user/**")
//                                .hasRole("USER")
//                                .requestMatchers("/**")
//                                .permitAll()
//                                .and()
//                                .formLogin()
//                                .loginPage("/login")
//                                .loginProcessingUrl("/dologin")
//                                .defaultSuccessUrl("/admin/adminpanel");
//                                           
//                            
//                            
//
//                    return http.build();
//               }
//
//
//    @Bean
//    public AuthenticationManager authenticationProviderBean(AuthenticationConfiguration authenticationProvider) throws Exception {
//        return authenticationProvider.getAuthenticationManager();
//    }
//    
//    
//}


package com.smart.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.FilterChainProxy;

@Configuration
@EnableWebSecurity
public class MyConfig {

    @Bean
    public UserDetailsService getUserDetailService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.getUserDetailService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
                .requestMatchers("/admin/**").hasRole("ADMIN")  // Only admin can access these paths
                .requestMatchers("/user/**").hasRole("USER")    // Only user can access these paths
                .requestMatchers("/**").permitAll()             // Everyone can access other paths
            .and()
            .formLogin()
                .loginPage("/signin")
                .loginProcessingUrl("/dologin")
                .defaultSuccessUrl("/user/userdashboard");
        
        return http.build();
    }                               
                          
                          

  
 

    



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}

