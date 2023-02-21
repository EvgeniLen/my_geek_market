package ru.lenivtsev.market.orders.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //@ManyToOne
    //@JoinColumn(name = "user_id")
//    @JsonBackReference
    @Column(name = "username")
    private String username;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "order")
    private List<OrderItem> orderItems;

    //@ManyToOne
    //@JoinColumn(name = "status_id")
    @Column(name = "status_id")
    private Long status;

    @Column(name = "email")
    private String email;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "delivery_price")
    private BigDecimal deliveryPrice = new BigDecimal(2);

    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    private DeliveryAddress deliveryAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @Column(name = "create_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "update_at")
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @JsonIgnore
    @Transient
    private boolean confirmed;

}
