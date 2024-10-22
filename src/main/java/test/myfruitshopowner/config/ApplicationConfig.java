package test.myfruitshopowner.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import test.myfruitshopowner.auth.UserDetailsImpl;
import test.myfruitshopowner.service.OwnerService;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class ApplicationConfig {

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
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/blog/**").permitAll()
                        .anyExchange().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                );

        return http.build();
    }

}