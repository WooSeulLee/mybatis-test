package com.mybatis.test.service;

import java.util.List;

import com.mybatis.test.repository.UserRepository;
import com.mybatis.test.vo.UserVO;

public class UserService {

	private UserRepository ur = new UserRepository();
	
	public List<UserVO> getUsers(UserVO user){
		return ur.selectUsers(user);
	}

	public UserVO getUser(int uiNum) {
		return ur.selectUser(uiNum);
	}

	public UserVO login(UserVO user) {
		return ur.selectUserForLogin(user);
	}
	
	public int insertUser(UserVO user) {
		return ur.insertUser(user);
	}
	
	public int updateUser(UserVO user) {
		return ur.updateUser(user);
	}
	
	public int deleteUser(UserVO user) {
		return ur.deleteUser(user);
	}
}

