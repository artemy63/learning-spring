package simple.oauth.resourceowner.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by volkovan on 22-Mar-17.
 */
@Configuration
@EnableResourceServer
public class ResourceServer extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.anonymous().and()
//                .antMatcher("/").anonymous().and()
                .authorizeRequests()
                .antMatchers("/secured").hasAnyRole("ADMIN");
//                .anyRequest().access("#oauth2.hasScope('read')");
        Map<String, String> permissions = new HashMap<>();
        permissions.put("/secured2", "hasRole('USER')");
        for (Map.Entry<String, String> permission : permissions.entrySet()) {
            http.authorizeRequests().antMatchers(permission.getKey()).access(permission.getValue());
        }
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("resource#1");
    }
}
