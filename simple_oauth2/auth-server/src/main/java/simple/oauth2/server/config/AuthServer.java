package simple.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthServer extends AuthorizationServerConfigurerAdapter {

    private static final String RESOURCE_ID = "resource#1";
    private static final String RESOURCE_ARAR_ID = "arar_resource#1";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //http://stackoverflow.com/questions/26750999/spring-security-oauth2-check-token-endpoint
        security.checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("resource1-owner")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
                .authorities("ROLE_CLIENT")
                .scopes("read", "trust")
                .resourceIds(RESOURCE_ID, RESOURCE_ARAR_ID)
                .secret("owner-secret")
                .and()
                .withClient("client#1")
                .authorizedGrantTypes("client_credentials", "password")
                .authorities("ROLE_CLIENT")
                .scopes("read")
                .resourceIds(RESOURCE_ID)
                .secret("client-secret");
    }
}
