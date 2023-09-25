package com.example.yanolja.user;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class CustomUserDetails implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	private int userid;
	private String name;
	private String email;
	private String password;
	private Timestamp createdate;
	private Timestamp modifydate;
	private int phone;
	private String masteryn;

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(this.masteryn));
	}

	public CustomUserDetails(int userid, String name, String email, String password, Timestamp createdate,
			Timestamp modifydate, int phone, String masteryn) {
		super();
		this.userid = userid;
		this.name = name;
		this.email = email;
		this.password = password;
		this.createdate = createdate;
		this.modifydate = modifydate;
		this.phone = phone;
		this.masteryn = masteryn;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;

	}

	public String getUName() {
		return this.name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
