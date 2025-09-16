package com.travelbooking.config;

import com.travelbooking.auth.JWTAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(withDefaults())
                .authorizeHttpRequests(auth -> auth

                        // TEMPORARY: Allow everything
                        .anyRequest().permitAll()

                        /*
                        // Authentication
                        .requestMatchers("/auth/**").permitAll()

                        // Products
                        .requestMatchers(HttpMethod.GET, "/products/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/products/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/products/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/products/**").hasRole("ADMIN")

                        // Categories
                        .requestMatchers(HttpMethod.GET, "/categories/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/categories/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/categories/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/categories/**").hasRole("ADMIN")

                        // Cities
                        .requestMatchers(HttpMethod.GET, "/cities/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/cities/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/cities/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/cities/**").hasRole("ADMIN")

                        // Images
                        .requestMatchers(HttpMethod.GET, "/images/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/images/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/images/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/images/**").hasRole("ADMIN")

                        // Reservations
                        .requestMatchers(HttpMethod.GET, "/reservations/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/reservations/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/reservations/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/reservations/**").hasRole("ADMIN")

                        // Scores
                        .requestMatchers(HttpMethod.GET, "/scores/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/scores/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/scores/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/scores/**").hasRole("ADMIN")

                        // Favorites
                        .requestMatchers(HttpMethod.GET, "/favorites/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/favorites/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/favorites/**").hasAnyRole("USER", "ADMIN")

                        // Users
                        .requestMatchers(HttpMethod.GET, "/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/users/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasAnyRole("USER", "ADMIN")

                        // Any other request must be authenticated
                        .anyRequest().authenticated()
                        */
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOriginPatterns(List.of(
                "http://localhost:5173"
        ));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
