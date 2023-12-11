package application.security;

import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService service;
    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfig(UserService service, JwtFilter jwtFilter) {
        this.service = service;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public static PasswordEncoder passwordEncode() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        //User
                        .requestMatchers(HttpMethod.POST, "/user/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/user/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/user/**").authenticated()

                        //Tasks
                        .requestMatchers(HttpMethod.POST, "/task/**").hasAuthority("AUTHOR")
                        .requestMatchers(HttpMethod.GET, "/task/my").authenticated()
                        .requestMatchers(HttpMethod.GET, "/task/author").hasAuthority("AUTHOR")

                        .requestMatchers(HttpMethod.DELETE, "/task/**").hasAuthority("AUTHOR")
                        .requestMatchers(HttpMethod.PUT, "/task/update").hasAuthority("AUTHOR")
                        .requestMatchers(HttpMethod.PUT, "/task/executor").hasAuthority("EXECUTOR")

                        //FeedBack
                        .requestMatchers(HttpMethod.POST, "/feedback/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/feedback/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/feedback/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/feedback/**").authenticated()
                        .anyRequest().permitAll()
                ).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncode());
        daoAuthenticationProvider.setUserDetailsService(service);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
