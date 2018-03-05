package com.psql.project.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.psql.project.vo.UserDTO;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserMapper userMapper;

	@Override
	public void addUser(UserDTO userDTO) throws Exception {
		userMapper.addUser(userDTO);
		
	}

	@Override
	public void testUser() throws Exception {
		int testNum = userMapper.testUser();
		

		
	}

}
