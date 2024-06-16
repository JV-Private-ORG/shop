package de.telran.shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
//@Table(name = "favorites")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Favorites {
    @Id
//    @Column(name = "favoriteId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favoriteId;

//    @Column(name = "userId")
//    private long userId;

//    @Column(name = "productId")
    private Long  productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId", nullable=false)
    private Users users;

}
