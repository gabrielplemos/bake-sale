package com.bakesale.app.bakesale.model;

import java.util.UUID;

public class SaleItemDTO {
    private UUID itemId;
    private Integer quantity;

    public UUID getItemId() {
        return itemId;
    }

    public void setItemId(UUID itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
