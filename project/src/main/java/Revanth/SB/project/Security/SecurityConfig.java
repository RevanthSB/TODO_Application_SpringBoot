package Revanth.SB.project.Security;


import Revanth.SB.project.Auth.JWTAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {
;

    @Bean
    public PasswordEncoder passwordEncoder() throws Exception{
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,JWTAuthFilter jwtAuthFilter) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors->cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth.
                requestMatchers("/api/v1/todo/User/**").permitAll()
                .anyRequest().authenticated()).addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:8081/**"));
        configuration.setAllowedMethods(List.of("GET","POST","PUT","DELETE"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",configuration);
        return  urlBasedCorsConfigurationSource;
    }

}
