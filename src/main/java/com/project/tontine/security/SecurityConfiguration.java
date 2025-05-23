package com.project.tontine.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration
{
    private JwtFilter jwtFilter;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers(GET, "/member/id/**").permitAll()
                                .requestMatchers(GET, "/member").permitAll()
                                .requestMatchers(POST, "/member/6").permitAll()
                                .requestMatchers(DELETE, "/member").permitAll()
                                .requestMatchers(POST, "/member/group/**").permitAll()
                                .requestMatchers(POST, "/member/account/**").permitAll()
                                .requestMatchers(POST, "/member/meeting/**").permitAll()
                                .requestMatchers(POST, "/member/plannedMeeting/**").permitAll()

                                .requestMatchers(POST, "/authentication/activate").permitAll()
                                .requestMatchers(POST, "/authentication/connect").permitAll()

                                .requestMatchers(GET, "/group").permitAll()

                                .requestMatchers(GET, "/account").permitAll()

                                .requestMatchers(GET, "/meeting").permitAll()

                                .requestMatchers(GET, "/plannedMeeting").permitAll()


                                // .requestMatchers(DELETE, "/member/**").hasAuthority()


                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService)
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
    {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
