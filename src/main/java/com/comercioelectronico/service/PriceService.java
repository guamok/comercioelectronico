package com.comercioelectronico.service;

import com.comercioelectronico.model.Price;
import com.comercioelectronico.model.dto.PriceDTO;
import com.comercioelectronico.repository.IPricesService;
import com.comercioelectronico.repository.PriceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class PriceService implements IPricesService {

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public PriceDTO getPrices(LocalDateTime appDate, Long brandId, Long productId) {
        List<Price> l = priceRepository.findNativeQueryPrices(appDate,  brandId ,productId);
        log.info("getPrices LLEGAAAAAAAAAAAAAA RAULLLLLLLLLLLLLL");
        Integer maxPriority = -1;
        Integer priority;
        int iMaxPriority =0;
        for (int i = 0; i < l.size(); i++) {
            PriceDTO price;
            priority = l.get(i).getPriority();
            if (priority > maxPriority){
                log.info("priority= {}, maxpririty: {}",priority,maxPriority);
                iMaxPriority = i;
            }
        }
        return this.mapperPriceToDto(l.get(iMaxPriority),appDate);
    }

    private PriceDTO mapperPriceToDto(Price p, LocalDateTime appDate){
        PriceDTO priceRet = new PriceDTO();
        priceRet.setBrandId(p.getBrandId());
        priceRet.setProductId(p.getProductId());
        priceRet.setPriceList(p.getPriceList());
        priceRet.setPrice(p.getPrice());
        priceRet.setAppDate(appDate);
        return priceRet;
    }

}
