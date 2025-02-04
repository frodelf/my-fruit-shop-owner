package test.myfruitshopowner.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import test.myfruitshopowner.auth.UserDetailsImpl;
import test.myfruitshopowner.service.OwnerService;

import java.net.URI;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final OwnerService ownerService;

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        return username -> ownerService.getByEmailForAuth(username)
                .map(owner -> new UserDetailsImpl(owner.getId(), owner.getEmail(), owner.getName(), owner.getPassword(), owner));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
//                .csrf(csrf -> csrf.csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse()))
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/").authenticated()
                        .anyExchange().permitAll()
                )
                .formLogin(formLoginSpec -> formLoginSpec.loginPage("/login"))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .requiresLogout(ServerWebExchangeMatchers.pathMatchers("/logout"))
                        .logoutSuccessHandler(new RedirectServerLogoutSuccessHandler())
                )
                .httpBasic(Customizer.withDefaults());

        http.addFilterAfter((exchange, chain) -> {
            Mono<CsrfToken> csrfTokenMono = exchange.getAttribute(CsrfToken.class.getName());
            if (csrfTokenMono != null) {
                return csrfTokenMono.flatMap(csrfToken -> {
                    ServerWebExchange mutatedExchange = exchange.mutate()
                            .request(request -> request.headers(headers ->
                                    headers.set("X-XSRF-TOKEN", csrfToken.getToken())))
                            .build();
                    return chain.filter(mutatedExchange);
                });
            }
            return chain.filter(exchange);
        }, SecurityWebFiltersOrder.CSRF);
        return http.build();
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager() {
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager =
                new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService());

        authenticationManager.setPasswordEncoder(passwordEncoder());

        return authenticationManager;
    }

    private ServerLogoutSuccessHandler logoutSuccessHandler() {
        RedirectServerLogoutSuccessHandler successHandler = new RedirectServerLogoutSuccessHandler();
        successHandler.setLogoutSuccessUrl(URI.create("/login"));
        return successHandler;
    }
}