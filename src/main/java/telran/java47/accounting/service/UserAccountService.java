package telran.java47.accounting.service;

import telran.java47.accounting.dto.RolesDto;
import telran.java47.accounting.dto.UserDto;
import telran.java47.accounting.dto.UserEditorDto;
import telran.java47.accounting.dto.UserRegesterDto;

public interface UserAccountService {
	
	UserDto register ( UserRegesterDto userRegesterDto);
	
	UserDto getUser (String login);
	
	UserDto deleteUser (String login);
	
	UserDto updateUser (String login, UserEditorDto userEditorDto);
	
	RolesDto changeRolesList (String login, String role, Boolean isAddRole);
	
	void changePassword (String login, String newPasword);

}
