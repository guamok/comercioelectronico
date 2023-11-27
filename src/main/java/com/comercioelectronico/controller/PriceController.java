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
import org.springframework.web.server.ResponseStatusException;

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
            log.error("422 Error: The appDateTime is wrong typed from client's side.");
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "The appDateTime is wrong typed from client's side"  );
        }

        ResponseEntity<PriceDTO> priceDTO = service.getPrices(appDateTime,brandId,productId);
        if(log.isDebugEnabled()){
            log.debug("StatusCode from service is= {}, body: {} ",priceDTO.getStatusCode(), priceDTO.getBody());
        }
        if(priceDTO.getStatusCode().is4xxClientError()){
            log.error("StatusCode from service is= {}, body: {} ",priceDTO.getStatusCode(), priceDTO.getBody());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"  );
        }
        return new ResponseEntity<> (priceDTO.getBody(), HttpStatus.OK);
    }
}
