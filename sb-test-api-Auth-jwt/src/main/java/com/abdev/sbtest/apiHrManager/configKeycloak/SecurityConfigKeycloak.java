package com.abdev.sbtest.apiHrManager.configKeycloak;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

//@KeycloakConfiguration
public class SecurityConfigKeycloak extends KeycloakWebSecurityConfigurerAdapter {

    @Override
    //@Bean
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(sessionRegistry());
    }

    //@Bean
    public KeycloakConfigResolver   keycloakConfigResolver(){
        return new KeycloakSpringBootConfigResolver();
    }

    //@Bean
    public SessionRegistry  sessionRegistry(){
        return new SessionRegistryImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder   authenticationManagerBuilder) throws Exception{
        KeycloakAuthenticationProvider  keycloakAuthenticationProvider  =   keycloakAuthenticationProvider();
        final SimpleAuthorityMapper simpleAuthorityMapper   =   new SimpleAuthorityMapper();
        simpleAuthorityMapper.setConvertToUpperCase(true);
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(simpleAuthorityMapper);
        authenticationManagerBuilder.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity   httpSecurity) throws Exception {
        super.configure(httpSecurity);
        httpSecurity.httpBasic().disable();
        httpSecurity.formLogin().disable();
        httpSecurity.csrf().disable();

        httpSecurity.headers()
                .frameOptions().sameOrigin().and()
                .authorizeRequests()
                .antMatchers("/api/ApiEmp/**").hasAnyRole("ROLE_EMPLOYEE")
                .antMatchers("/api/ApiDep/**").hasAnyRole("ROLE_CHEF_DEPARTEMENT")
                .antMatchers("/api/**").hasAnyRole("ROLE_HR_MANAGER")
                .antMatchers("/auth").authenticated();
    }
}
