package com.comercioelectronico.repository;


import com.comercioelectronico.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;


public interface PriceRepository extends JpaRepository<Price, Long> {
     @Query(value = "select id, curr, end_date, start_date, priority, :appData, product_id, brand_id, price_list, price from PRICES where BRAND_ID= :brand and product_id= :productId and :appData between start_date and end_date", nativeQuery = true)
    List<Price> findNativeQueryPrices(@Param("appData") LocalDateTime appData, @Param("brand") long brand, @Param("productId") long productId);
   }
