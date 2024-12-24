package model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Customer customer;

    private LocalDateTime orderDate;

    private double totalAmount;
}
