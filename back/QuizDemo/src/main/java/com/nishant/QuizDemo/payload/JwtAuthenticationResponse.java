package com.nishant.QuizDemo.payload;

import com.nishant.QuizDemo.model.Role;

public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Role role;
    private String emailId;

    public JwtAuthenticationResponse(String accessToken,Role role,String emailId) {
        this.accessToken = accessToken;
        this.role = role;
        this.emailId = emailId;
    }
    
    public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
    
}