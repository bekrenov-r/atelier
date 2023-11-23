package com.group.atelier.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {
    private static final String AUTHORITIES_BY_USERNAME_QUERY =
            """
                select user_role.role from "user"
                join user_role on "user".id = user_role.user_id
                where "user".username = ?
            """;

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME_QUERY);
        return jdbcUserDetailsManager;
    }
}
