package com.soo.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author KHS
 */
@Component
@ConfigurationProperties(prefix = "user")
public class UserProperty {
}
