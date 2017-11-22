package simple.oauth.resourceowner.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * Created by volkovan on 22-Mar-17.
 */
//@SpringBootApplication
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public ResourceServerTokenServices tokenService() {
        RemoteTokenServices tokenServices = new RemoteTokenServices();
        tokenServices.setClientId("resource1-owner");
        tokenServices.setClientSecret("owner-secret");
        tokenServices.setTokenName("token");
        tokenServices.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
        tokenServices.setAccessTokenConverter(accessTokenConverter());
        return tokenServices;
    }

    @Bean
    public AccessTokenConverter accessTokenConverter() {
        return new DefaultAccessTokenConverter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll().and().authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access", "/oauth/token").permitAll().anyRequest()
//                .authenticated().and().csrf().disable();
        http.csrf().disable()
                .anonymous().and()
                .authorizeRequests()
                .antMatchers("/**").permitAll();
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
        authenticationManager.setTokenServices(tokenService());
        return authenticationManager;
    }
}
