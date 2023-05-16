package com.example.parautomini.Configs;

import com.example.parautomini.Entites.User;
import com.example.parautomini.auth.JwtAuthEntryPoint;
import com.example.parautomini.auth.JwtAuthFilter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SecurityConfig {
    public static final String LOGIN_URL = "/auth/login";
    public static final String SWAGGER_UI_URL = "/swagger-ui/**";
    public static final String API_DOCS_URL = "/v3/api-docs/**";
    public static final String[] ALLOWED_URLS = {
            LOGIN_URL, SWAGGER_UI_URL, API_DOCS_URL
    };
    private final UserDetailsService userDetailsService;
    private final JwtAuthEntryPoint jwtAuthEntryPoint;
    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(UserDetailsService userDetailsService, JwtAuthEntryPoint jwtAuthEntryPoint, JwtAuthFilter jwtAuthFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.securityContext().requireExplicitSave(false)
                .and()
                .csrf().disable()
                .cors().disable()
                .authorizeHttpRequests(authorize -> {
                    authorize
                            .requestMatchers(ALLOWED_URLS).permitAll()
                            .requestMatchers( HttpMethod.GET,"/drivers/**").hasAnyAuthority(User.RoleEnum.ADMIN.name(), User.RoleEnum.TRIP_MANAGER.name())
                            .requestMatchers( HttpMethod.GET,"/vehicles/**").hasAnyAuthority(User.RoleEnum.ADMIN.name(), User.RoleEnum.TRIP_MANAGER.name())
                            .requestMatchers( HttpMethod.GET, "/holidays/**").hasAnyAuthority(User.RoleEnum.ADMIN.name(), User.RoleEnum.TRIP_MANAGER.name())
                            .requestMatchers( HttpMethod.GET, "/licenses/**").hasAnyAuthority(User.RoleEnum.ADMIN.name(), User.RoleEnum.TRIP_MANAGER.name())
                            .requestMatchers( "/trips/**").hasAnyAuthority(User.RoleEnum.ADMIN.name(), User.RoleEnum.TRIP_MANAGER.name())
                            .requestMatchers( "/assignments/**").hasAnyAuthority(User.RoleEnum.ADMIN.name(), User.RoleEnum.TRIP_MANAGER.name())
                            .requestMatchers("/**").hasRole(User.RoleEnum.ADMIN.name())
                            .anyRequest().authenticated();
                })
                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
