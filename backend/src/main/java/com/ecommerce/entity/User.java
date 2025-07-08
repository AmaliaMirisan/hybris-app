package com.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

		@NotBlank
		@Size(min = 3, max = 50)
		@Column(unique = true)
		private String username;

		@NotBlank
		@Email
		@Column(unique = true)
		private String email;

		@NotBlank
		@Size(min = 6)
		private String password;

		@NotBlank
		@Size(max = 50)
		private String firstName;

		@NotBlank
		@Size(max = 50)
		private String lastName;

		@Enumerated(EnumType.STRING)
		private Role role = Role.USER;

		@Column(name = "is_active")
		private boolean isActive = true;

		@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		private List<Cart> carts;

		@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		private List<Order> orders;

		public enum Role {
			USER, ADMIN
		}

		// UserDetails implementation
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
		}

		@Override
		public boolean isAccountNonExpired() { return true; }

		@Override
		public boolean isAccountNonLocked() { return true; }

		@Override
		public boolean isCredentialsNonExpired() { return true; }

		@Override
		public boolean isEnabled() { return isActive; }

		// Getters and setters
		public String getUsername() { return username; }
		public void setUsername(String username) { this.username = username; }

		public String getEmail() { return email; }
		public void setEmail(String email) { this.email = email; }

		public String getPassword() { return password; }
		public void setPassword(String password) { this.password = password; }

		public String getFirstName() { return firstName; }
		public void setFirstName(String firstName) { this.firstName = firstName; }

		public String getLastName() { return lastName; }
		public void setLastName(String lastName) { this.lastName = lastName; }

		public Role getRole() { return role; }
		public void setRole(Role role) { this.role = role; }

		public boolean isActive() { return isActive; }
		public void setActive(boolean active) { isActive = active; }

		public List<Cart> getCarts() { return carts; }
		public void setCarts(List<Cart> carts) { this.carts = carts; }

		public List<Order> getOrders() { return orders; }
		public void setOrders(List<Order> orders) { this.orders = orders; }
	}
}
