package ru.lenivtsev.market.orders.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "delivery_addresses")
public class DeliveryAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //@ManyToOne
    //@JoinColumn(name = "user_id")
    @Column(name = "username")
    private String username;

    @Column(name = "address")
    private String address;
}
