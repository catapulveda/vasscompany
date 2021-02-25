package com.vasscompany.config;

import com.vasscompany.domain.AccesTokenMapper;
import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtConverter extends DefaultAccessTokenConverter implements JwtAccessTokenConverterConfigurer {

    @Override
    public void configure(JwtAccessTokenConverter jwtAccessTokenConverter) {
        jwtAccessTokenConverter.setAccessTokenConverter(this);
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        OAuth2Authentication  oauth = super.extractAuthentication(map);
        AccesTokenMapper details = new AccesTokenMapper();

        if (map.get("id") != null){
            details.setId(String.valueOf(map.get("id")));
        }
        if (map.get("username") != null) {
            details.setUserName(map.get("username").toString());
        }
        if (map.get("email") != null) {
            details.setEmail(map.get("email").toString());
        }

        oauth.setDetails(details);
        return oauth;
    }
}
