package com.att.tdp.bisbis10.DTO;

public class OrderItemDTO {

    private Long dishId;
    private Integer amount;

    // Getters and Setters
    public Long getDishId() {
        return dishId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}

