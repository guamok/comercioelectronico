package com.comercioelectronico.repository;


import com.comercioelectronico.model.Price;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

    @RepositoryRestResource(collectionResourceRel = "price", path = "price")
    public interface Prices2Repository extends PagingAndSortingRepository<Price, Long>, CrudRepository<Price,Long> {


        @Query(value = "select p1.PRODUCT_ID , p1.BRAND_ID, p1.price_list,  p1.price, p1.start_date, p1.end_date, max(p1.priority)\n" +
                "                                      from PRICES p1 \n" +
                "                                     where  ?1 between start_date and end_date  -- funciona ok sin id, solo un valor\n" +
                "                                      and p1.BRAND_ID= ?2 \n" +
                "                                      and p1.product_id= ?3 \n" +
                "                                       group by p1.PRODUCT_ID , p1.BRAND_ID, p1.priority", nativeQuery = true)
        List<Price> findNativeQueryAll(LocalDateTime appData, Long brandId, Long productId);




        List<Price> findByBrandId(@Param("brandId") Long brandId);

    }

