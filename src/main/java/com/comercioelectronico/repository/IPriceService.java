package com.comercioelectronico.repository;

import com.comercioelectronico.model.dto.PriceDTO;

import java.time.LocalDateTime;

public interface IPriceService {
    public PriceDTO getPrices(LocalDateTime appData, Long brandId, Long productId);
}
