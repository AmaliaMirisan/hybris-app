package com.ecommerce.repository;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>
{

	Optional<Cart> findBySessionId(String sessionId);

	List<Cart> findByUser(User user);

	@Query("SELECT c FROM Cart c WHERE c.user IS NULL AND c.createdAt < :cutoffDate")
	List<Cart> findAbandonedGuestCarts(@Param("cutoffDate") java.time.LocalDateTime cutoffDate);

	void deleteBySessionId(String sessionId);
}
