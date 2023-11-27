package com.comercioelectronico.controller;

import com.comercioelectronico.model.dto.PriceDTO;
import com.comercioelectronico.service.IPriceService;
import com.comercioelectronico.utils.UtilsPrice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private IPriceService service;

    @GetMapping(value="price/{appDate}/{productId}/{brandId}")
    public ResponseEntity<PriceDTO> getPrices(@PathVariable  String appDate,@PathVariable Long productId,@PathVariable Long brandId) {
        if(log.isDebugEnabled()){
            log.debug("Input´s client: brandId= {}, productId: {}, appDate: {}",brandId,productId,appDate);
        }
        LocalDateTime appDateTime = null;
        try{
             appDateTime = UtilsPrice.parserLocalDate(appDate);
        }catch (DateTimeParseException dte){
            log.error("The appDateTime is wrong typed from client's side.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ResponseEntity<PriceDTO> priceDTO = service.getPrices(appDateTime,brandId,productId);
        if(log.isDebugEnabled()){
            log.debug("StatusCode from service is= {} ",priceDTO.getStatusCode());
        }
        if(priceDTO.getStatusCode().is4xxClientError()){
            log.error("StatusCode from service is= {}",priceDTO.getStatusCode());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<> (priceDTO.getBody(), HttpStatus.OK);
    }
}
