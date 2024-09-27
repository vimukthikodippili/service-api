//package com.example.security.config;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
//import org.springframework.security.web.csrf.CsrfTokenRequestHandler;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//
//import java.util.Arrays;
//
//@Configuration
//public class ProjectSecurityConfig {
//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        CsrfTokenRequestAttributeHandler requestHandler =new CsrfTokenRequestAttributeHandler();
//requestHandler.setCsrfRequestAttributeName("_csrf");
//
//http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .cors().configurationSource(new CorsConfigurationSource() {
//            @Override
//            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//CorsConfiguration configuration=new CorsConfiguration();
//            configuration.setAllowCredentials(true);
//            configuration.setExposedHeaders(Arrays.asList("Authorization"));
//            return configuration;
//            }
//        }).and().cors();
//        //how to authentication done
//http.csrf().disable()
//        .authorizeRequests()
//        .requestMatchers("/save")
//        .permitAll();
//
//
//        //permit all
////        http.authorizeRequests().anyRequest().permitAll()
////                .and().formLogin()
////                .and().httpBasic();
//        return http.build();
//    }
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails admin= User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("1234")
//                .authorities("admin")
//                .build();
//        return new InMemoryUserDetailsManager(admin);
//
//    }
////    @Bean
////    public PasswordEncoder passwordEncoder(){
////        return NoOpPasswordEncoder.getInstance();
////
////    }
//
//    @Bean
//    public PasswordEncoder bcryptEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//}
