package com.bakesale.app.bakesale;

import com.bakesale.app.bakesale.model.BakeSale;
import com.bakesale.app.bakesale.model.BakeSaleDTO;
import com.bakesale.app.bakesale.model.SaleDTO;
import com.bakesale.app.common.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@Controller
@RequestMapping("/v1/bake-sales")
public class BakeSaleController {

    private final BakeSaleMapper bakeSaleMapper;
    private final BakeSaleService bakeSaleService;

    public BakeSaleController(BakeSaleMapper bakeSaleMapper, BakeSaleService bakeSaleService) {
        this.bakeSaleMapper = bakeSaleMapper;
        this.bakeSaleService = bakeSaleService;
    }

    @PostMapping
    public ResponseEntity<BakeSaleDTO> createBakeSale(@RequestBody BakeSaleDTO bakeSaleDTO) {
        return ResponseEntity.ok(bakeSaleMapper.convertToDTO(bakeSaleService.save(bakeSaleMapper.convertToEntity(bakeSaleDTO))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BakeSaleDTO> getBakeSale(@PathVariable UUID id) {
        BakeSale bakeSale = bakeSaleService.findById(id);
        return ResponseEntity.ok(bakeSaleMapper.convertToDTO(bakeSale));
    }

    @PostMapping("/{id}/sale")
    public ResponseEntity<BakeSaleDTO> sale(@PathVariable UUID id, @RequestBody SaleDTO saleDTO) {
        BakeSale bakeSale = bakeSaleService.processSale(saleDTO);
        return ResponseEntity.ok(bakeSaleMapper.convertToDTO(bakeSale));
    }
}
