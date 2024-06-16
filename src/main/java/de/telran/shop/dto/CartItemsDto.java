package de.telran.shop.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemsDto {
    private Long cartItemId;
    private Long productId;
    private Integer quantity;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("cart")
    private CartDto cart;

}
