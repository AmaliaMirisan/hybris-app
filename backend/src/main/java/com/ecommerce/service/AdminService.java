package com.ecommerce.service;

import com.ecommerce.entity.Order;
import com.ecommerce.entity.Product;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime todayStart = now.truncatedTo(ChronoUnit.DAYS);
        LocalDateTime weekStart = now.minusDays(7);
        LocalDateTime monthStart = now.minusDays(30);

        // Order statistics
        stats.put("totalOrders", orderRepository.count());
        stats.put("todayOrders", orderRepository.countOrdersSince(todayStart));
        stats.put("weekOrders", orderRepository.countOrdersSince(weekStart));
        stats.put("monthOrders", orderRepository.countOrdersSince(monthStart));

        // Revenue statistics
        BigDecimal todayRevenue = orderRepository.getTotalRevenue(todayStart, now);
        BigDecimal weekRevenue = orderRepository.getTotalRevenue(weekStart, now);
        BigDecimal monthRevenue = orderRepository.getTotalRevenue(monthStart, now);
        
        stats.put("todayRevenue", todayRevenue != null ? todayRevenue : BigDecimal.ZERO);
        stats.put("weekRevenue", weekRevenue != null ? weekRevenue : BigDecimal.ZERO);
        stats.put("monthRevenue", monthRevenue != null ? monthRevenue : BigDecimal.ZERO);

        // Product statistics
        stats.put("totalProducts", productRepository.countActiveProducts());
        stats.put("lowStockProducts", productRepository.findLowStockProducts(10).size());

        // User statistics
        stats.put("totalUsers", userRepository.count());
        stats.put("activeUsers", userRepository.findByIsActiveTrue().size());

        // Recent orders
        stats.put("recentOrders", orderRepository.findRecentOrders(Pageable.ofSize(10)));

        return stats;
    }

    public List<Product> getLowStockProducts() {
        return productRepository.findLowStockProducts(10);
    }

    public List<Order> getOrdersByStatus(Order.OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    public Map<String, Long> getOrderStatusCounts() {
        Map<String, Long> statusCounts = new HashMap<>();
        
        for (Order.OrderStatus status : Order.OrderStatus.values()) {
            Long count = (long) orderRepository.findByStatus(status).size();
            statusCounts.put(status.name(), count);
        }
        
        return statusCounts;
    }
}
