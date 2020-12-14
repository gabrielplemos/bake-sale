package com.bakesale.app.bakesale;

import com.bakesale.app.bakesale.model.BakeSale;
import com.bakesale.app.bakesale.model.SaleDTO;
import com.bakesale.app.common.exception.NotFoundException;
import com.bakesale.app.item.ItemService;
import com.bakesale.app.item.model.Item;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BakeSaleService {
    private final BakeSaleRepository bakeSaleRepository;
    private final ItemService itemService;

    public BakeSaleService(BakeSaleRepository bakeSaleRepository, ItemService itemService) {
        this.bakeSaleRepository = bakeSaleRepository;
        this.itemService = itemService;
    }

    public BakeSale findById(UUID id) {
        return bakeSaleRepository.findById(id).orElseThrow(() ->
                new NotFoundException(
                        String.format("BakeSale not found %s", id)));
    }

    public BakeSale save(BakeSale bakeSale) {
        Set<Item> items = bakeSale.getItems();
        bakeSale.setItems(new HashSet<>());
        BakeSale saved = bakeSaleRepository.save(bakeSale);
        items.forEach(item -> {
            item.setSale(bakeSale);
            itemService.save(item);
        });
        saved.setItems(items);
        return saved;
    }

    public BakeSale processSale(SaleDTO saleDTO) {
        BakeSale bakeSale = findById(saleDTO.getBakeSaleId());
        Map<UUID, Item> itemMap = bakeSale.getItems().stream().collect(Collectors.toMap(Item::getId, Function.identity()));
        saleDTO.getItems().forEach(saleItem -> {
            Item item = itemMap.get(saleItem.getItemId());
            if(item.getAvailable() - saleItem.getQuantity() < 0) {
                throw new RuntimeException();
            }
            item.setAvailable(item.getAvailable() - saleItem.getQuantity());
            itemService.save(item);
        });
        return bakeSale;
    }
}
