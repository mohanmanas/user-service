package com.jpop.userservice.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	private int userId;
	
	private String userName;
	
	private LocalDate dob;
	
	private String email;
	
	private String phoneNumber;
}