package com.comercioelectronico.model.dto;

import com.comercioelectronico.model.Price;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PriceDTO {

    private Long brandId;
    private LocalDateTime appDate;
    private Integer priceList;
    private Integer productId;
    private BigDecimal price;

}
