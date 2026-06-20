package com.premit.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premit.dao.repository.UserRepository;
import com.premit.dto.UserDto;
import com.premit.entity.UserEntity;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;	
	

	public String userRegistration(UserDto userDto) {
		
		UserEntity userEntity = new UserEntity();
		userEntity.setContact(userDto.getContact());
		userEntity.setDob(userDto.getDob());
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setGender(userDto.getGender());
		userEntity.setLastName(userDto.getLastName());
		userEntity.setMailId(userDto.getMailId());
		userEntity.setPassword(userDto.getPassword());
		userEntity.setUserName(userDto.getUserName());
		
		Optional<UserEntity> optionalOfRetrievedUser = userRepository.findById(userEntity.getUserName());
		if (optionalOfRetrievedUser.isPresent()) {
			return "User already existed with username : "+userEntity.getUserName()+", Please try again with other username.";
		} else {
			UserEntity savedEntity = userRepository.save(userEntity);
			if(savedEntity!=null) {
				return "User registered successfully with username : "+savedEntity.getUserName();
			} else {
				return "User registration failed, Please try again.";
			}
		}
	}
}
