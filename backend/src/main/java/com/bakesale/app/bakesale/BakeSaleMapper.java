package com.bakesale.app.bakesale;

import com.bakesale.app.bakesale.model.BakeSale;
import com.bakesale.app.bakesale.model.BakeSaleDTO;
import com.bakesale.app.item.ItemMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BakeSaleMapper {

    private final ItemMapper itemMapper;

    public BakeSaleMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public BakeSale convertToEntity(BakeSaleDTO dto) {
        BakeSale bakeSale = new BakeSale();
        bakeSale.setDate(LocalDate.now());
        bakeSale.setItems(itemMapper.convertToEntity(dto.getItems()));
        return bakeSale;
    }

    public BakeSaleDTO convertToDTO(BakeSale entity) {
        BakeSaleDTO bakeSaleDTO = new BakeSaleDTO();
        bakeSaleDTO.setId(entity.getId());
        bakeSaleDTO.setDate(entity.getDate());
        bakeSaleDTO.setItems(itemMapper.convertToDTO(entity.getItems()));
        return bakeSaleDTO;
    }

}
