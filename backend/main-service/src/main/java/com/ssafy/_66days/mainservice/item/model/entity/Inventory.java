package com.ssafy._66days.mainservice.item.model.entity;

import com.ssafy._66days.mainservice.user.model.entity.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@IdClass(InventoryId.class) // Jpa 복합키 사용 어노테이션
@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {
    @Id
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Id
    @NotNull
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @NotNull
    @Column(name = "quantity", nullable = false)
    @ColumnDefault("0")
    private int quantity;

    public void updateQuantity() {
        this.quantity++;
    }



}
