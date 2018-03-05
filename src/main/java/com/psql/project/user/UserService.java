package com.psql.project.user;

import com.psql.project.vo.UserDTO;

public interface UserService {

	void addUser(UserDTO userDTO) throws Exception;

	void testUser() throws Exception;

}
