package com.josmejia2401.controller;

import com.josmejia2401.dto.UserReqDTO;
import com.josmejia2401.dto.UserResDTO;
import com.josmejia2401.exceptions.CustomErrorResponse;
import com.josmejia2401.exceptions.CustomException;
import com.josmejia2401.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/users")
@Log4j2
public class UserController {

	@Autowired
	private IUserService iUserService;

	@Operation(
			summary = "Recupera los elementos",
			description = "Método GET para recuperar los elementos por filtros de búsqueda",
			tags = { "shows", "get" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserResDTO.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = CustomErrorResponse.class), mediaType = "application/json") }) })
	@GetMapping("")
	public ResponseEntity<List<?>> getAll(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  Date createdAt,
										  @RequestParam(required = false) String dni,
										  @RequestParam(required = false) String email,
										  @RequestParam(required = false) String role,
										  @RequestParam(required = false) String name) {
		try {
			List<UserResDTO> models = this.iUserService.getAll(UserReqDTO
					.builder()
							.createdAt(createdAt)
							.dni(dni)
							.email(email)
							.role(role)
							.name(name)
					.build());
			if (models.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(models, HttpStatus.OK);
		} catch (CustomException e) {
			log.error(e);
			throw e;
		} catch (Exception e) {
			log.error(e);
			throw new CustomException(e);
		}
	}

	@Operation(
			summary = "Recupera un elemento por ID",
			description = "Método GET para recuperar un elemento por ID",
			tags = { "show", "get" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserResDTO.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = CustomErrorResponse.class), mediaType = "application/json") }) })
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") long id) {
		try {
			UserResDTO data = this.iUserService.getById(id);
			if (data != null) {
				return new ResponseEntity<>(data, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (CustomException e) {
			log.error(e);
			throw e;
		} catch (Exception e) {
			log.error(e);
			throw new CustomException(e);
		}
	}

	@Operation(
			summary = "Crea un elemento",
			description = "Método POST para crear un elemento",
			tags = { "show", "post" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserResDTO.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = CustomErrorResponse.class), mediaType = "application/json") }) })
	@PostMapping("")
	public ResponseEntity<?> create(@Valid @RequestBody UserReqDTO req) {
		try {
			return new ResponseEntity<>(this.iUserService.create(req), HttpStatus.CREATED);
		} catch (CustomException e) {
			log.error(e);
			throw e;
		} catch (Exception e) {
			log.error(e);
			throw new CustomException(e);
		}
	}

	@Operation(
			summary = "Actualizar un elemento por ID",
			description = "Método PUT para actualizar un elemento por ID",
			tags = { "show", "put" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserResDTO.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = CustomErrorResponse.class), mediaType = "application/json") }) })
	@PutMapping("/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id") long id, @Valid @RequestBody UserReqDTO req) {
		try {
			req.setId(id);
			return new ResponseEntity<>(this.iUserService.update(req), HttpStatus.OK);
		} catch (CustomException e) {
			log.error(e);
			throw e;
		} catch (Exception e) {
			log.error(e);
			throw new CustomException(e);
		}
	}

	@Operation(
			summary = "Eliminar un elemento por ID",
			description = "Método DELETE para eliminar un elemento por ID",
			tags = { "show", "delete" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = CustomErrorResponse.class), mediaType = "application/json") }) })
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
		try {
			this.iUserService.deleteById(id);
			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (CustomException e) {
			log.error(e);
			throw e;
		} catch (Exception e) {
			log.error(e);
			throw new CustomException(e);
		}
	}
}
