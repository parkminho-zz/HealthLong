package com.healthlong.hellong.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtill {
	private String key = "SSAFY_Project_GawnTong_SecretKey";
	private SecretKey secretkey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
	
	// 다양한 데이터를 매브올 받아서 처리를 핳ㄹ 수 도 있지만 <= 그럼 무슨정보를 ㅏㄱ져올까?
	// 심플하게 ID만 받아서 토큰을 ㅁ나드엊보자
	public String createToken(String id) {
		Date exp = new Date(System.currentTimeMillis() + 1000 * 60 * 60); //1시간
		return Jwts.builder().header().add("typ", "JWT").and().claim("id", id)
				.expiration(exp).signWith(secretkey).compact();
	}
	
	//실제로 확인하려는 용도가아니고 유효기간이 지났다면 아아서 에러처리하려고함.
	public Jws<Claims> vaildate(String token){
		return Jwts.parser().verifyWith(secretkey).build().parseSignedClaims(token);
		
	}
}
