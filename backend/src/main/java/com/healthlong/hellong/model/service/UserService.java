package com.healthlong.hellong.model.service;

import java.time.LocalDateTime;

import com.healthlong.hellong.model.dto.Board;
import com.healthlong.hellong.model.dto.User;

public interface UserService {

	//이메일 중복확인
	public boolean isEmailCheck(String email);

	//카카오유저, 일반유저 확인
	public int isUserCheck(String email);

	//카ㅣ카오 이름확인
	public String isNameCheck(String email);

	//비밀번호 확인
	public String isPasswordCheck(String email);

	//종료시간 확인
	public int isEndTimeCheck(String email);
	
	//종료시간 확인
	public int isRemainTimeCheck(String email);
	
	//시작시작 확인
	public LocalDateTime isStartTimeCheck(String email);
	
	// 캌아오유저 회원가입
	public boolean registKakaoUser(User user);

	//카카오유저 핸드폰번호확인
	public String isUserPhNum(String email);

	// 유저 시간 +
	public void updateRemainTime(String email, int time);

	//끝나느시간설정
	public void updateEndTime(String email);

	// 유저 수정
	public boolean modifyUser(String email, String name, String password);

	// 유저 수정
	public boolean modifyKakaoUser(String email, String name);
	
	// 일반 유저 회원가입
	public boolean registUser(User user);

	
}
