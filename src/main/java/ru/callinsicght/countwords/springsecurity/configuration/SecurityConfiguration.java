package ru.callinsicght.countwords.springsecurity.configuration;

import org.apache.log4j.Logger;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.apache.log4j.Logger.getLogger;

/**
 * TODO: comment
 *
 * @author Alexander Kaleganov
 * @version 0.0.1
 */
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final static Logger LOGGER = getLogger(SecurityConfiguration.class);
}
