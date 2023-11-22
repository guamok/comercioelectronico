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


    public PriceDTO(Price price) {
        this.brandId = price.getBrandId();
        this.appDate= price.getStartDate();
        this.priceList= price.getPriceList();
        this.productId= price.getProductId();
        this.price= price.getPrice();
    }
}
