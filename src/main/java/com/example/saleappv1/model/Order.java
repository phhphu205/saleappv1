package com.example.saleappv1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String customerName;

    @Column(length = 20)
    private String customerPhone;

    @Column(length = 255)
    private String shippingAddress;

    private LocalDateTime orderDate;

    @Column(nullable = false)
    private Double totalAmount;

    @Column(length = 30)
    private String status; 

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
}