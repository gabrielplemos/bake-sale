package com.bakesale.app.bakesale.model;

import java.util.Set;
import java.util.UUID;

public class SaleDTO {
    private UUID bakeSaleId;
    private Set<SaleItemDTO> items;

    public UUID getBakeSaleId() {
        return bakeSaleId;
    }

    public void setBakeSaleId(UUID bakeSaleId) {
        this.bakeSaleId = bakeSaleId;
    }

    public Set<SaleItemDTO> getItems() {
        return items;
    }

    public void setItems(Set<SaleItemDTO> items) {
        this.items = items;
    }
}
