package com.ecommerce.entity;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends BaseEntity
{

	// Getters and setters
	@NotBlank
	@Size(min = 3, max = 50)
	@Column(unique = true)
	private String username;

	@NotBlank
	@Email
	@Column(unique = true)
	private String email;

	@NotBlank @Size(min = 6)
	private String password;

	@NotBlank @Size(max = 50)
	private String firstName;

	@NotBlank @Size(max = 50)
	private String lastName;

	@Enumerated(EnumType.STRING)
	private Role role = Role.USER;

	@Column(name = "is_active")
	private boolean isActive = true;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Cart> carts;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Order> orders;

	public enum Role
	{
		USER, ADMIN
	}


}
