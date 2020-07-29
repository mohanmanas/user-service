package com.jpop.userservice.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

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
	
	private BigDecimal amountDue;
	
	@CreatedBy
	private String createdUser;
	
	@CreatedDate
	private LocalDateTime createdDateTime;
	
	@LastModifiedBy
	private String modifiedUser;
	
	@LastModifiedDate
	private LocalDateTime modifiedDateTime;
	
	@Version
	private long version;
}