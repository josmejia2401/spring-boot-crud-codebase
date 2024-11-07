package com.josmejia2401.service;

import com.josmejia2401.dto.UserReqDTO;
import com.josmejia2401.dto.UserResDTO;
import com.josmejia2401.exceptions.CustomException;
import com.josmejia2401.models.UserModel;
import com.josmejia2401.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Description("Servicio para gestionar los usuarios")
@Service
@Log4j2
public class UserService implements IUserService {

	@Autowired
	private EntityManager em;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<UserResDTO> getAll(UserReqDTO req) {
		StringBuilder sql = new StringBuilder("SELECT * FROM functions");
		if (req.getRole() != null) {
			sql.append(String.format(" WHERE role = '%s'", req.getRole()));
		}
		Query query = em.createNativeQuery(sql.toString(), UserModel.class);
		List<?> results = query.getResultList();
		Type listType = new TypeToken<List<UserResDTO>>(){}.getType();
		return modelMapper.map(results, listType);
	}

	@Override
	public UserResDTO getById(Long id) {
		UserModel model = this.userRepository.findById(id).orElseThrow(() -> new CustomException(404, "Elemento no existe."));
		return modelMapper.map(model, UserResDTO.class);
	}

	@Override
	public void deleteById(Long id) {
		UserResDTO model = this.getById(id);
		this.userRepository.deleteById(model.getId());
	}

	@Override
	public UserResDTO update(UserReqDTO req) {
		UserResDTO data = this.getById(req.getId());
		UserModel model = modelMapper.map(req, UserModel.class);
		model.setCreatedAt(data.getCreatedAt());
		this.userRepository.saveAndFlush(model);
		return modelMapper.map(model, UserResDTO.class);
	}

	@Override
	public UserResDTO create(UserReqDTO req) {
		UserModel model = modelMapper.map(req, UserModel.class);
		this.userRepository.saveAndFlush(model);
		return modelMapper.map(model, UserResDTO.class);
	}
}
