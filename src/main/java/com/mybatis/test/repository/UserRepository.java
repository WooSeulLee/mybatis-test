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
}
