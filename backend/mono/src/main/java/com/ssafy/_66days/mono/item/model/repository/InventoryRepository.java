package com.ssafy._66days.mono.item.model.repository;

import com.ssafy._66days.mono.item.model.entity.Inventory;
import com.ssafy._66days.mono.item.model.entity.InventoryId;
import com.ssafy._66days.mono.item.model.entity.Item;
import com.ssafy._66days.mono.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, InventoryId>{
    Optional<Inventory> findByUserAndItem(User user, Item item);
}
