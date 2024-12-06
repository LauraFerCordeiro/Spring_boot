package br.edu.ifsp.dsw3.trabalho.empresa.page_controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/css/*", "/image/*","/","/contato", "/login", "/parceiros", "/quem_somos", "/registro", "/registro_pessoa", "/h2-console/**", "/accounts/person/salvar").permitAll()
                .requestMatchers("/people/home", "/courses/lesson/*","/courses/lessons/*","/courses/matricular/*",  "/people/meuscursos", "/people/todoscursos", "/courses/course/*", "/people/meusdados", "/people/editarPessoa/*", "/people/meuscartoes", "/people/cadastrarcartao").hasRole("PERSON")
                .requestMatchers( "/courses/**", "/lessons/**" , "/people/**", "/dashboard/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            
            .headers(headers -> headers.frameOptions().disable())
                
            .csrf(csrf -> csrf
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")))

            .formLogin((form) -> form
                .loginPage("/login")
                .defaultSuccessUrl("/login", true)
                .permitAll()
            )
            
            .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authenticationProvider);
    } 
/*
    @Bean
    public UserDetailsService userDetailsService(){
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails user = User.withUsername("fulano")
            .password(encoder.encode("fulano"))
            .roles("USER","TESTE")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
*/
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}

