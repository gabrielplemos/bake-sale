package com.bakesale.app.item;

import com.bakesale.app.common.BaseMapper;
import com.bakesale.app.item.model.Item;
import com.bakesale.app.item.model.ItemDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ItemMapper extends BaseMapper<Item, ItemDTO> {
    public ItemMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
