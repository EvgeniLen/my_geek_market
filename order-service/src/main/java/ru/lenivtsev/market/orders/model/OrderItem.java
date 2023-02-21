package ru.lenivtsev.market.orders.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders_item")
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {
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
    //@JoinColumn(name = "product_id")
    @Column(name = "product_id")
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "title")
    private String title;


}
