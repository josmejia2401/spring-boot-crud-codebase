package com.josmejia2401.service;

import com.josmejia2401.dto.UserReqDTO;
import com.josmejia2401.dto.UserResDTO;

import java.util.List;

public interface IUserService {
	List<UserResDTO> getAll(UserReqDTO req);
	UserResDTO getById(Long id);
	void deleteById(Long id);
	UserResDTO update(UserReqDTO req);
	UserResDTO create(UserReqDTO req);
}
