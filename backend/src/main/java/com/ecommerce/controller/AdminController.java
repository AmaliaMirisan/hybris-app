package com.ecommerce.controller;

import com.ecommerce.dto.request.CategoryRequest;
import com.ecommerce.dto.request.ProductRequest;
import com.ecommerce.dto.response.ApiResponse;
import com.ecommerce.dto.response.OrderResponse;
import com.ecommerce.dto.response.ProductResponse;
import com.ecommerce.entity.*;
import com.ecommerce.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AdminService adminService;

    // Dashboard
    @GetMapping("/dashboard")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getDashboard() {
        Map<String, Object> dashboardStats = adminService.getDashboardStats();
        return ResponseEntity.ok(ApiResponse.success(dashboardStats));
    }

    // Product Management
    @PostMapping("/products")
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        Product product = convertToProduct(productRequest);
        Product savedProduct = productService.createProduct(product);
        ProductResponse productResponse = convertToProductResponse(savedProduct);
        return ResponseEntity.ok(ApiResponse.success("Product created successfully", productResponse));
    }

    @GetMapping("/products")
    public ResponseEntity<ApiResponse<Page<ProductResponse>>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productService.getAllActiveProducts(pageable);
        Page<ProductResponse> productResponses = products.map(this::convertToProductResponse);
        
        return ResponseEntity.ok(ApiResponse.success(productResponses));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        ProductResponse productResponse = convertToProductResponse(product);
        return ResponseEntity.ok(ApiResponse.success(productResponse));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest productRequest) {
        
        Product productDetails = convertToProduct(productRequest);
        Product updatedProduct = productService.updateProduct(id, productDetails);
        ProductResponse productResponse = convertToProductResponse(updatedProduct);
        
        return ResponseEntity.ok(ApiResponse.success("Product updated successfully", productResponse));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<ApiResponse<String>> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(ApiResponse.success("Product deleted successfully"));
    }

    @PostMapping("/products/{id}/stock")
    public ResponseEntity<ApiResponse<String>> updateStock(
            @PathVariable Long id,
            @RequestParam Integer quantity) {
        
        productService.updateStock(id, quantity);
        return ResponseEntity.ok(ApiResponse.success("Stock updated successfully"));
    }

    @GetMapping("/products/low-stock")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getLowStockProducts() {
        List<Product> products = adminService.getLowStockProducts();
        List<ProductResponse> productResponses = products.stream()
            .map(this::convertToProductResponse)
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(ApiResponse.success(productResponses));
    }

    // Category Management
    @PostMapping("/categories")
    public ResponseEntity<ApiResponse<Category>> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        Category category = convertToCategory(categoryRequest);
        Category savedCategory = categoryService.createCategory(category);
        return ResponseEntity.ok(ApiResponse.success("Category created successfully", savedCategory));
    }

    @GetMapping("/categories")
    public ResponseEntity<ApiResponse<List<Category>>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(ApiResponse.success(categories));
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<ApiResponse<Category>> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequest categoryRequest) {
        
        Category categoryDetails = convertToCategory(categoryRequest);
        Category updatedCategory = categoryService.updateCategory(id, categoryDetails);
        
        return ResponseEntity.ok(ApiResponse.success("Category updated successfully", updatedCategory));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(ApiResponse.success("Category deleted successfully"));
    }

    // Order Management
    @GetMapping("/orders")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        List<OrderResponse> orderResponses = orders.stream()
            .map(this::convertToOrderResponse)
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(ApiResponse.success(orderResponses));
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        OrderResponse orderResponse = convertToOrderResponse(order);
        return ResponseEntity.ok(ApiResponse.success(orderResponse));
    }

    @PutMapping("/orders/{id}/status")
    public ResponseEntity<ApiResponse<OrderResponse>> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        
        Order.OrderStatus orderStatus = Order.OrderStatus.valueOf(status.toUpperCase());
        Order updatedOrder = orderService.updateOrderStatus(id, orderStatus);
        OrderResponse orderResponse = convertToOrderResponse(updatedOrder);
        
        return ResponseEntity.ok(ApiResponse.success("Order status updated successfully", orderResponse));
    }

    @GetMapping("/orders/status/{status}")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getOrdersByStatus(@PathVariable String status) {
        Order.OrderStatus orderStatus = Order.OrderStatus.valueOf(status.toUpperCase());
        List<Order> orders = adminService.getOrdersByStatus(orderStatus);
        List<OrderResponse> orderResponses = orders.stream()
            .map(this::convertToOrderResponse)
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(ApiResponse.success(orderResponses));
    }

    @GetMapping("/orders/stats")
    public ResponseEntity<ApiResponse<Map<String, Long>>> getOrderStats() {
        Map<String, Long> orderStats = adminService.getOrderStatusCounts();
        return ResponseEntity.ok(ApiResponse.success(orderStats));
    }

    // Helper methods
    private Product convertToProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setImageUrl(productRequest.getImageUrl());
        product.setActive(productRequest.isActive());
        
        if (productRequest.getCategoryId() != null) {
            Category category = new Category();
            category.setId(productRequest.getCategoryId());
            product.setCategory(category);
        }
        
        return product;
    }

    private Category convertToCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());
        return category;
    }

    private ProductResponse convertToProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStockQuantity(product.getStockQuantity());
        response.setCategoryId(product.getCategory().getId());
        response.setCategoryName(product.getCategory().getName());
        response.setImageUrl(product.getImageUrl());
        response.setActive(product.isActive());
        response.setCreatedAt(product.getCreatedAt());
        response.setUpdatedAt(product.getUpdatedAt());
        return response;
    }

    private OrderResponse convertToOrderResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setOrderNumber(order.getOrderNumber());
        response.setTotalAmount(order.getTotalAmount());
        response.setStatus(order.getStatus().name());
        response.setShippingAddress(order.getShippingAddress());
        response.setPaymentMethod(order.getPaymentMethod());
        response.setCreatedAt(order.getCreatedAt());
        return response;
    }
}
