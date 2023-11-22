package com.comercioelectronico.controller;

import com.comercioelectronico.model.dto.PriceDTO;
import com.comercioelectronico.repository.IPricesService;
import com.comercioelectronico.utils.UtilsPrice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping(value="ecommerce")
@Slf4j
public class PriceController {

    @Autowired
    private IPricesService service;

    @GetMapping(value="price/{appDate}/{productId}/{brandId}")
    public PriceDTO getPrices(@PathVariable  String appDate,@PathVariable Long productId,@PathVariable Long brandId) {
        if(log.isDebugEnabled()){
            log.debug("Input´s client: brandId= {}, productId: {}, appDate: {}",brandId,productId, appDate);
        }
        LocalDateTime appDateTime = null;
        try{
             appDateTime = UtilsPrice.parserLocalDate(appDate);
        }catch (DateTimeParseException dte){
            log.error("The appDateTime is wrong typed, keep in mind and anyway put right now date.");
            return service.getPrices(LocalDateTime. now(),brandId,productId);
        }
        return service.getPrices(appDateTime,brandId,productId);
    }
}
