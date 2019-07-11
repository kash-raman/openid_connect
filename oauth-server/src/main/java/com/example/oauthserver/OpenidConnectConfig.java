package com.example.oauthserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class OpenidConnectConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
    @Value("classpath:oauth-schema.sql")
    private Resource schemaScript;

    @Value("classpath:user_data.sql")
    private Resource dataScript;
    @Autowired
    DataSource dataSource;


    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore())
                .authorizationCodeServices(getAuthorizationCodeServices())
                .authenticationManager(authenticationManager);
    }

    @Bean
    public JdbcAuthorizationCodeServices getAuthorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("sample-web")
                .secret(("secret"))
                .authorizedGrantTypes("password", "refresh_token", "authorization_code")
                .scopes("default")
                .autoApprove(true)
                .redirectUris("http://localhost:8082/test", "http://127.0.0.1:8082/test")
                // .accessTokenValiditySeconds(3600)
                .and()
                .withClient("sample-web-token")
                .secret(("secret"))
                .authorizedGrantTypes("implicit")
                .scopes("user_info")
                .autoApprove(true)
                .redirectUris("http://localhost:8083/myToken", "http://127.0.0.1:8083/myToken")
                .and()
                .withClient("multi-web-token")
                .secret(("secret"))
                .authorizedGrantTypes("implicit")
                .scopes("user_info")
                .autoApprove(true)
                .redirectUris("http://localhost:8083/myToken", "http://127.0.0.1:8083/myToken")
        ; // 1 hour
    }


    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource());
    }

    private DataSource dataSource() {
        return dataSource;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(schemaScript);
        populator.addScript(dataScript);
        return populator;
    }
}
