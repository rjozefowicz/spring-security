package workshop.sb.security.basics.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import workshop.sb.security.basics.model.User;
import workshop.sb.security.basics.model.UserPasswordMigration;
import workshop.sb.security.basics.repository.UserPasswordMigrationRepository;
import workshop.sb.security.basics.repository.UserRepository;
import workshop.sb.security.basics.service.CustomUserDetailsService;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${migrate.user.password}")
    private boolean migrateUserPassword;

    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPasswordMigrationRepository userPasswordMigrationRepository;

    public AppSecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authBuilder) throws Exception {
        authBuilder
                .eraseCredentials(!migrateUserPassword) // TODO
                .userDetailsService(userDetailsService)
                .passwordEncoder(delegatingPasswordEncoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable();

    }

    @Bean
    public PasswordEncoder delegatingPasswordEncoder() {
        Map<String, PasswordEncoder> passwordEncoders = new HashMap<>();
        passwordEncoders.put("sha256", standardPasswordEncoder());
        passwordEncoders.put("bcrypt", bCryptPasswordEncoder());
        DelegatingPasswordEncoder delegatingPasswordEncoder = new DelegatingPasswordEncoder("bcrypt", passwordEncoders);
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(bCryptPasswordEncoder());
        return delegatingPasswordEncoder;
    }

    public PasswordEncoder standardPasswordEncoder() {
        return new StandardPasswordEncoder();
    }

    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ApplicationListener<AuthenticationSuccessEvent> authenticationSuccessListener(PasswordEncoder encoder) {
        return (AuthenticationSuccessEvent event) -> {
            if (migrateUserPassword) {
                final Authentication auth = event.getAuthentication();
                if (auth instanceof UsernamePasswordAuthenticationToken
                        && auth.getCredentials() != null) {
                    final UserDetails userDetails = (UserDetails) auth.getPrincipal();
                    final User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new IllegalStateException("should never happen"));

                    if (!userPasswordMigrationRepository.existsByUserId(user.getId())) {
                        final CharSequence clearTextPass = (CharSequence) auth.getCredentials();
                        final String newPasswordHash = encoder.encode(clearTextPass);
                        user.setPassword(newPasswordHash);
                        userRepository.save(user);
                        final UserPasswordMigration userPasswordMigration = new UserPasswordMigration();
                        userPasswordMigration.setUserId(user.getId());
                        userPasswordMigration.setMigrationDate(LocalDateTime.now());
                        userPasswordMigrationRepository.save(userPasswordMigration);
                        ((UsernamePasswordAuthenticationToken) auth).eraseCredentials();
                    }

                }
            }
        };
    }

    @PostConstruct
    public void setup() {
        userRepository.save(user("user1@test.pl", "{sha256}" + standardPasswordEncoder().encode("password")));
        userRepository.save(user("user2@test.pl", "{sha256}" + standardPasswordEncoder().encode("password")));
        userRepository.save(user("user3@test.pl", "{sha256}" + standardPasswordEncoder().encode("password")));
        userRepository.save(user("user4@test.pl", "{bcrypt}" + bCryptPasswordEncoder().encode("password")));
    }

    private User user(String email, String password) {
        User user = new User();
        user.setEnabled(false);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

}

