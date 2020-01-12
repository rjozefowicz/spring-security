package dev.jozefowicz.springsecurity.centralizedconfig.application.configuration;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.method.MapBasedMethodSecurityMetadataSource;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableGlobalMethodSecurity
public class CustomGlobalMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {

    @Override
    protected MethodSecurityMetadataSource customMethodSecurityMetadataSource() {
        Map<String, List<ConfigAttribute>> attrMap = new HashMap<>();
        attrMap.put("dev.jozefowicz.springsecurity.centralizedconfig.application.controller.MeController.me",
                SecurityConfig.createList("ROLE_USER", "ROLE_ADMIN"));
        attrMap.put("dev.jozefowicz.springsecurity.centralizedconfig.module1.controller.Module1Controller.module1",
                SecurityConfig.createList( "ROLE_ADMIN"));
        return new MapBasedMethodSecurityMetadataSource(attrMap);
    }

}
