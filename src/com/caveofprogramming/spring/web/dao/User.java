package com.caveofprogramming.spring.web.dao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {
	
	@NotBlank(message="${NotBlank.user.username}")
	@Size(min = 5, max = 15, message = "${Size.user.usernmae}")
	@Pattern(regexp="^\\w{5,}$", message ="${Pattern.user.username}")
	private String username;
	private String authority;
	
	private boolean enabled;
	
	@NotBlank(message="${NotBlank.user.email}")
	@Email(message="${Email.user.email}")
	private String email;
	
	@NotBlank(message="${Password cannot be blank.}")
	@Pattern(regexp="^\\S+$", message="${Pattern.user.password}")
	@Size(min = 5, max = 80, message = "${Size.user.password }")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "[" + "username: " + username + "email: " + email + "password:" + password + "]";
	}
	
	public boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
