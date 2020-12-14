package com.bakesale.app.bakesale.model;

import com.bakesale.app.item.model.ItemDTO;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public class BakeSaleDTO {
    private UUID id;
    private LocalDate date;
    private Set<ItemDTO> items;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<ItemDTO> getItems() {
        return items;
    }

    public void setItems(Set<ItemDTO> items) {
        this.items = items;
    }
}
