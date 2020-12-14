package com.bakesale.app.bakesale;

import com.bakesale.app.bakesale.model.BakeSale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BakeSaleRepository  extends CrudRepository<BakeSale, UUID> {}