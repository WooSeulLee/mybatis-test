package com.mybatis.test.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mybatis.test.config.SqlMybatisConfig;
import com.mybatis.test.vo.UserVO;

public class UserRepository {

	public List<UserVO> selectUsers(UserVO user){
		try(SqlSession session = SqlMybatisConfig.getSessionFactory().openSession()){
			return session.selectList("UserMapper.selectUsers", user);
		}
	}
	public UserVO selectUser(int uiNum) {
		try(SqlSession session = SqlMybatisConfig.getSessionFactory().openSession()){
			return session.selectOne("UserMapper.selectUser", uiNum);
		}
	}
	public int insertUser(UserVO user) {
		try(SqlSession session = SqlMybatisConfig.getSessionFactory().openSession(true)){
			return session.insert("UserMapper.insertUser", user);
		}
	}
	public int updateUser(UserVO user) {
		try(SqlSession session = SqlMybatisConfig.getSessionFactory().openSession(true)){
			return session.update("UserMapper.updateUser", user);
		}
	}
	public int deleteUser(UserVO user) {
		try(SqlSession session = SqlMybatisConfig.getSessionFactory().openSession(true)){
			return session.delete("UserMapper.deleteUser", user);
		}
	}
	
	public static void main(String[] args) {
		UserRepository ur = new UserRepository();
		UserVO user = new UserVO();
		user.setUiBirth("20221022");
		user.setUiDesc("test data");
		user.setUiGender("1");
		user.setUiHobby("자전거");
		user.setUiId("test1234");
		user.setUiName("테스트");
		user.setUiPwd("1234test");
		int result = ur.insertUser(user);
		System.out.println(result);
		
		List<UserVO> users = ur.selectUsers(null);
		System.out.println(users);
		user = ur.selectUser(4);
		System.out.println(user);
		
		user.setUiBirth("20111212");
		result = ur.updateUser(user);
		
		//result = ur.deleteUser(user);
		
		user = ur.selectUser(4);
		System.out.println(user);
		
	}
}
