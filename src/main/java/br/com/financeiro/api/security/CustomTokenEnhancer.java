package br.com.financeiro.api.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import br.com.financeiro.api.model.SystemUser;

public class CustomTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		SystemUser systemUser = (SystemUser) authentication.getPrincipal();

		Map<String, Object> addInfo = new HashMap<>();
		addInfo.put("nome", systemUser.getUsuario().getName());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
		
		return accessToken;
	}

}
