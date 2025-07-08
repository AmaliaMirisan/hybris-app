package com.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	Optional<Cart> findBySessionId(String sessionId);

	List<Cart> findByUser(User user);

	@Query("SELECT c FROM Cart c WHERE c.user IS NULL AND c.createdAt < :cutoffDate")
	List<Cart> findAbandonedGuestCarts(@Param("cutoffDate") java.time.LocalDateTime cutoffDate);

	void deleteBySessionId(String sessionId);
}
