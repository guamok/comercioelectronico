package com.comercioelectronico.service;

import com.comercioelectronico.model.Price;
import com.comercioelectronico.model.dto.PriceDTO;
import com.comercioelectronico.repository.IPriceService;
import com.comercioelectronico.repository.PriceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class PriceService implements IPriceService {

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public PriceDTO getPrices(LocalDateTime appDate, Long brandId, Long productId) {
        List<Price> l = priceRepository.findNativeQueryPrices(appDate,  brandId ,productId);
        //TODO: Put this in private function
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

    /**
     * Mapper Price's entity to dto object Price
     *
     * @param p
     * @param appDate
     * @return priceRet mapped dto from entity
     */
    private PriceDTO mapperPriceToDto(Price p, LocalDateTime appDate){
        PriceDTO priceRet = new PriceDTO();
        priceRet.setBrandId(p.getBrandId());
        priceRet.setProductId(p.getProductId());
        priceRet.setPriceList(p.getPriceList());
        priceRet.setPrice(p.getPrice());
        priceRet.setAppDate(appDate);
        //TODO: Put this in private function
        return priceRet;
    }

}
