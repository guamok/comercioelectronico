package com.comercioelectronico.service.impl;

import com.comercioelectronico.model.Price;
import com.comercioelectronico.model.dto.PriceDTO;
import com.comercioelectronico.repository.PriceRepository;
import com.comercioelectronico.service.IPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class PriceService implements IPriceService {

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public ResponseEntity<PriceDTO> getPrices(LocalDateTime appDate, Long brandId, Long productId) {
        List<Price> lPrices = priceRepository.findNativeQueryPrices(appDate,  brandId ,productId);
        if(lPrices.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<> (this.mapperPriceToDto(lPrices.get(this.getIPriority(lPrices)),appDate), HttpStatus.OK);
    }

    /**
     * Mapper Price's entity to dto object Price
     *
     * @param p Entity to mapper
     * @param appDate app date
     * @return priceRet mapped dto from entity
     */
    private PriceDTO mapperPriceToDto(Price p, LocalDateTime appDate){
        PriceDTO priceRet = new PriceDTO();
        priceRet.setBrandId(p.getBrandId());
        priceRet.setProductId(p.getProductId());
        priceRet.setPriceList(p.getPriceList());
        priceRet.setPrice(p.getPriceAmount());
        priceRet.setAppDate(appDate);

        return priceRet;
    }

    /**
     * Get max priority from Prices
     *
     * @param l Price's List
     * @return i  ind max of Prices list
     */
    private Integer getIPriority(List<Price> l){
        Integer maxPriority = -1;
        Integer priority;
        int iMaxPriority =0;
        for (int i = 0; i < l.size(); i++) {
            priority = l.get(i).getPriority();
            if (priority > maxPriority){
                if(log.isDebugEnabled()){
                    log.debug("priority= {}, maxpriority: {}",priority,maxPriority);
                }
                iMaxPriority = i;
            }
        }
        return iMaxPriority;
    }

}
