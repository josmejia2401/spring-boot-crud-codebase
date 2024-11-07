package com.josmejia2401.models;

import com.josmejia2401.utils.Constants;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@Entity
@Table(name = "users_tmp", schema = Constants.SCHEMA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "dni")
	private String dni;

	@Column(name = "email")
	private String email;

	@Column(name = "role")
	private String role;

	@Column(name = "created_at")
	@CreationTimestamp
	private Date createdAt;
}
