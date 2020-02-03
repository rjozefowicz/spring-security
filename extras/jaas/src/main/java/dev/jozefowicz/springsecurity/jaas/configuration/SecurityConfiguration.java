package dev.jozefowicz.springsecurity.jaas.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.authentication.jaas.DefaultJaasAuthenticationProvider;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.security.auth.login.AppConfigurationEntry;
import java.util.Collections;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public DefaultJaasAuthenticationProvider defaultJaasAuthenticationProvider() {
        DefaultJaasAuthenticationProvider provider = new DefaultJaasAuthenticationProvider();

        final AppConfigurationEntry[] appConfigurationEntries = new AppConfigurationEntry[]{new AppConfigurationEntry("dev.jozefowicz.springsecurity.jaas.configuration.TestLoginModule", AppConfigurationEntry.LoginModuleControlFlag.REQUIRED, Collections.emptyMap())};
        InMemoryConfiguration configuration = new InMemoryConfiguration(appConfigurationEntries);
        provider.setConfiguration(configuration);

        final AuthorityGranter[] authorityGranters = new AuthorityGranter[]{(principal -> {
            if (principal.getName().equals("jan")) {
                return Collections.singleton("ROLE_ADMIN");
            } else {
                return Collections.singleton("ROLE_USER");
            }
        })};
        provider.setAuthorityGranters(authorityGranters);
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(defaultJaasAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests -> {
                    authorizeRequests
                            .antMatchers("/admin").hasRole("ADMIN")
                            .antMatchers("/all").hasAnyRole("ADMIN", "USER")
                            .anyRequest()
                            .authenticated();
                })
                .httpBasic();
    }
}
