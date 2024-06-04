package com.healthlong.hellong.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthlong.hellong.model.dto.User;
import com.healthlong.hellong.model.service.OAuthService;
import com.healthlong.hellong.model.service.PaymentService;
import com.healthlong.hellong.model.service.TicketService;
import com.healthlong.hellong.model.service.UserService;
import com.healthlong.hellong.util.JwtUtill;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/")
public class MainController {

	// 응답을 편하게 하기 위해 상수로 지정
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtUtill jwtUtil;
	private final TicketService ticketService;
	private final OAuthService oAuthService;
	private final UserService userService;

	@Autowired
	public MainController(OAuthService oAuthService,UserService userService, TicketService ticketService) {
		this.oAuthService = oAuthService;
		this.userService = userService;
		this.ticketService = ticketService;
	}
	
	//로그인창				 localhost:8080/login

	//index화면			localhost:8080/index
	
	//main화면			localhost:8080/main

	@GetMapping("/kakaologin")
    public ResponseEntity<?> kakaologin(@RequestParam("code") String code) {
        // 토큰을 요청하여 얻음
//        String kakaoToken = loginService.requestToken(code);
//        System.out.println("카카오토큰: " + kakaoToken);
//    	System.out.println(code);
        // 사용자 정보를 요청하여 얻음
		Map<String, Object> result1 = new HashMap<>();

		System.out.println(code);
		
    	String getToken = oAuthService.getKakaoAccessToken(code);
        String getUserInfo = oAuthService.createKakaoUser(getToken);
        
        System.out.println("userId :" + getUserInfo);
        result1.put("code", code);
        result1.put("getUserInfo", getUserInfo);
        
		if(userService.isEmailCheck(getUserInfo)) {
			if(userService.isUserCheck(getUserInfo) ==2) {
				Map<String, Object> result2 = new HashMap<>();
				String getUserType = userService.isUserCheck(getUserInfo) + ""; 
				String getUserName = userService.isNameCheck(getUserInfo);
				String getUserPhNum = userService.isUserPhNum(getUserInfo);
				int getUserEndTime = userService.isEndTimeCheck(getUserInfo);
				int getUserRemainTime = userService.isRemainTimeCheck(getUserInfo);
				LocalDateTime getUserStartTime = userService.isStartTimeCheck(getUserInfo);
				
//				if(userService.isEndTimeCheck(getUserInfo)) {
//					getUserEndTime ="0";
//				}else {
//					getUserEndTime = userService.isEndTimeCheck(getUserInfo);					
//				}
				
				result2.put("message", SUCCESS);
				result2.put("email", getUserInfo);
				result2.put("name", getUserName);
				result2.put("phNum", getUserPhNum);
				result2.put("type", getUserType);
				result2.put("access-token", jwtUtil.createToken(getUserName));
				result2.put("isEndTime", getUserEndTime);
				result2.put("isRemainTime", getUserRemainTime);
				result2.put("isStartTime", getUserStartTime);
				
				
				return new ResponseEntity<>(result2, HttpStatus.OK);
			}
		}
		 
        return new ResponseEntity<>(result1, HttpStatus.OK);
    }
	//일반유저 회원가입
	@PostMapping("/regist1")
	public ResponseEntity<?> write3(@RequestBody User user ) {
//		System.out.println("123");   
		System.out.println(user); 
		userService.registUser(user);
		user.setRegDate("0");
		user.setStartTime("0");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	@PostMapping("/user/login")
    public ResponseEntity<?> userlogin(@RequestBody Map<String,String> data) {

		String email = data.get("username");
		String password = data.get("password");
		
		if(userService.isEmailCheck(email) && userService.isPasswordCheck(email).equals(password)&&
				userService.isUserCheck(email) ==1 ) {
				Map<String, Object> result2 = new HashMap<>();
				String getUserType = userService.isUserCheck(email) + ""; 
				String getUserName = userService.isNameCheck(email);
				String getUserPhNum = userService.isUserPhNum(email);
				int getUserEndTime = userService.isEndTimeCheck(email);
				int getUserRemainTime = userService.isRemainTimeCheck(email);
				LocalDateTime getUserStartTime = userService.isStartTimeCheck(email);
//				
				result2.put("message", SUCCESS);
				result2.put("email", email);
				result2.put("name", getUserName);
				result2.put("phNum", getUserPhNum);
				result2.put("type", getUserType);
				result2.put("access-token", jwtUtil.createToken(getUserName));
				result2.put("isEndTime", getUserEndTime);
				result2.put("isRemainTime", getUserRemainTime);
				result2.put("isStartTime", getUserStartTime);
//			
//				
				return new ResponseEntity<>(result2, HttpStatus.OK);
		}else {
		    Map<String, String> result = new HashMap<>();
		    result.put("message", "로그인 실패");
		    return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
		}
		 
    }

}
