package com.healthlong.hellong.model.dao;

import org.apache.ibatis.annotations.Param;

import com.healthlong.hellong.model.dto.User;
import java.time.LocalDateTime;

public interface UserDao {

	// 이메일중복확인
	public int emailCheck(String email);

	// 카카오유저, 일반유저 확인
	public int isUserCheck(String email);

	// 이름확인
	public String isNameCheck(String email);
	
	//비밀번호 확인
	public String isPasswordCheck(String email);
	
	// 종료시간확인
	public int isEndTimeCheck(String email);

	// 시작시간확인
	public LocalDateTime isStartTimeCheck(String email);
	
	// 잔여시간확인
	public int isRemainTimeCheck(String email);
	
	// 핸드폰버ㅗㄴ호 확인
	public String isUserPhNum(String email);
	
	// 카카오유저등록
	public int insertKakaoUser(User user);
	
	// 유저 시간 +
	public void updateRemainTime(@Param("email") String email, @Param("time") int time);

	public void updateEndTime(String email);
	
	//유저 닉네임(이름) 바꾸기 
	public int updateUser(@Param("email") String email,  @Param("name")String name, @Param("password") String password);

	//카카오유저 닉네임(이름) 바꾸기 
	public int updateKakaoUser(@Param("email") String email,  @Param("name")String name);


	
	// 이메일유저 등록
	public int insertUser(User user);

	// 유저삭제
	
	// 유저 목록 조회
}
