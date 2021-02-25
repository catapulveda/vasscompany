package com.vasscompany.config;

import com.vasscompany.entities.UsuarioEntity;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.LinkedHashMap;
import java.util.Map;

public class CustomTokenEnhancer extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        UsuarioEntity usuarioEntity = (UsuarioEntity) authentication.getPrincipal();

        Map<String, Object> info = new LinkedHashMap<String, Object>(accessToken.getAdditionalInformation());

        if (usuarioEntity.getId() != null) {
            info.put("id", usuarioEntity.getId());
        }
        if (usuarioEntity.getUsername() != null){
            info.put("username", usuarioEntity.getUsername());
        }
        if (usuarioEntity.getEmail() != null) {
            info.put("email", usuarioEntity.getEmail());
        }

        DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
        customAccessToken.setAdditionalInformation(info);

        return super.enhance(customAccessToken, authentication);
    }
}
