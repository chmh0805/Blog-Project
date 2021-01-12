package com.myblog.blogproject.domain.user;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private String role;
}
