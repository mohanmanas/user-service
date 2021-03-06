package com.jpop.userservice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.jpop.userservice.model.User;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {

	private int userId;
	
	private String userName;
	
	private LocalDate dob;
	
	private String email;
	
	private String phoneNumber;
	
	private BigDecimal amountDue;
	
	public static UserDto toUserDto(User user) {
		return UserDto.builder()
				.userId(user.getUserId())
				.userName(user.getUserName())
				.dob(user.getDob())
				.email(user.getEmail())
				.phoneNumber(user.getPhoneNumber())
				.amountDue(user.getAmountDue())
				.build();
	}
	
	public static User fromUserDto(UserDto userDto) {
		return User.builder()
				.userId(userDto.getUserId())
				.userName(userDto.getUserName())
				.dob(userDto.getDob())
				.email(userDto.getEmail())
				.phoneNumber(userDto.getPhoneNumber())
				.amountDue(userDto.getAmountDue())
				.build();
	}
}
