package com.ecommerce.service;

import com.ecommerce.entity.User;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


@Service
@Transactional
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User createUser(User user)
	{
		if (userRepository.existsByUsername(user.getUsername()))
		{
			throw new BadRequestException("Username already exists");
		}
		if (userRepository.existsByEmail(user.getEmail()))
		{
			throw new BadRequestException("Email already exists");
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public User getUserById(Long id)
	{
		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
	}

	public User getUserByUsername(String username)
	{
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
	}

	public User updateUser(Long id, User userDetails)
	{
		User user = getUserById(id);

		if (!user.getUsername().equals(userDetails.getUsername()) &&
				userRepository.existsByUsername(userDetails.getUsername()))
		{
			throw new BadRequestException("Username already exists");
		}

		if (!user.getEmail().equals(userDetails.getEmail()) &&
				userRepository.existsByEmail(userDetails.getEmail()))
		{
			throw new BadRequestException("Email already exists");
		}

		user.setUsername(userDetails.getUsername());
		user.setEmail(userDetails.getEmail());
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());

		return userRepository.save(user);
	}

	public void changePassword(Long userId, String oldPassword, String newPassword)
	{
		User user = getUserById(userId);

		if (!passwordEncoder.matches(oldPassword, user.getPassword()))
		{
			throw new BadRequestException("Current password is incorrect");
		}

		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
	}

	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}

	public List<User> getActiveUsers()
	{
		return userRepository.findByIsActiveTrue();
	}

	public void deactivateUser(Long id)
	{
		User user = getUserById(id);
		user.setActive(false);
		userRepository.save(user);
	}

	public void activateUser(Long id)
	{
		User user = getUserById(id);
		user.setActive(true);
		userRepository.save(user);
	}

	@Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		return null;
	}
}