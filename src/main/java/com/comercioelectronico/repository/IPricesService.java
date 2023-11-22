package com.comercioelectronico.repository;

import com.comercioelectronico.model.dto.PriceDTO;

import java.time.LocalDateTime;

public interface IPricesService {
    public PriceDTO getPrices(LocalDateTime appData, Long brandId, Long productId);
}
