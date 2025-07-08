package com.ecommerce.controller;

import java.util.HashMap;
import java.util.Map;

import com.ecommerce.dto.request.LoginRequest;
import com.ecommerce.dto.request.RegisterRequest;
import com.ecommerce.dto.response.ApiResponse;
import com.ecommerce.entity.User;
import com.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
public class AuthController {


	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<Map<String, Object>>> login(@Valid @RequestBody LoginRequest loginRequest) {
		try {
			// Find user by username
			User user = userService.getUserByUsername(loginRequest.getUsername());

			// Check if user is active
			if (!user.isActive()) {
				return ResponseEntity.badRequest()
						.body(ApiResponse.error("Account is deactivated"));
			}

			// Verify password manually
			if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
				return ResponseEntity.badRequest()
						.body(ApiResponse.error("Invalid username or password"));
			}

			// Create response with user information
			Map<String, Object> loginResponse = new HashMap<>();
			loginResponse.put("id", user.getId());
			loginResponse.put("username", user.getUsername());
			loginResponse.put("email", user.getEmail());
			loginResponse.put("firstName", user.getFirstName());
			loginResponse.put("lastName", user.getLastName());
			loginResponse.put("role", user.getRole().name());
			loginResponse.put("isActive", user.isActive());

			return ResponseEntity.ok(ApiResponse.success("Login successful", loginResponse));

		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body(ApiResponse.error("Invalid username or password"));
		}
	}

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<String>> register(@Valid @RequestBody RegisterRequest registerRequest) {
		User user = new User();
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(registerRequest.getPassword());
		user.setFirstName(registerRequest.getFirstName());
		user.setLastName(registerRequest.getLastName());
		user.setRole(User.Role.USER);

		userService.createUser(user);
		return ResponseEntity.ok(ApiResponse.success("User registered successfully"));
	}

	@PostMapping("/logout")
	public ResponseEntity<ApiResponse<String>> logout() {
		SecurityContextHolder.clearContext();
		return ResponseEntity.ok(ApiResponse.success("Logout successful"));
	}
}