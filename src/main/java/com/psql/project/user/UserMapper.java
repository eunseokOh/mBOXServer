package com.psql.project.user;

import org.apache.ibatis.annotations.Mapper;

import com.psql.project.vo.UserDTO;



@Mapper
public interface UserMapper {

	void addUser(UserDTO userDTO) throws Exception;

	int testUser() throws Exception;

	void testGoodUser(int testUser);
	
}
