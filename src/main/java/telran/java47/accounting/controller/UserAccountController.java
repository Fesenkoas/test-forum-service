package telran.java47.accounting.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java47.accounting.dto.RolesDto;
import telran.java47.accounting.dto.UserDto;
import telran.java47.accounting.dto.UserEditorDto;
import telran.java47.accounting.dto.UserRegesterDto;
import telran.java47.accounting.service.UserAccountService;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class UserAccountController {

	final UserAccountService userAccountService;

	@PostMapping("/register")
	public UserDto register(@RequestBody UserRegesterDto userRegesterDto) {
		return userAccountService.register(userRegesterDto);
	}
	
//	@PostMapping("/login")
//	public UserDto login(@RequestHeader("Authorizacion")String authorization) {
//		return userAccountService.getUser(null);
//	}
	
	@PostMapping("/login")
	public UserDto login(Principal principal) {
		return userAccountService.getUser(principal.getName());
	}

	@GetMapping("/user/{login}")
	public UserDto getUser(@PathVariable String login) {
		return userAccountService.getUser(login);
	}

	@DeleteMapping("/user/{login}")
	public UserDto deleteUser(@PathVariable String login) {
		return userAccountService.deleteUser(login);
	}

	@PutMapping("/user/{login}")
	public UserDto updateUser(@PathVariable String login,@RequestBody UserEditorDto userEditorDto) {
		// TODO Auto-generated method stub
		return userAccountService.updateUser(login, userEditorDto);
	}

	@PutMapping("/user/{login}/role/{role}")
	public RolesDto addRolesList(@PathVariable String login, @PathVariable String role) {
		return userAccountService.changeRolesList(login, role, true);
	}
	
	@DeleteMapping("/user/{login}/role/{role}")
	public RolesDto deleteRolesList(@PathVariable String login, @PathVariable String role) {
		return userAccountService.changeRolesList(login, role, false);
	}

	@PutMapping("/password")
	public void changePassword(Principal principal,@RequestHeader("X-Password") String newPasword) {
		userAccountService.changePassword(principal.getName(), newPasword);
		
	}

}
