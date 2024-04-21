package com.att.tdp.bisbis10.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id // Marks 'id' as the primary key of the entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Configures the generation strategy for the primary key using the database's identity column.
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // Defines a many-to-one relationship with Order entities, loaded lazily.
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    // Specifies the foreign key column for the relationship.
    private Order order; // Links this OrderItem to an Order.

    @Column(name = "dish_id") // Maps this field to the 'dish_id' column in the database.
    private Long dishId; // Stores the ID of the dish associated with this order item.

    @Column(name = "amount") // Maps this field to the 'amount' column in the database.
    private Integer amount;

    // Constructors
    public OrderItem() {
        super();
    }

    // Getter and Setter methods
    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Long getDishId() {
        return dishId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }
     public void setAmount(Integer amount)
     {
         this.amount = amount;
     }
}


