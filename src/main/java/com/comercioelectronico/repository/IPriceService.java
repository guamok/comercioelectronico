package com.comercioelectronico.repository;

import com.comercioelectronico.model.dto.PriceDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public interface IPriceService {
    public ResponseEntity<PriceDTO> getPrices(LocalDateTime appData, Long brandId, Long productId);
}
