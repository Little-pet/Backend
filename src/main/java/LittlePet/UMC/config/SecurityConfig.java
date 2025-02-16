package LittlePet.UMC.config;



import LittlePet.UMC.User.jwt.JwtFilter;
import LittlePet.UMC.User.jwt.JwtUtil;
import LittlePet.UMC.User.oauth2.CustomSuccessHandler;
import LittlePet.UMC.User.service.CustomOAuth2UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final JwtUtil jwtUtil;
    private final CustomSuccessHandler customSuccessHandler;
    private final OAuth2LoggingFilter oAuth2LoggingFilter;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService, CustomSuccessHandler customSuccessHandler, JwtUtil jwtUtil,OAuth2LoggingFilter oAuth2LoggingFilter) {

        this.customOAuth2UserService = customOAuth2UserService;
        this.customSuccessHandler = customSuccessHandler;
        this.jwtUtil = jwtUtil;
        this.oAuth2LoggingFilter = oAuth2LoggingFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 1. CORS 설정
        http.cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration configuration = new CorsConfiguration();

                configuration.setAllowedOrigins(Arrays.asList(
                        "http://localhost:5173",
                        "https://umclittlepet.shop"
                ));
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                configuration.setAllowCredentials(true);
                configuration.setAllowedHeaders(Collections.singletonList("*"));
                configuration.setMaxAge(3600L);
                configuration.setExposedHeaders(Arrays.asList("Authorization", "Set-Cookie"));


                return configuration;
            }
        }));


        http.csrf(csrf -> csrf.disable());


        http.formLogin(formLogin -> formLogin.disable());


        http.httpBasic(httpBasic -> httpBasic.disable());



        // 5. JWTFilter 추가

         http
                 .addFilterAfter(new JwtFilter(jwtUtil), OAuth2LoginAuthenticationFilter.class);

//        //6. OAuth2 로그인 설정
//        http.oauth2Login(oauth2 -> oauth2
//                .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
//                .successHandler(customSuccessHandler)
//        );

        http
                .oauth2Login((oauth2) -> oauth2
                        .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                                .userService(customOAuth2UserService))
                        .successHandler(customSuccessHandler)


                );

//        http
//                .authorizeHttpRequests((auth) -> auth
//                        .requestMatchers(
//                                "/",
//                                "/docs/**",
//                                "/v3/api-docs/**",
//                                "/swagger-ui/**",
//                                "/oauth2/authorization/**",
//                                "/login/oauth2/code/**",
//                                "/login",
//                                "/api/**"
//                        ).permitAll()
//                                // /pet-register 경로는 로그인한 사용자만 접근 가능하게 설정
//                        .requestMatchers("/pet-register").authenticated()
//                                // 나머지 모든 요청은 인증 필요 (필요에 따라 조정)
//                        .anyRequest().authenticated());
//                        //.anyRequest().authenticated());

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/pet-register").authenticated() // /pet-register는 인증 필요
                .requestMatchers(
                        "/",
                        "/docs/**",
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/oauth2/authorization/**",
                        "/login/oauth2/code/**",
                        "/login",
                        "/api/**"
                ).permitAll()  // 나머지 명시된 URL은 인증 없이 접근 가능
                .anyRequest().authenticated() // 위에 명시하지 않은 모든 요청은 인증 필요
        );

        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.addFilterBefore(oAuth2LoggingFilter, OAuth2LoginAuthenticationFilter.class);

        return http.build();
    }
}