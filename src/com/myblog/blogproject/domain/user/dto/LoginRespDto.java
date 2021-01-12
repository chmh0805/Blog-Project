package com.myblog.blogproject.domain.user.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginRespDto {
	private int id;
	private String username;
	private String email;
	private String role;
}
