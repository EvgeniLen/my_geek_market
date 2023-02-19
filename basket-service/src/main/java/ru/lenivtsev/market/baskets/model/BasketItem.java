package ru.lenivtsev.market.baskets.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "basket_items")
@Getter
@Setter
@NoArgsConstructor
public class BasketItem {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "quantity")
        private Long quantity;

        @Column(name = "item_price")
        private BigDecimal itemPrice;

        @Column(name = "total_price")
        private BigDecimal totalPrice;

       //@ManyToOne
       // @JoinColumn(name = "product_id")
        @Column(name = "product_id")
        private Long productId;

        @Column(name = "title")
        private String title;

        @ManyToOne
        @JoinColumn(name = "basket_id")
        private Basket basket;

}
