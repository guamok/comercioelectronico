package com.comercioelectronico.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name="prices")
@Data
@NoArgsConstructor
public class Price implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="brand_id")
    private Long brandId;

    @Column(name="start_date")
    private LocalDateTime startDate;

    @Column(name="end_date")
    private LocalDateTime endDate;

    @Column(name="price_list")
    private Integer priceList;

    @Column(name="product_id")
    private Integer productId;

    @Column(name="priority")
    private Integer priority;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="curr")
    private String curr;
}
