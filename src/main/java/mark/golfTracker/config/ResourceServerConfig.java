package mark.golfTracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;


@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/",
                        "/h2-console/**",
                        "/v2/api-docs",
                        "/webjars/**",
                        "/createNewUser",
                        "/rounds/**",
                        "/holes/**")
                .permitAll()
                .antMatchers(HttpMethod.POST,
                        "/users/**")
                .hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,
                        "/users/**")
                .hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PUT,
                        "/users/**")
                .hasAnyRole("ADMIN")
                .antMatchers("/users/**",
                        "/oauth/revoke-token",
                        "/logout")
                .authenticated()
                .antMatchers("/roles/**")
                .hasAnyRole("ADMIN")
                .anyRequest()
                .denyAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new OAuth2AccessDeniedHandler());
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.logout().disable();
    }
}
