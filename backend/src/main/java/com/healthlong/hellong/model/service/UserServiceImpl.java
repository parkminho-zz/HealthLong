package com.healthlong.hellong.model.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthlong.hellong.model.dao.UserDao;
import com.healthlong.hellong.model.dto.User;

@Service
public class UserServiceImpl implements UserService{

	private final UserDao userDao; 
	
	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	

	//이메일 중복확인 로직
	@Override
	public boolean isEmailCheck(String email) {
		// count(*)를 사용하므로 결과 값이 1 또는 0이 됩니다.
	    return userDao.emailCheck(email) == 1; 
	}
	
	//카카오유저, 일반유저 확인로직
	@Override
	public int isUserCheck(String email) {
		return userDao.isUserCheck(email);
	}
	
	//이름 확인
	@Override
	public String isNameCheck(String email) {
		return userDao.isNameCheck(email);
	}
	
	@Override
	public boolean registKakaoUser(User user) {
		return userDao.insertKakaoUser(user) == 1;
	}


	@Override
	public String isUserPhNum(String email) {
		return userDao.isUserPhNum(email);
	}


	@Override
	public void updateRemainTime(String email, int time) {
		userDao.updateRemainTime(email, time);
	}
	
	@Override
	public boolean modifyUser(String email, String name, String password) {
		return userDao.updateUser(email, name, password) == 1;
	}
	
	@Override
	public boolean modifyKakaoUser(String email, String name) {
		return userDao.updateKakaoUser(email, name) == 1;
	}


	@Override
	public int isEndTimeCheck(String email) {
	    return userDao.isEndTimeCheck(email);
	}
	
	@Override
	public LocalDateTime isStartTimeCheck(String email) {
		return userDao.isStartTimeCheck(email);
	}


	@Override
	public int isRemainTimeCheck(String email) {
		return userDao.isRemainTimeCheck(email);
	}


	@Override
	public boolean registUser(User user) {
	    return userDao.insertUser(user) == 1; 
	}


	@Override
	public String isPasswordCheck(String email) {
		return userDao.isPasswordCheck(email);
	}


	@Override
	public void updateEndTime(String email) {
		userDao.updateEndTime(email);
	}

}
