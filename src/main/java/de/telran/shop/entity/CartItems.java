package de.telran.shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
//@Table(name = "cartItems")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItems {
    @Id
//    @Column(name = "cartItemId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

//    @Column(name = "cartId")
//    private long cartId;

//    @Column(name = "productId")
    private Long  productId;

    @Column(name = "Quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cartId", nullable=false)
    private Cart cart;
}