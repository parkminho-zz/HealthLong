package com.healthlong.hellong.model.service;

public interface OAuthService {
	public String getKakaoAccessToken(String code);
	
	public String createKakaoUser(String token);

}
