package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.User;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long>
{

	Optional<Cart> findBySessionId(String sessionId);

	List<Cart> findByUser(User user);

	@Query("SELECT c FROM Cart c WHERE c.user IS NULL AND c.createdAt < :cutoffDate")
	List<Cart> findAbandonedGuestCarts(@Param("cutoffDate") java.time.LocalDateTime cutoffDate);

	void deleteBySessionId(String sessionId);
}
